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
import com.example.mobileseniorcare.dataclass.TipoUsuario
import com.example.mobileseniorcare.dataclass.usuario.UsuarioCuidador
import com.example.mobileseniorcare.dataclass.usuario.UsuarioTokenDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class SeniorCareViewModel : ViewModel() {

    var endereco = mutableStateOf<CepResponse?>(null)

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

    fun criarAgenda(disponibilidade: Array<Array<Boolean>>) {
        val novaAgenda = Agenda(disponibilidade = disponibilidade)

        Log.d("SeniorCareViewModel", "Nova agenda criada: $novaAgenda")

        usuarioAtual = usuarioAtual.copy(agendas = novaAgenda)
        Log.d("SeniorCareViewModel", "Usuário Atual após atualizar agendas: $usuarioAtual")
        Log.d("CriarAgenda", "Disponibilidade recebida: ${disponibilidade.contentDeepToString()}")

        _agenda.value = novaAgenda

    }

    private val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val dataNascimentoString: String
        get() = usuarioAtual.dtNascimento?.format(dateFormatter) ?: ""

    fun setDataNascimento(data: LocalDate) {
        usuarioAtual = usuarioAtual.copy(dtNascimento = data)
    }

    var erroApi by mutableStateOf(false)

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

               if (usuarioAtual.tipoDeUsuario == TipoUsuario.CUIDADOR) {
                   endpoint = "cuidadores"
               } else if (usuarioAtual.tipoDeUsuario == TipoUsuario.RESPONSAVEL) {
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

