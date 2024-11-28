package com.example.mobileseniorcare.api
    import com.example.mobileseniorcare.dataclass.CepResponse
    import com.example.mobileseniorcare.dataclass.Endereco
    import com.example.mobileseniorcare.dataclass.EnderecoViaCep
    import retrofit2.Call
    import retrofit2.http.GET
    import retrofit2.http.Path

    interface ViaCep {
        @GET("{cep}/json/")
        suspend fun buscarCep(@Path("cep") cep: String): EnderecoViaCep
    }
