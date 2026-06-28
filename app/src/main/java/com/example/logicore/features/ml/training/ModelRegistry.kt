package com.example.logicore.features.ml.training

class ModelRegistry {

    private var activeModelVersion: String = "v1"

    fun getActive(): String = activeModelVersion

    fun update(version: String) {
        activeModelVersion = version
    }
}