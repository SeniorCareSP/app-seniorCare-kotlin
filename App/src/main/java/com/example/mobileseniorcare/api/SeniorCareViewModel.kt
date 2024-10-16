package com.example.mobileseniorcare.api


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileseniorcare.dataclass.usuario.UsuarioCuidador
import com.example.mobileseniorcare.dataclass.usuario.UsuarioResponsavel
import kotlinx.coroutines.launch

class SeniorCareViewModel : ViewModel() {

    // Objeto responsável por fazer as requisições HTTP via Retrofit
    private val apiSeniorCare: ApiSeniorCare = RetrofitService.getApiSeniorCare()

    // Estado para armazenar o resultado do login
    var usuarioLogado = mutableStateOf<UsuarioCuidador?>(null)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    var isLoading = mutableStateOf(false)
        private set

    // Função para realizar o login
    fun login(email: String, senha: String) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val loginRequest = LoginRequest(email, senha)
                val response = apiSeniorCare.login(loginRequest)

                if (response.isSuccessful) {
                    usuarioLogado.value = response.body()
                    errorMessage.value = null
                    Log.i("LoginViewModel", "Login bem-sucedido: ${usuarioLogado.value}")
                } else {
                    usuarioLogado.value = null
                    errorMessage.value = "Erro ao realizar login: ${response.errorBody()?.string()}"
                    Log.e("LoginViewModel", errorMessage.value!!)
                }
            } catch (e: Exception) {
                usuarioLogado.value = null
                errorMessage.value = "Erro de conexão ou outro erro: ${e.message}"
                Log.e("LoginViewModel", "Erro de conexão ou outro erro", e)
            } finally {
                isLoading.value = false
            }
        }
    }

    fun cadastroResponsavel(usuarioResponsavel: UsuarioResponsavel) {
        viewModelScope.launch {

        }
    }
}