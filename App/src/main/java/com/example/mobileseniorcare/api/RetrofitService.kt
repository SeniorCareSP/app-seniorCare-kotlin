package com.example.mobileseniorcare.api

import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonNull
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.format.DateTimeFormatter


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
        val logging = HttpLoggingInterceptor()
        logging.setLevel(Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val gson = GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
            .create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson)) // Converter resposta JSON para objetos Kotlin
            .client(httpClient.build())
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


internal class LocalDateAdapter : JsonSerializer<LocalDate?> {
    override fun serialize(
        src: LocalDate?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return if (src != null) {
            JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE))
        } else {
            JsonNull.INSTANCE
        }
    }

}