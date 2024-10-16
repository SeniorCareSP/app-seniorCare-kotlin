package com.example.mobileseniorcare.dataclass

import java.time.LocalDate

data class Idoso(
    var nome: String,
    var descricao: String,
    var mobilidade: Boolean,
    var lucido: Boolean,
    var doencasCronicas: String?,
    var cuidadosMin: Boolean?,
    var dtNascimento: LocalDate?,
    var genero: String?
)
