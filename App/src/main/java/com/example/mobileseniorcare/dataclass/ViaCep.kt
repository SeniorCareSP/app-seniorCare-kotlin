package com.example.mobileseniorcare.dataclassx
    import com.example.mobileseniorcare.dataclass.CepResponse
    import retrofit2.Call
    import retrofit2.http.GET
    import retrofit2.http.Path

    interface ViaCep {
        @GET("{cep}/json/")
        fun buscarCep(@Path("cep") cep: String): Call<CepResponse>
    }
