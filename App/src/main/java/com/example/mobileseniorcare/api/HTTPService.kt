package com.example.mobileseniorcare.api

import com.example.mobileseniorcare.dataclass.Endereco
import com.google.gson.Gson
import java.net.HttpURLConnection
import java.net.URL

class HTTPService {
    private val gson = Gson()

    fun buscarEnderecoPorCep(cep: String): Endereco? {
        val url = URL("https://viacep.com.br/ws/$cep/json/")
        val connection = url.openConnection() as HttpURLConnection

        return try {
            connection.requestMethod = "GET"
            connection.connectTimeout = 5000
            connection.readTimeout = 5000

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val response = connection.inputStream.bufferedReader().use { it.readText() }
                gson.fromJson(response, Endereco::class.java)
            } else {
                println("Erro na conexão: Código ${connection.responseCode}")
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } finally {
            connection.disconnect()
        }
    }
}