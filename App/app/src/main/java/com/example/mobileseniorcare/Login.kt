package com.example.mobileseniorcare
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobileseniorcare.ui.theme.MobileSeniorCareTheme


class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileSeniorCareTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var login by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    val labelColor = Color(0xFF000000)
    val borderColor = Color(0xFF077DB0)
    val buttonBackgroundColor = Color(0xFF077DB0)
    val buttonTextColor = Color.White
    val buttonWhiteTextColor = Color(0xFF077DB0)
    val buttonWhiteBackgroundColor = Color.White
    val textColor = Color.Black // Cor do texto dos campos de entrada

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF077DB0)) // Fundo azul
    ) {

        // Parte superior azul com logo e texto
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.35f), // 35% para a área azul
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top // Logo mais pra cima
        ) {
            Spacer(modifier = Modifier.height(32.dp)) // Espaço para mover a logo mais para cima
            Image(
                painter = painterResource(id = R.drawable.logo_mobile), // Substitua pelo seu recurso de imagem
                contentDescription = "Logo do Mobile Senior Care",
                modifier = Modifier.size(150.dp), // Ajuste do tamanho da imagem
            )
        }

        // Parte inferior branca com campos de login e botões
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f) // A parte branca ocupará 75% da tela
                .background(
                    color = Color.White,
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(
                        topEnd = 30.dp,
                        topStart = 30.dp
                    )
                )
                .align(Alignment.BottomCenter)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top // Conteúdo mais para baixo
        ) {
            Spacer(modifier = Modifier.height(90.dp))
            OutlinedTextField(
                label = { Text("E-mail", color = labelColor) },  // Cor personalizada do label
                value = login,
                onValueChange = { login = it },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    focusedTextColor = textColor, // Cor do texto quando o campo está focado
                    unfocusedTextColor = textColor  // Cor do texto quando o campo não está focado
                )

            )
            Spacer(modifier = Modifier.height(16.dp)) // Espaço entre os inputs
            OutlinedTextField(
                label = { Text("Senha", color = labelColor) },  // Cor personalizada do label
                value = senha,
                onValueChange = { senha = it },
                modifier = Modifier
                    .fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),  // Esconde a senha
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    focusedTextColor = textColor, // Cor do texto quando o campo está focado
                    unfocusedTextColor = textColor  // Cor do texto quando o campo não está focado
                )
            )
            Spacer(modifier = Modifier.height(40.dp)) // Espaço entre o segundo input e o botão de login
            Button(
                onClick = { /* Ação de login */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(androidx.compose.foundation.shape.RoundedCornerShape(8.dp)), // Bordas arredondadas
                colors = androidx.compose.material3.ButtonDefaults.buttonColors( // Cor personalizada do botão
                    containerColor = buttonBackgroundColor,
                    contentColor = buttonTextColor
                )
            ) {
                Text("Login")
            }
            Spacer(modifier = Modifier.height(16.dp)) // Espaço entre os botões
            // Botão Voltar
            Button(
                onClick = { /* Ação de voltar */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(androidx.compose.foundation.shape.RoundedCornerShape(8.dp)) // Bordas arredondadas
                    .border(1.dp, borderColor, shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors( // Cor personalizada do botão "Voltar"
                    containerColor = buttonWhiteBackgroundColor,
                    contentColor = buttonWhiteTextColor
                )
            ) {
                Text("Voltar")
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Texto "Não tenho uma conta" clicável e sublinhado
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Não tenho uma conta",
                modifier = Modifier
                    .clickable { /* Ação para criar conta */ }
                    .padding(top = 16.dp),
                color = labelColor,
                textAlign = TextAlign.Center,
                style = TextStyle(textDecoration = TextDecoration.Underline) // Texto sublinhado
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MobileSeniorCareTheme {
        Greeting("Android")
    }
}
