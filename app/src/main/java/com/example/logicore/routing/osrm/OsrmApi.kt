package com.example.logicore.routing.osrm

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OsrmApi {

    @GET("route/v1/driving/{coords}")
    suspend fun getRoute(
        @Path("coords") coords: String,
        @Query("overview") overview: String = "false",
        @Query("alternatives") alternatives: Boolean = false,
        @Query("steps") steps: Boolean = false
    ): OsrmResponse
}