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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListagemViewModel : ViewModel() {

    private val api: ApiSeniorCare = RetrofitService.getApiWithoutToken()

    private val usuariosCuidadores = mutableStateListOf<UsuarioCuidador>()

    private val usuariosResponsaveis = mutableStateListOf<UsuarioCuidador>()

    var usuario by mutableStateOf(UsuarioCuidador())

    // usaremos este atributo para indicar se a lista está atualizada
    var listaAtualizada by mutableStateOf(true)

    // Lista filtrada
    var usuariosFiltrados by mutableStateOf<List<UsuarioCuidador>>(emptyList())

    // Busca lista de usuários e aplica filtro por tipo

    fun getListaCuidador(tipoUsuario: TipoUsuario?): List<UsuarioCuidador> {
        GlobalScope.launch {
            try {
                val resposta = api.getCuidadores() // Fazendo a requisição GET para a API
                if (resposta.isSuccessful) {
                    Log.i("api", "Items da API: ${resposta.body()}")
                    usuariosCuidadores.clear()
                      val listaCuidadores = resposta.body()?.filter {
                            it.tipoDeUsuario == TipoUsuario.CUIDADOR
                      } ?: emptyList()
                    usuariosCuidadores.addAll(listaCuidadores)
                    Log.i("api", "Lista de cuidadores carregada com sucesso: $usuariosCuidadores")
                } else {
                    Log.e("api", "Erro ao buscar itens: ${resposta.errorBody()?.string()}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao buscar itens", exception)
            }
        }
        return usuariosCuidadores.toList()
    }


    fun getListaResponsavel(tipoUsuario: TipoUsuario?): List<UsuarioCuidador> {
        GlobalScope.launch {
            try {
                val resposta = api.getResponsavel() // Fazendo a requisição GET para a API
                if (resposta.isSuccessful) {
                    Log.i("api", "Items da API: ${resposta.body()}")
                    usuariosResponsaveis.clear()
                    val listaResponsaveis = resposta.body()?.filter {
                        it.tipoDeUsuario == TipoUsuario.RESPONSAVEL
                    } ?: emptyList()
                    usuariosResponsaveis.addAll(listaResponsaveis)
                    Log.i("api", "Lista de responsaveis carregada com sucesso: $usuariosResponsaveis")
                } else {
                    Log.e("api", "Erro ao buscar itens: ${resposta.errorBody()?.string()}")
                }
            } catch (exception: Exception) {
                Log.e("api", "Erro ao buscar itens", exception)
            }
        }
        return usuariosResponsaveis.toList()
    }

}
