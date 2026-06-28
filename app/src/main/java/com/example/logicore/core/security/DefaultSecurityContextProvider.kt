package com.example.logicore.core.security

class DefaultSecurityContextProvider(
    private val sessionManager: SecuritySessionManager
) : SecurityContextProvider {

    override suspend fun current(): SecurityContext? {
        return sessionManager.context.value
    }
}