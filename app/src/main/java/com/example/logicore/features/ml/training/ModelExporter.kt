package com.example.logicore.features.ml.training

import android.content.Context
import java.io.File

class ModelExporter(
    private val context: Context
) {

    fun export(version: String) {

        val modelFile = File(context.filesDir, "model_$version.tflite")

        modelFile.writeBytes(byteArrayOf()) // placeholder export

        println("Model exported: ${modelFile.absolutePath}")
    }
}