package com.example.logicore.core.audit

object AuditFactory {

    fun create(
        userId: String?
    ): AuditMetadata {

        val now = System.currentTimeMillis()

        return AuditMetadata(
            createdAt = now,
            updatedAt = now,
            createdBy = userId,
            updatedBy = userId,
            version = 1L
        )
    }

    fun update(
        existing: AuditMetadata,
        userId: String?
    ): AuditMetadata {

        return existing.copy(
            updatedAt = System.currentTimeMillis(),
            updatedBy = userId,
            version = existing.version + 1
        )
    }
}