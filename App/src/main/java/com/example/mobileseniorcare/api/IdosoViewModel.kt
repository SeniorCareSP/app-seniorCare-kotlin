package com.example.mobileseniorcare.api

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileseniorcare.dataclass.Idoso
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class IdosoViewModel(private val token: String? = null) : ViewModel() {

    private val api: ApiSeniorCare = if (token.isNullOrEmpty()) {
        RetrofitService.getApiWithoutToken()
    } else {
        RetrofitService.getApiSeniorCareToken(token)
    }

    val idosos = mutableStateListOf<Idoso>()

    fun cadastrarIdoso(idoso: Idoso, onSuccess: (Idoso) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = api.cadastrarIdoso(idoso)
                if (response.isSuccessful) {
                    response.body()?.let {
                        idosos.add(it)
                        onSuccess(it)
                    }
                } else {
                    onError("xErro ao cadastrar idoso: ${response.message()}")
                }
            } catch (e: Exception) {
                onError("Falha na comunicação: ${e.localizedMessage}")
            }
        }
    }

    fun listarIdosos(onSuccess: (List<Idoso>) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = api.listarIdosos()
                if (response.isSuccessful) {
                    response.body()?.let {
                        idosos.clear()
                        idosos.addAll(it)
                    }
                } else {
                    onError("Erro ao listar idosos: ${response.message()}")
                }
            } catch (e: Exception) {
                onError("Falha na comunicação: ${e.localizedMessage}")
            }
        }
    }

    fun obterIdosoPorId(id: Int, onSuccess: (Idoso) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = api.obterIdosoPorId(id)
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) }
                } else {
                    onError("Erro ao obter idoso: ${response.message()}")
                }
            } catch (e: Exception) {
                onError("Falha na comunicação: ${e.localizedMessage}")
            }
        }
    }

    fun atualizarIdoso(id: Int, idoso: Idoso, onSuccess: (Idoso) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = api.atualizarIdoso(id, idoso)
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) }
                } else {
                    onError("Erro ao atualizar idoso: ${response.message()}")
                }
            } catch (e: Exception) {
                onError("Falha na comunicação: ${e.localizedMessage}")
            }
        }
    }

    fun deletarIdoso(id: Int, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = api.deletarIdoso(id)
                if (response.isSuccessful) {
                    idosos.removeIf { it.idIdoso == id }
                    onSuccess()
                } else {
                    onError("Erro ao deletar idoso: ${response.message()}")
                }
            } catch (e: Exception) {
                onError("Falha na comunicação: ${e.localizedMessage}")
            }
        }
    }
}
