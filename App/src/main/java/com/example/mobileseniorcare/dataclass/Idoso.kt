package com.example.mobileseniorcare.dataclass

import java.time.LocalDate

data class Idoso(
    var idIdoso: Int?,
    var idade: Int?,
    var nome: String,
    var descricao: String,
    var mobilidade: Boolean,
    var lucido: Boolean,
    var doencasCronicas: String?,
    var cuidadosMin: Boolean?,
    var dtNascimento: LocalDate?,
    var genero: String?,
    var sobre: String?,
    val responsavel: Int?,  // ID do responsável
    val dtNasc: String  // Data de nascimento no formato "yyyy-MM-dd"
)
