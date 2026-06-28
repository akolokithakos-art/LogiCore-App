package com.example.logicore.features.rl.training

import com.example.logicore.features.rl.memory.Experience
import com.example.logicore.features.events.data.local.EventStoreEntity
import com.google.gson.Gson

class TrainingDatasetBuilder(
    private val gson: Gson
) {

    fun build(event: EventStoreEntity): Experience? {

        return try {

            val payload = gson.fromJson(
                event.payload,
                Map::class.java
            )

            Experience(
                state = mapToState(payload),
                action = mapToAction(payload),
                reward = payload["reward"] as? Double ?: 0.0,
                nextState = mapToNextState(payload)
            )

        } catch (e: Exception) {
            null
        }
    }

    private fun mapToState(data: Map<*, *>): com.example.logicore.features.rl.state.DispatchState {
        return com.example.logicore.features.rl.state.DispatchState(
            distanceKm = (data["distanceKm"] as? Double) ?: 0.0,
            traffic = (data["traffic"] as? Double) ?: 0.0,
            driverLoad = (data["driverLoad"] as? Double) ?: 0.0,
            vehicleCapacity = (data["vehicleCapacity"] as? Double) ?: 0.0,
            slaUrgency = (data["slaUrgency"] as? Double) ?: 0.0
        )
    }

    private fun mapToAction(data: Map<*, *>): com.example.logicore.features.rl.action.DispatchAction {
        return com.example.logicore.features.rl.action.DispatchAction(
            driverId = (data["driverId"] as? String) ?: "",
            vehicleId = (data["vehicleId"] as? String) ?: ""
        )
    }

    private fun mapToNextState(data: Map<*, *>): com.example.logicore.features.rl.state.DispatchState {
        return mapToState(data) // placeholder
    }
}
