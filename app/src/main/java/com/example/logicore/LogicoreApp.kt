package com.example.logicore

import android.app.Application
import com.example.logicore.features.events.bootstrap.ProjectionBootstrap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class LogicoreApp : Application() {

    private val appScope = CoroutineScope(
        SupervisorJob() + Dispatchers.IO
    )

    // SINGLE SOURCE OF TRUTH (safe)
    private var projectionBootstrap: ProjectionBootstrap? = null

    override fun onCreate() {
        super.onCreate()

        initDependencies()
        rebuildProjectionsOnStartup()
    }

    private fun initDependencies() {
        projectionBootstrap = ServiceLocator.projectionBootstrap(this)
    }

    private fun rebuildProjectionsOnStartup() {

        appScope.launch {

            val bootstrap = projectionBootstrap

            if (bootstrap != null) {

                try {
                    bootstrap.rebuildAllTenants()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            } else {
                println("ProjectionBootstrap not initialized")
            }
        }
    }
}