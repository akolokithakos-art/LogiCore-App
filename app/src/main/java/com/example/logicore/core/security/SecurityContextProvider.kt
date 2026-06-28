package com.example.logicore.core.security

interface SecurityContextProvider {

    suspend fun current(): SecurityContext?
}