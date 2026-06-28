package com.example.logicore.features.address.domain

import com.example.logicore.features.address.domain.model.GeoPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.net.URL
import java.net.URLEncoder

class NominatimGeocoder : AddressGeocoder {

    override suspend fun geocode(
        street: String,
        number: String,
        city: String
    ): GeoPoint? = withContext(Dispatchers.IO) {
        try {
            val query = URLEncoder.encode("$street $number, $city", "UTF-8")
            val url = "https://nominatim.openstreetmap.org/search?q=$query&format=json&limit=1"
            
            val connection = URL(url).openConnection()
            connection.setRequestProperty("User-Agent", "LogiCore-App")
            
            val response = connection.getInputStream().bufferedReader().use { it.readText() }
            val jsonArray = JSONArray(response)
            
            if (jsonArray.length() > 0) {
                val obj = jsonArray.getJSONObject(0)
                GeoPoint(
                    latitude = obj.getDouble("lat"),
                    longitude = obj.getDouble("lon")
                )
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
