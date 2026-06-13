package com.example.logicore.features.auth

import com.example.logicore.features.auth.model.AppUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthSessionManager {

    private val _user =
        MutableStateFlow<AppUser?>(null)

    val user: StateFlow<AppUser?> = _user

    fun login(user: AppUser) {
        _user.value = user
    }

    fun logout() {
        _user.value = null
    }

    fun currentUser(): AppUser? = _user.value
}