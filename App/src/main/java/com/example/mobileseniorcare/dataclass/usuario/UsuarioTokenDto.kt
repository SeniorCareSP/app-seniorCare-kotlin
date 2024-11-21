package com.example.mobileseniorcare.dataclass.usuario

data class UsuarioTokenDto(
    var userId: Int? = null,
    var nome: String? = null,
    var emaiil: String? = null,
    var tipoUsuario: String? = null,
    var token: String? = null,
    var status: Boolean? = null

)