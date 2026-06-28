package com.example.logicore.features.core.resilience

suspend fun <T> retry(
    times: Int = 3,
    block: suspend () -> T
): T {

    require(times > 0) {
        "Retry attempts must be greater than zero"
    }

    var lastError: Exception? = null

    repeat(times) {

        try {
            return block()
        } catch (e: Exception) {
            lastError = e
        }
    }

    throw lastError
        ?: IllegalStateException(
            "Retry failed without captured exception"
        )
}