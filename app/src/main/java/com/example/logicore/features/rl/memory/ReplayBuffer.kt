package com.example.logicore.features.rl.memory

class ReplayBuffer {

    private val buffer = mutableListOf<Experience>()

    fun add(exp: Experience) {
        buffer.add(exp)
    }

    fun sample(): List<Experience> {
        return buffer.shuffled().take(32)
    }
}