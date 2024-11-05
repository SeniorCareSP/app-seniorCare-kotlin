package com.example.mobileseniorcare.di
import java.time.LocalDateTime

/*
Classe que usaremos para ter a "sessão" do usuário
Outros atributos que ela poderia ter: email, URL da foto, perfil, id da empresa etc
 */
data class SessaoUsuario(
    var login: String = "",
    var nome: String = "",
    var token: String = "",
    var dataHoraLogin: LocalDateTime? = null
)
