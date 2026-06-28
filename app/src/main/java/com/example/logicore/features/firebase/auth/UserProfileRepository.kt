package com.example.logicore.features.firebase.auth

interface UserProfileRepository {

    suspend fun getProfile(
        uid: String
    ): FirebaseUserProfile?
}