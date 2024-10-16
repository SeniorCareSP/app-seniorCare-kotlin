package com.example.mobileseniorcare.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {


    val BASE_URL_SENIOR_CARE = "http://localhost:8080"

    // Função que retorna o cliente Retrofit para a API Senior Care
    fun getApiSeniorCare(): ApiSeniorCare {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_SENIOR_CARE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiSeniorCare::class.java)
    }
}
