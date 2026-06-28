package com.example.logicore.features.ml.training

class DriftDetector {

    fun detect(oldError: Double, newError: Double): Boolean {
        return newError > oldError * 1.2
    }
}