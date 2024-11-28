package com.example.mobileseniorcare.dataclass

import com.example.mobileseniorcare.dataclass.usuario.UsuarioCuidador

data class EnderecoViaCep(
    val logradouro: String?,
    val complemento: String?,
    val bairro: String?,
    val localidade: String?,
    val uf: String?
)

