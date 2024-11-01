package com.example.mobileseniorcare.api

import com.example.mobileseniorcare.dataclass.usuario.UsuarioCuidador
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

data class Usuario(
    val id: Long,
    val nome: String,
    val email: String,
    val senha: String
)

interface ApiSeniorCare {


        // Exemplo de login
        @POST("login")
        suspend fun login(@Body loginRequest: LoginRequest): Response<UsuarioCuidador>

        // Endpoint para obter todos os usuários
        @GET("/usuarios")
        suspend fun getAllUsuarios(): Response<List<UsuarioCuidador>>

        // Endpoint para obter um usuário específico por ID
        @GET("/usuarios/{id}")
        suspend fun getUsuarioById(@Path("id") id: Int): Response<UsuarioCuidador>

        // Endpoint para criar um novo usuário
        @POST("/usuarios")
        suspend fun createUsuario(@Body usuario: UsuarioCuidador): Response<UsuarioCuidador>

        // Endpoint para atualizar um usuário existente
        @PUT("/usuarios/{id}")
        suspend fun updateUsuario(@Path("id") id: Int, @Body usuario: UsuarioCuidador): Response<UsuarioCuidador>

        // Endpoint para excluir um usuário
        @DELETE("/usuarios/{id}")
        suspend fun deleteUsuario(@Path("id") id: Int): Response<Void>
    }



data class LoginRequest(
    val email: String,
    val senha: String
)
