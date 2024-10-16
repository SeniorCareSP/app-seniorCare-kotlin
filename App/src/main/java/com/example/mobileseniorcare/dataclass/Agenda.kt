package com.example.mobileseniorcare.dataclass

import com.example.mobileseniorcare.dataclass.usuario.UsuarioCuidador

data class Agenda(
    // val diaDaSemana: String? = null
    val disponibilidade: Array<Array<Boolean>>,
    val usuario: UsuarioCuidador? = null
)
