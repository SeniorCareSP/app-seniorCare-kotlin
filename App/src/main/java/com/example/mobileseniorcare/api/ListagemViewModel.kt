import android.content.Intent
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mobileseniorcare.api.ApiSeniorCare
import com.example.mobileseniorcare.api.RetrofitService
import com.example.mobileseniorcare.dataclass.TipoUsuario
import com.example.mobileseniorcare.dataclass.usuario.UsuarioCuidador
import com.example.mobileseniorcare.dataclass.usuario.UsuarioResponse
import com.example.mobileseniorcare.dataclass.usuario.UsuarioTokenDto
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent

class ListagemViewModel() : ViewModel(), KoinComponent {

    //  private val api: ApiSeniorCare = RetrofitService.getApiSeniorCareToken(sessaoUsuario.token)

    private val  api: ApiSeniorCare by KoinJavaComponent.inject(ApiSeniorCare::class.java)

    private val sessaoUsuario: UsuarioTokenDto by inject()

    private val usuariosCuidadores = mutableStateListOf<UsuarioCuidador>()

    private val usuariosResponsaveis = mutableStateListOf<UsuarioCuidador>()

    var usuario by mutableStateOf(UsuarioCuidador())

    // usaremos este atributo para indicar se a lista está atualizada
    var listaAtualizada by mutableStateOf(true)

    // Lista filtrada
    var usuariosFiltrados by mutableStateOf<List<UsuarioCuidador>>(emptyList())

    // Busca lista de usuários e aplica filtro por tipo

//    fun getListaCuidador(tipoUsuario: TipoUsuario?): List<UsuarioCuidador> {
//        GlobalScope.launch {
//            try {
//                val resposta = api.getCuidadores() // Fazendo a requisição GET para a API
//                if (resposta.isSuccessful) {
//                    Log.i("api", "Items da API: ${resposta.body()}")
//                    usuariosCuidadores.clear()
//                    val listaCuidadores = resposta.body()?.filter {
//                        it.tipoDeUsuario == TipoUsuario.CUIDADOR
//                    } ?: emptyList()
//                    usuariosCuidadores.addAll(listaCuidadores)
//                    Log.i("api", "Lista de cuidadores carregada com sucesso: $usuariosCuidadores")
//                } else {
//                    Log.e("api", "Erro ao buscar itens: ${resposta.errorBody()?.string()}")
//                }
//            } catch (exception: Exception) {
//                Log.e("api", "Erro ao buscar itens", exception)
//            }
//        }
//        return usuariosCuidadores.toList()
//    }
//
//
//    fun getListaResponsavel(tipoUsuario: TipoUsuario?): List<UsuarioCuidador> {
//        GlobalScope.launch {
//            try {
//                val resposta = api.getResponsavel() // Fazendo a requisição GET para a API
//                if (resposta.isSuccessful) {
//                    Log.i("api", "Items da API: ${resposta.body()}")
//                    usuariosResponsaveis.clear()
//                    val listaResponsaveis = resposta.body()?.filter {
//                        it.tipoDeUsuario == TipoUsuario.RESPONSAVEL
//                    } ?: emptyList()
//                    usuariosResponsaveis.addAll(listaResponsaveis)
//                    Log.i("api", "Lista de responsaveis carregada com sucesso: $usuariosResponsaveis")
//                } else {
//                    Log.e("api", "Erro ao buscar itens: ${resposta.errorBody()?.string()}")
//                }
//            } catch (exception: Exception) {
//                Log.e("api", "Erro ao buscar itens", exception)
//            }
//        }
//        return usuariosResponsaveis.toList()
//    }

    var tipoUsuarioLogado by mutableStateOf<TipoUsuario?>(null)
        private set
    var usuariosExibidos by mutableStateOf<List<UsuarioResponse>>(emptyList())



    fun carregarUsuarios(tipoUsuario: TipoUsuario) {
        val userId = sessaoUsuario.userId ?: throw IllegalArgumentException("User ID não pode ser null")
        GlobalScope.launch {
            try {
                val resposta = if (tipoUsuario == TipoUsuario.RESPONSAVEL) {
                    api.getResponsavel(userId)
                } else {
                    api.getCuidadores(userId)
                }

                if (resposta.isSuccessful) {
                    val usuarios = resposta.body()
                    usuariosExibidos = resposta.body() ?: emptyList()
                } else {
                    Log.e("ListagemViewModel", "Erro: ${resposta.errorBody()?.string()}")
                }
            } catch (exception: Exception) {
                Log.e("ListagemViewModel", "Erro ao carregar usuários", exception)
            }
        }
    }

}





