package com.example.logicore.core.security

open class SecurityException(
    message: String
) : RuntimeException(message)

class AccessDeniedException(
    message: String
) : SecurityException(message)

class TenantViolationException(
    message: String
) : SecurityException(message)

class UnauthenticatedException(
    message: String
) : SecurityException(message)