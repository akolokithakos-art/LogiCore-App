package com.example.logicore.core.audit

data class AuditMetadata(

    val createdAt: Long = 0L,

    val updatedAt: Long = 0L,

    val createdBy: String? = null,

    val updatedBy: String? = null,

    val version: Long = 1L
)