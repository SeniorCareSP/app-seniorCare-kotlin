package com.example.mobileseniorcare.api

import com.example.mobileseniorcare.dataclass.usuario.UsuarioCuidador
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

data class Usuario(
    val id: Long,
    val nome: String,
    val email: String,
    val senha: String
)

interface ApiSeniorCare {

    // Exemplo de busca de todos os usuários
    @GET("usuarios")
    suspend fun getUsuarios(): Response<List<UsuarioCuidador>>

    // Exemplo de login
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<UsuarioCuidador>
}

data class LoginRequest(
    val email: String,
    val senha: String
)
