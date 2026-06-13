package com.example.logicore.routing.osrm

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object OsrmClient {

    private const val BASE_URL = "http://router.project-osrm.org/"

    val api: OsrmApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OsrmApi::class.java)
    }
}