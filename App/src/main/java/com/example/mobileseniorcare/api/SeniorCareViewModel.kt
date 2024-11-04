package com.example.mobileseniorcare.api


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileseniorcare.dataclass.usuario.UsuarioCuidador
import kotlinx.coroutines.launch
class SeniorCareViewModel : ViewModel() {

    private val apiSeniorCare: ApiSeniorCare = RetrofitService.getApiSeniorCare()

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
                    Log.i("SeniorCareViewModel", "Login bem-sucedido: ${usuarioLogado.value}")
                } else {
                    errorMessage.value = "Erro ao realizar login: ${response.errorBody()?.string()}"
                    Log.e("SeniorCareViewModel", errorMessage.value!!)
                }
            } catch (e: Exception) {
                errorMessage.value = "Erro de conexão ou outro erro: ${e.message}"
                Log.e("SeniorCareViewModel", "Erro de conexão ou outro erro", e)
            } finally {
                isLoading.value = false
            }
        }
    }

    // Função para criar um novo usuário (ex: responsável)
    fun createUsuario(usuario: UsuarioCuidador) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val response = apiSeniorCare.createUsuario(usuario)

                if (response.isSuccessful) {
                    Log.i("SeniorCareViewModel", "Usuário criado: ${response.body()}")
                } else {
                    errorMessage.value = "Erro ao criar usuário: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                errorMessage.value = "Erro ao criar usuário: ${e.message}"
                Log.e("SeniorCareViewModel", "Erro ao criar usuário", e)
            } finally {
                isLoading.value = false
            }
        }
    }

    // Função para obter todos os usuários
    fun getAllUsuarios() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val response = apiSeniorCare.getAllUsuarios()

                if (response.isSuccessful) {
                    Log.i("SeniorCareViewModel", "Usuários: ${response.body()}")
                } else {
                    errorMessage.value = "Erro ao obter usuários: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                errorMessage.value = "Erro ao obter usuários: ${e.message}"
                Log.e("SeniorCareViewModel", "Erro ao obter usuários", e)
            } finally {
                isLoading.value = false
            }
        }
    }

    // Função para atualizar um usuário
    fun updateUsuario(id: Int, usuario: UsuarioCuidador) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val response = apiSeniorCare.updateUsuario(id, usuario)

                if (response.isSuccessful) {
                    Log.i("SeniorCareViewModel", "Usuário atualizado: ${response.body()}")
                } else {
                    errorMessage.value = "Erro ao atualizar usuário: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                errorMessage.value = "Erro ao atualizar usuário: ${e.message}"
                Log.e("SeniorCareViewModel", "Erro ao atualizar usuário", e)
            } finally {
                isLoading.value = false
            }
        }
    }

    // Função para excluir um usuário
    fun deleteUsuario(id: Int) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val response = apiSeniorCare.deleteUsuario(id)

                if (response.isSuccessful) {
                    Log.i("SeniorCareViewModel", "Usuário excluído com sucesso")
                } else {
                    errorMessage.value = "Erro ao excluir usuário: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                errorMessage.value = "Erro ao excluir usuário: ${e.message}"
                Log.e("SeniorCareViewModel", "Erro ao excluir usuário", e)
            } finally {
                isLoading.value = false
            }
        }
    }
}
