package com.example.logicore.features.firebase.auth

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirestoreUserProfileRepository(
    private val firestore: FirebaseFirestore
) : UserProfileRepository {

    override suspend fun getProfile(
        uid: String
    ): FirebaseUserProfile? {

        return firestore
            .collection("users")
            .document(uid)
            .get()
            .await()
            .toObject(
                FirebaseUserProfile::class.java
            )
    }
}