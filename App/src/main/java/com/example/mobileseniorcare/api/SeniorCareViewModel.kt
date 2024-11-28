package com.example.mobileseniorcare.api


import android.util.Log
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileseniorcare.dataclass.Agenda
import com.example.mobileseniorcare.dataclass.CepResponse
import com.example.mobileseniorcare.dataclass.Endereco
import com.example.mobileseniorcare.dataclass.usuario.UsuarioCuidador
import com.example.mobileseniorcare.dataclass.usuario.UsuarioTokenDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class SeniorCareViewModel : ViewModel() {

    var endereco = mutableStateOf<CepResponse?>(null)

    //    private val apiSeniorCare: ApiSeniorCare = RetrofitService.getApiSeniorCare()
    private val api: ApiSeniorCare = RetrofitService.getApiWithoutToken();
    var usuarioLogado = mutableStateOf<UsuarioTokenDto?>(null)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    var isLoading = mutableStateOf(false)
        private set

    var usuarioAtual by mutableStateOf(UsuarioCuidador(endereco = Endereco()))

    var usuarioAtualizacao by mutableStateOf<UsuarioCuidador?>(null)


    var usuarioAgenda by mutableStateOf<UsuarioCuidador?>(null)
        private set

    private val _agenda = MutableLiveData<Agenda?>()
    val agenda: LiveData<Agenda?> get() = _agenda

    fun criarAgenda(disponibilidade: Array<Array<Boolean>>, usuario: UsuarioCuidador) {
        val novaAgenda = Agenda(disponibilidade = disponibilidade, usuario = usuario)

        usuarioAtual = usuarioAtual.copy(agendas = novaAgenda)

        // Adicione logs para verificar se a agenda foi atualizada corretamente
        Log.d("SeniorCareViewModel", "Nova agenda criada: $novaAgenda")
        Log.d("SeniorCareViewModel", "Usuário Atual após atualizar agendas: $usuarioAtual")

        // Atualize o LiveData para refletir as mudanças
        _agenda.value = novaAgenda
    }


    private val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val dataNascimentoString: String
        get() = usuarioAtual.dtNascimento?.format(dateFormatter) ?: ""

    fun setDataNascimento(data: LocalDate) {
        usuarioAtual = usuarioAtual.copy(dtNascimento = data)
    }

    var erroApi by mutableStateOf(false)


    // Função para realizar o login
    suspend fun login(email: String, senha: String) {
        isLoading.value = true
        try {
            val loginRequest = LoginRequest(email, senha)
            val response = api.login(loginRequest)

            if (response.isSuccessful) {
                usuarioLogado.value = response.body()
                errorMessage.value = null
                Log.i("SeniorCareViewModel", "Login bem-sucedido: ${usuarioLogado.value}")
            } else {
                usuarioLogado.value = null
                errorMessage.value = "Erro ao realizar login: ${response.errorBody()?.string()}"
                Log.e("SeniorCareViewModel", errorMessage.value!!)
            }
        } catch (e: Exception) {
            usuarioLogado.value = null
            errorMessage.value = "Erro de conexão ou outro erro: ${e.message}"
            Log.e("SeniorCareViewModel", "Erro de conexão ou outro erro", e)
        } finally {
            isLoading.value = false
        }
    }



    fun salvar() {
        GlobalScope.launch {
           try {
               // Define o endpoint com base no tipo de usuário
               val endpoint: String
               Log.d("SeniorCareViewModel", "Usuário Atual antes de salvar: $usuarioAtual")

               if (usuarioAtual.tipoDeUsuario.equals("CUIDADOR", ignoreCase = true)) {
                   endpoint = "cuidadores"
               } else if (usuarioAtual.tipoDeUsuario.equals("RESPONSAVEL", ignoreCase = true)) {
                   endpoint = "responsaveis"
               } else {
                   // Caso o tipo de usuário seja inválido, exibe uma mensagem de erro e para a execução
                   errorMessage.value = "Tipo de usuário inválido"
                   return@launch // Interrompe o bloco de execução atual
               }

                var resposta = api.createUsuario(endpoint,usuarioAtual)
                if (resposta.isSuccessful) {
                    // usuarioAtual = filmes.last() // atualizando o filmeAtual para que ele tenha um id que veio da API
                    Log.i("SeniorCareViewModel", "Usuário criado: ${resposta.body()}")
                    erroApi = false
                } else {
                    Log.e("api", "Erro ao cadastrar usuário: ${resposta.errorBody()?.string()}")
                    Log.e("User", "Usuário: ${usuarioAtual.toString()}")

                    erroApi = true
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao cadastrar/atualizar filme", exception)
                erroApi = true
            }
            // filmeEmAtualizacao = null
        }
    }
}
//fun atualizar(){
//    GlobalScope.launch {
//        try {
//            //filmeEmAtualizacao = filmeAtual
//                var resposta = api.updateUsuario(usuarioAtual.id!!, usuarioAtual)
//                if (resposta.isSuccessful) {
//               //     filmes[filmes.indexOfFirst { it.id == filmeAtual.id }] = resposta.body()!! // atualizando a lista com o corpo da resposta
//                    erroApi = false
//                } else {
//                    Log.e("api", "Erro ao atualizar o usuário: ${resposta.errorBody()?.string()}")
//                    erroApi = true
//                }
//
//        } catch (exception: Exception) {
//            Log.e("api", "Erro ao atualizar o usuário", exception)
//            erroApi = true
//        }
//
//    }
//}



    // Função para criar um novo usuário (ex: responsável)
//    fun createUsuario(usuario: UsuarioCuidador) {
//        viewModelScope.launch {
//            isLoading.value = true
//            try {
//                val response = api.createUsuario(usuario)
//
//                if (response.isSuccessful) {
//                    Log.i("SeniorCareViewModel", "Usuário criado: ${response.body()}")
//                } else {
//                    errorMessage.value = "Erro ao criar usuário: ${response.errorBody()?.string()}"
//                }
//            } catch (e: Exception) {
//                errorMessage.value = "Erro ao criar usuário: ${e.message}"
//                Log.e("SeniorCareViewModel", "Erro ao criar usuário", e)
//            } finally {
//                isLoading.value = false
//            }
//        }
//    }

//    // Função para obter todos os usuários
//    fun getAllUsuarios() {
//        viewModelScope.launch {
//            isLoading.value = true
//            try {
//                val response = api.getAllUsuarios()
//
//                if (response.isSuccessful) {
//                    Log.i("SeniorCareViewModel", "Usuários: ${response.body()}")
//                } else {
//                    errorMessage.value = "Erro ao obter usuários: ${response.errorBody()?.string()}"
//                }
//            } catch (e: Exception) {
//                errorMessage.value = "Erro ao obter usuários: ${e.message}"
//                Log.e("SeniorCareViewModel", "Erro ao obter usuários", e)
//            } finally {
//                isLoading.value = false
//            }
//        }
//    }
//
//    // Função para atualizar um usuário
//    fun updateUsuario(id: Int, usuario: UsuarioCuidador) {
//        viewModelScope.launch {
//            isLoading.value = true
//            try {
//                val response = api.updateUsuario(id, usuario)
//
//                if (response.isSuccessful) {
//                    Log.i("SeniorCareViewModel", "Usuário atualizado: ${response.body()}")
//                } else {
//                    errorMessage.value = "Erro ao atualizar usuário: ${response.errorBody()?.string()}"
//                }
//            } catch (e: Exception) {
//                errorMessage.value = "Erro ao atualizar usuário: ${e.message}"
//                Log.e("SeniorCareViewModel", "Erro ao atualizar usuário", e)
//            } finally {
//                isLoading.value = false
//            }
//        }
//    }

    // Função para excluir um usuário
//    fun deleteUsuario(id: Int) {
//        viewModelScope.launch {
//            isLoading.value = true
//            try {
//                val response = api.deleteUsuario(id)
//
//                if (response.isSuccessful) {
//                    Log.i("SeniorCareViewModel", "Usuário excluído com sucesso")
//                } else {
//                    errorMessage.value = "Erro ao excluir usuário: ${response.errorBody()?.string()}"
//                }
//            } catch (e: Exception) {
//                errorMessage.value = "Erro ao excluir usuário: ${e.message}"
//                Log.e("SeniorCareViewModel", "Erro ao excluir usuário", e)
//            } finally {
//                isLoading.value = false
//            }
//        }
//    }
//}
