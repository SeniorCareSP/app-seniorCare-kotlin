package com.example.mobileseniorcare.telas

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mobileseniorcare.ui.theme.MobileSeniorCareTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mobileseniorcare.R
import com.example.mobileseniorcare.dataclass.usuario.UsuarioResponse
import com.example.mobileseniorcare.dataclass.usuario.UsuarioTokenDto
import org.koin.android.ext.android.inject

import org.koin.core.component.KoinComponent

import org.koin.java.KoinJavaComponent


class MainActivity2 : ComponentActivity(), KoinComponent{
    private val sessaoToken: UsuarioTokenDto by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileSeniorCareTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Tela(
                        rememberNavController(),
                        modifier = Modifier.padding(innerPadding),
                        sessaoToken
                    )
                }
            }
        }
    }
}


@Composable
fun Tela(navController: NavHostController, modifier: Modifier = Modifier, sessaoToken: UsuarioTokenDto) {
    Scaffold(
        modifier = Modifier
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(16)
            ),
        bottomBar = {
            androidx.compose.material3.BottomAppBar(
                containerColor = Color(0xff077DB0),
                modifier = Modifier,
                actions = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.width(500.dp)
                    ) {
                        val iconMod = Modifier.size(40.dp)
                        IconButton(onClick = {
                            if (sessaoToken.tipoUsuario == "CUIDADOR") {
                                navController.navigate("edicaoPerfilCuidador")
                            } else {
                               navController.navigate("edicaoPerfil")
                            }
                        }) {     Icon(
                            painter = painterResource(R.drawable.edit),
                            contentDescription = "Localized description",
                            modifier = iconMod,
                            Color.White
                        )
                        }
                        IconButton(onClick = {navController.navigate("listagem")}) {
                            Icon(
                                painter = painterResource(R.drawable.home),
                                contentDescription = "Localized description",
                                modifier = iconMod,
                                Color.White
                            )
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(R.drawable.logout),
                                contentDescription = "Localized description",
                                modifier = iconMod,
                                Color.White
                            )
                        }
                    }
                }
            )
        },
    ) { innerPadding ->
        NavHost(
            modifier = modifier.padding(innerPadding),
            navController = navController, // "gerente" de navegação
            startDestination = "listagem" // fragmento inicial
        ) {
            composable("listagem") { // candidato possível para navegação ("fragmento1")
                ListagemUsuarios(sessaoToken)
            }
            composable("edicao") { // candidato possível para navegação ("fragmento2")
               // EdicaoPerfilTela()
            }
            composable("edicaoPerfil") { // Rota que vai abrir a MainActivity2
                EdicaoPerfilTela(navController, sessaoToken)
            }
            composable("edicaoPerfilCuidador") { // Rota que vai abrir a MainActivity2
                val context = LocalContext.current
                LaunchedEffect(Unit) {
                    context.startActivity(Intent(context, EdicaoPerfilCuidador::class.java))
                }
            }
            composable("cadastroveio"){
                CadastroIdosoScreen(navController)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview10() {
    MobileSeniorCareTheme {
        Tela(rememberNavController(), sessaoToken = UsuarioTokenDto())

    }
}