package com.example.logicore.core.audit

interface TenantDocument {

    val tenantId: String

    val audit: AuditMetadata
}