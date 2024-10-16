package com.example.mobileseniorcare.dataclass

import com.example.mobileseniorcare.dataclass.usuario.UsuarioCuidador

data class Endereco(
    val cep: String? = null,
    val logradouro: String? = null,
    val complemento: String? = null,
    val numero: String? = null,
    val cidade: String? = null,
    val bairro: String? = null,
    val usuario: UsuarioCuidador? = null
)
