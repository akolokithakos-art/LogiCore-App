package com.example.logicore.features.ml.tflite

import android.content.Context
import org.tensorflow.lite.Interpreter
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class TFLiteModelLoader(private val context: Context) {

    fun loadModel(): Interpreter {

        val fileDescriptor = context.assets.openFd("model.tflite")

        val inputStream = fileDescriptor.createInputStream()
        val fileChannel = inputStream.channel

        val mappedBuffer: MappedByteBuffer = fileChannel.map(
            FileChannel.MapMode.READ_ONLY,
            fileDescriptor.startOffset,
            fileDescriptor.declaredLength
        )

        return Interpreter(mappedBuffer)
    }
}