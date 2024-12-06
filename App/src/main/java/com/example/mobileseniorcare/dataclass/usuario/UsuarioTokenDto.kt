package com.example.mobileseniorcare.dataclass.usuario

data class UsuarioTokenDto(
    var userId: Int? = null,
    var nome: String? = null,
    var email: String? = null,
    var tipoUsuario: String? = null,
    var token: String? = null,
    var status: Boolean? = null,
    var imagemUrl: String? = null,
)