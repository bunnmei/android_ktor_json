package com.example.calender.http


import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable

class Greeting {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun greeting(): List<Weather> {
        val response: List<Weather> = client.get("http://10.0.2.2:3000/json").body()
        return response
    }
}



@Serializable
data class Weather(
    val day: String,
    val precipitation: Precipitation,
    val temperature: Temperature
)

@Serializable
data class Precipitation(
    val total: String,
    val max_1h: String,
    val max_10min: String,
)

@Serializable
data class Temperature(
    val average: String,
    val max: String,
    val min: String,
)