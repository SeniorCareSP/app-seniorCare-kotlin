package com.example.mobileseniorcare.telas

import ListagemViewModel
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mobileseniorcare.R
import com.example.mobileseniorcare.api.SeniorCareViewModel
import com.example.mobileseniorcare.dataclass.TipoUsuario
import com.example.mobileseniorcare.dataclass.usuario.UsuarioTokenDto
import com.example.mobileseniorcare.ui.theme.MobileSeniorCareTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class Login : ComponentActivity() {

    private val sessaoUsuario: UsuarioTokenDto by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileSeniorCareTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(rememberNavController(),  sessaoUsuario, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}



@Composable
fun LoginScreen(navController: NavHostController,  sessaoUsuario: UsuarioTokenDto, modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    val seniorCareViewModel: SeniorCareViewModel = viewModel()
    val carregar: ListagemViewModel = viewModel()




    // Layout principal
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF077DB0)) // Fundo azul
    ) {
        // Parte superior com logo
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.35f), // 35% para a área azul
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top // Logo mais para cima
        ) {
            Spacer(modifier = Modifier.height(32.dp)) // Espaço para mover a logo mais para cima
            Image(
                painter = painterResource(id = R.drawable.logo_mobile), // Substitua pelo seu recurso de imagem
                contentDescription = "Logo do Mobile Senior Care",
                modifier = Modifier.size(150.dp) // Ajuste do tamanho da imagem
            )
        }

        // Parte inferior branca com campos de login e botões
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)
                )
                .align(Alignment.BottomCenter)
                .padding(20.dp)
                .padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Frase de boas-vindas
            Text(
                text = "Bem-vindo de Volta!",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF077DB0)
                ),
                modifier = Modifier.padding(bottom = 16.dp) // Espaço abaixo do texto
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF077DB0),
                    unfocusedBorderColor = Color(0xFF077DB0)
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = senha,
                onValueChange = { senha = it },
                label = { Text(text = "Senha") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF077DB0),
                    unfocusedBorderColor = Color(0xFF077DB0)
                )
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Botão de Login
            Button(
                onClick = {

                    CoroutineScope(Dispatchers.IO).launch {
                        seniorCareViewModel.login(email, senha)
                        seniorCareViewModel.usuarioLogado.value?.let {
                            navController.navigate("mainActivity2")
                            { // Navegação para MainActivity2
                                popUpTo("login") { inclusive = true }
                            }
                        } ?: run {
                            navController.context?.let { context ->
                                Toast.makeText(context, "Falha no login. Verifique suas credenciais.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                },
                modifier = Modifier
                    .width(200.dp) // Largura do botão de login
                    .height(40.dp),
                content = {
                    Text(text = "Entrar", color = Color.White)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF077DB0))
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Botão de Voltar com contorno azul
            Button(
                onClick = { navController.popBackStack() }, // Volta para a tela anterior
                modifier = Modifier
                    .width(200.dp) // Largura do botão de voltar
                    .height(40.dp)
                    .border(1.dp, Color(0xFF077DB0), RoundedCornerShape(8.dp)), // Contorno azul
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent) // Fundo transparente
            ) {
                Text(text = "Voltar", color = Color(0xFF077DB0) /* Cor do texto azul */)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MobileSeniorCareTheme {
        LoginScreen(rememberNavController(), sessaoUsuario = UsuarioTokenDto())
    }
}
