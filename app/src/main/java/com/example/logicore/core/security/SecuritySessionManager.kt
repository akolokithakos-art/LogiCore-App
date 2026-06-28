package com.example.logicore.core.security

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SecuritySessionManager {

    private val _context =
        MutableStateFlow<SecurityContext?>(null)

    val context: StateFlow<SecurityContext?>
        get() = _context

    fun set(context: SecurityContext) {
        _context.value = context
    }

    fun clear() {
        _context.value = null
    }

    fun require(): SecurityContext {
        return _context.value
            ?: throw UnauthenticatedException("No active session")
    }
}