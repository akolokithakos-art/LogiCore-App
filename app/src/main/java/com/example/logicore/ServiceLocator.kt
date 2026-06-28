package com.example.logicore

import android.content.Context
import androidx.room.Room
import com.example.logicore.features.core.runtime.AppScope
import com.example.logicore.features.events.bootstrap.ProjectionBootstrap
import com.example.logicore.features.events.checkpoint.CheckpointStore
import com.example.logicore.features.events.projections.StockProjection
import com.example.logicore.features.events.registry.ProjectionRegistry
import com.example.logicore.features.events.replay.EventReplayEngine
import com.example.logicore.features.events.resilience.InMemoryDeadLetterQueue
import com.example.logicore.features.events.resilience.ResilientProjectionExecutor
import com.example.logicore.features.events.resilience.retry.RetryPolicy
import com.example.logicore.features.events.resilience.retry.RetryScheduler
import com.example.logicore.features.core.database.AppDatabase
import com.example.logicore.features.fleet.data.local.DriverDao
import com.example.logicore.features.firebase.repository.FirebaseRouteRepository
import com.example.logicore.features.firebase.firestore.FirestoreFirebaseRouteRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.coroutines.launch

object ServiceLocator {

    private var database: AppDatabase? = null
    private var projectionBootstrap: ProjectionBootstrap? = null

    private fun getDatabase(context: Context): AppDatabase {
        return database ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "logicore_db"
            )
            .fallbackToDestructiveMigration()
            .build()
            database = instance
            instance
        }
    }

    private val gson = Gson()

    fun driverDao(context: Context): DriverDao {
        return getDatabase(context).driverDao()
    }

    fun firebaseRouteRepository(): FirebaseRouteRepository {
        return FirestoreFirebaseRouteRepository(FirebaseFirestore.getInstance())
    }

    fun projectionBootstrap(context: Context): ProjectionBootstrap {
        return projectionBootstrap ?: synchronized(this) {
            val instance = createProjectionBootstrap(context)
            projectionBootstrap = instance
            instance
        }
    }

    private fun createProjectionBootstrap(context: Context): ProjectionBootstrap {
        val db = getDatabase(context)
        val dlq = InMemoryDeadLetterQueue()
        val retryPolicy = RetryPolicy()
        
        // Lateinit to resolve circular dependency with RetryScheduler
        lateinit var executor: ResilientProjectionExecutor
        
        val stockProjection = StockProjection(db.stockProjectionDao(), gson)
        val registry = ProjectionRegistry(listOf(stockProjection))
        
        val retryScheduler = RetryScheduler(
            scope = AppScope.scope,
            policy = retryPolicy,
            executor = { state ->
                val event = db.eventStoreDao().getById(state.eventId.toLong())
                val projection = registry.getByName(state.projection)
                if (event != null && projection != null) {
                    AppScope.scope.launch {
                        executor.execute(projection, event)
                    }
                }
            }
        )

        executor = ResilientProjectionExecutor(
            policy = retryPolicy,
            retryScheduler = retryScheduler,
            dlq = dlq
        )
        
        val checkpointStore = CheckpointStore(db.checkpointDao())
        
        val replayEngine = EventReplayEngine(
            dao = db.eventStoreDao(),
            checkpointStore = checkpointStore,
            registry = registry,
            executor = executor,
            dlq = dlq
        )
        
        return ProjectionBootstrap(
            tenantDao = db.tenantDao(),
            replayEngine = replayEngine
        )
    }
}
