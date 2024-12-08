package com.example.mobileseniorcare.api

import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonNull
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.format.DateTimeFormatter


object RetrofitService {

    private const val BASE_URL = "http://44.221.246.250:8080/api/"

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
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()
            .create(ApiSeniorCare::class.java)
    }

    fun getApiSeniorCareToken(token:String): ApiSeniorCare {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(InterceptorTokenJWT(token))
            .build()

        val cliente =
            Retrofit.Builder()
                .client(okHttpClient) // aqui estamos dizendo que o cliente Retrofit usará o cliente OkHttp que criamos
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiSeniorCare::class.java)
        return cliente
    }





    val retrofit = Retrofit.Builder()
        .baseUrl("https://viacep.com.br/ws/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val viaCepService = retrofit.create(ViaCep::class.java)


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

class InterceptorTokenJWT(val token:String): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val currentRequest = chain.request().newBuilder()

        currentRequest.addHeader("Authorization", "Bearer $token")

        val newRequest = currentRequest.build()
        return chain.proceed(newRequest)
    }
}