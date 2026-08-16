package com.gps.appvoos.api

import okhttp3.OkHttpClient
import okhttp3.Request

class FlightApi{
    private val client = OkHttpClient()

    fun buscarPrecos(): String{

        val request = Request.Builder()
            .url(
                "https://google-flights4.p.rapidapi.com/" +
                        "price-graph/for-one-way" +
                        "?departureId=JFK" +
                        "&arrivalId=LOS" +
                        "&departureDate=2026-08-18" +
                        "&startDate=2026-08-18"
            )
            .get()
            .addHeader("x-rapidapi-key",
                "998eb13b91msh5ed90f936399b6ep137985jsnfe7e313e9b82"
            )
            .addHeader("x-rapidapi-host",
                "google-flights4.p.rapidapi.com"
            )
            .addHeader("Content-Type",
                "application/json"
            )
            .build()

        val response = client.newCall(request).execute()

        val body = response.body?.string() ?: ""

        return """Código HTTP: ${response.code}
            Mensagem: ${response.message}
            Resposta:$body""".trimIndent()
    }
}