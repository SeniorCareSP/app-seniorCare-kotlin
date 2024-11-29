package com.example.mobileseniorcare.dataclass.usuario

import com.example.mobileseniorcare.dataclass.Agenda
import com.example.mobileseniorcare.dataclass.Ajuda
import com.example.mobileseniorcare.dataclass.Caracteristica
import com.example.mobileseniorcare.dataclass.Endereco
import com.example.mobileseniorcare.dataclass.Favorito
import com.example.mobileseniorcare.dataclass.Idioma
import com.example.mobileseniorcare.dataclass.Idoso
import com.example.mobileseniorcare.dataclass.TipoUsuario
import java.time.LocalDate

data class UsuarioCuidador(
    var id: Int? = null,
    var nome: String? = "",
    var email: String? = "",
    var senha: String? = "",
    var telefone: String? = null,
    var sexoBiologico: String? = null,
    var tipoDeUsuario: TipoUsuario? = null,
    var dtNascimento: LocalDate? = null,
    var apresentacao: String? = null,
    var dtCadastro: LocalDate? = null,
    var faixaEtaria: String? = null,
    var idosos: List<Idoso> = emptyList(),
    var status: Boolean? = null,
    var agendas: Agenda? = null,
   // var idiomas: List<Idioma>? = null,
    var idiomas: List<Idioma> = mutableListOf(),
    var favoritos: List<Favorito>? = null,
    var experiencia: String? = null,
    var endereco: Endereco? = null,
    var qtdIdoso: Int? = null,
    var precoHora: Double = 0.0,
    var caracteristicas: List<Caracteristica> = emptyList(),
    var ajuda: List<Ajuda> = emptyList()
)

