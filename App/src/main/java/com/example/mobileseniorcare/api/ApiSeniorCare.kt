package com.example.mobileseniorcare.api

import android.telecom.Call
import com.example.mobileseniorcare.dataclass.Idoso
import com.example.mobileseniorcare.dataclass.usuario.UsuarioCuidador
import com.example.mobileseniorcare.dataclass.usuario.UsuarioResponse
import com.example.mobileseniorcare.dataclass.usuario.UsuarioTokenDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Url

data class Usuario(
    val id: Long,
    val nome: String,
    val email: String,
    val senha: String
)

interface ApiSeniorCare {

    // Exemplo de login
    @POST("usuarios/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<UsuarioTokenDto>

    // Endpoint para obter todos os usuários
    @GET("/usuarios")
    suspend fun getAllUsuarios(): Response<List<UsuarioCuidador>>

    @GET("usuarios/listarDistanciaDoCuidador/{id}")
    suspend fun getCuidadores(@Path("id") id: Int): Response<List<UsuarioResponse>>

    @GET("usuarios/listarDistanciaDoResponsavel/{id}")
    suspend fun getResponsavel(@Path("id") id: Int): Response<List<UsuarioResponse>>


    // Endpoint para obter um usuário específico por ID
    @GET("cuidadores/{id}")
    suspend fun getCuidadorById(@Path("id") id: Int): Response<UsuarioResponse>

    @GET("responsaveis/{id}")
    suspend fun getResponsavelById(@Path("id") id: Int): Response<UsuarioResponse>

    @POST
    suspend fun createUsuario(@Url endpoint: String, @Body usuario: UsuarioCuidador): Response<UsuarioCuidador>

    @PUT("/usuarios/{id}")
    suspend fun updateUsuario(@Path("id") id: Int, @Body usuario: UsuarioCuidador): Response<UsuarioCuidador>

    @DELETE("/usuarios/{id}")
    suspend fun deleteUsuario(@Path("id") id: Int): Response<Void>

    // Idoso
    @POST("idosos")
    suspend fun cadastrarIdoso(@Body idoso: Idoso): Response<Idoso>

    @GET("idosos")
    suspend fun listarIdosos(): Response<List<Idoso>>

    @GET("idosos/{id}")
    suspend fun obterIdosoPorId(@Path("id") id: Int): Response<Idoso>

    @PUT("idosos/{id}")
    suspend fun atualizarIdoso(@Path("id") id: Int, @Body idoso: Idoso): Response<Idoso>

    @DELETE("idosos/{id}")
    suspend fun deletarIdoso(@Path("id") id: Int): Response<Void>

}



data class LoginRequest(
    val email: String,
    val senha: String
)
