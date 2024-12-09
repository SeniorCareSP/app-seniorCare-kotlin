package com.example.mobileseniorcare.api

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileseniorcare.dataclass.Idoso
import kotlinx.coroutines.launch
import retrofit2.Response

class IdosoViewModel : ViewModel() {
    private val api = RetrofitService.getApiWithoutToken() // Ou use getApiSeniorCareToken(token) se for necessário um token

    // Função para cadastrar um idoso
    fun cadastrarIdoso(idoso: Idoso, onSuccess: (Idoso) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response: Response<Idoso> = api.cadastrarIdoso(idoso)
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) }
                } else {
                    onError("Erro ao cadastrar idoso: ${response.message()}")
                }
            } catch (e: Exception) {
                onError("Falha na comunicação: ${e.localizedMessage}")
            }
        }
    }
}
