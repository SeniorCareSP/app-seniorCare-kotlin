package com.example.mobileseniorcare.dataclass.usuario

import com.example.mobileseniorcare.dataclass.Agenda
import com.example.mobileseniorcare.dataclass.Endereco
import com.example.mobileseniorcare.dataclass.Favorito
import com.example.mobileseniorcare.dataclass.Idioma
import com.example.mobileseniorcare.dataclass.Idoso
import java.time.LocalDate

data class UsuarioResponsavel(
    val nome: String,
    val email: String,
    val senha: String,
    val telefone: String? = null,
    val cpf: String? = null,
    val sexoBiologico: String? = null,
    val tipoDeUsuario: String? = null,
    val dtNascimento: LocalDate? = null,
    val apresentacao: String? = null,
    val dtCadastro: LocalDate? = null,
    val status: Boolean? = null,
    val agendas: Agenda? = null,
    val idiomas: List<Idioma>? = null,
    val favoritos: List<Favorito>? = null,
    val experiencia: String? = null,
    val endereco: Endereco? = null,
    val faixaEtaria: String? = null,
    val idosos: List<Idoso> = emptyList(),
)

