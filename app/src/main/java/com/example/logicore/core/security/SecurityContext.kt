package com.example.logicore.core.security

import com.example.logicore.features.firebase.auth.UserRole

data class SecurityContext(

    val uid: String,

    val tenantId: String,

    val role: UserRole,

    val permissions: Set<Permission>
)