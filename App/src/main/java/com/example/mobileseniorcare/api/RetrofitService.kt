package com.example.mobileseniorcare.api

import com.example.mobileseniorcare.dataclassx.ViaCep
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private const val BASE_URL_SENIOR_CARE = "http://44.221.246.250:8080/api/"
    private const val BASE_URL = "http://44.221.246.250:8080/api/" // Emulador acessando localhost

    // Instância de Retrofit sem token, para requisições como o login
//    fun getApiSeniorCareWithoutToken(): ApiSeniorCare {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL_SENIOR_CARE)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ApiSeniorCare::class.java)
//    }],

    fun getApiWithoutToken(): ApiSeniorCare {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Converter resposta JSON para objetos Kotlin
            .build()
            .create(ApiSeniorCare::class.java)
    }



    val retrofit = Retrofit.Builder()
        .baseUrl("https://viacep.com.br/ws/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val viaCepService = retrofit.create(ViaCep::class.java)



    // Instância de Retrofit com token, para requisições autenticadas
//    fun getApiSeniorCare(token: String): ApiSeniorCare {
//        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(InterceptorTokenJWT(token))
//            .build()
//
//        return Retrofit.Builder()
//            .client(okHttpClient)
//            .baseUrl(BASE_URL_SENIOR_CARE)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ApiSeniorCare::class.java)
//    }
}
