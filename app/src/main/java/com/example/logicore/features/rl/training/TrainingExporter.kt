package com.example.logicore.features.rl.training

import com.example.logicore.features.rl.memory.Experience
import com.example.logicore.features.events.data.local.EventStoreEntity
import com.google.gson.Gson

class TrainingExporter(
    private val gson: Gson
) {

    fun export(experiences: List<Experience>): String {
        return gson.toJson(experiences)
    }
}
