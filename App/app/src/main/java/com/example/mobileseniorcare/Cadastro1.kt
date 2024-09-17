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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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

class Cadastro1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileSeniorCareTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting2(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") } // Estado separado para o campo de e-mail
    var senha by remember { mutableStateOf("") } // Estado separado para o campo de senha
    var confirmarSenha by remember { mutableStateOf("") } // Estado separado para confirmar senha
    var cep by remember { mutableStateOf("") } // Estado separado para o campo de CEP
    var selectedOption by remember { mutableStateOf("") } // Estado para controle de seleção
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
            Spacer(modifier = Modifier.height(36.dp))
            OutlinedTextField(
                label = { Text("E-mail", color = labelColor) },  // Cor personalizada do label
                value = email,
                onValueChange = { email = it },
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
            Spacer(modifier = Modifier.height(16.dp)) // Espaço entre os inputs
            OutlinedTextField(
                label = { Text("Confirmar senha", color = labelColor) },  // Cor personalizada do label
                value = confirmarSenha,
                onValueChange = { confirmarSenha = it },
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
            Spacer(modifier = Modifier.height(16.dp)) // Espaço entre os inputs
            OutlinedTextField(
                label = { Text("CEP", color = labelColor) },  // Cor personalizada do label
                value = cep,
                onValueChange = { cep = it },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    focusedTextColor = textColor, // Cor do texto quando o campo está focado
                    unfocusedTextColor = textColor  // Cor do texto quando o campo não está focado
                )
            )
            Spacer(modifier = Modifier.height(24.dp)) // Espaço entre o último input e os botões de seleção

            // Linha com botões "Cuidador" e "Responsável"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { selectedOption = "Cuidador" },
                    modifier = Modifier
                        .weight(1f)
                        .border(
                            width = 1.dp,
                            color = if (selectedOption == "Cuidador") Color.White else borderColor,
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)

                        ),
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = if (selectedOption == "Cuidador") buttonBackgroundColor else buttonWhiteBackgroundColor,
                        contentColor = if (selectedOption == "Cuidador") buttonTextColor else buttonWhiteTextColor
                    )
                ) {
                    Text("Cuidador")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = { selectedOption = "Responsável" },
                    modifier = Modifier
                        .weight(1f)
                        .border(
                            width = 1.dp,
                            color = if (selectedOption == "Responsável") Color.White else borderColor,
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
                        ),
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = if (selectedOption == "Responsável") buttonBackgroundColor else buttonWhiteBackgroundColor,
                        contentColor = if (selectedOption == "Responsável") buttonTextColor else buttonWhiteTextColor
                    )
                ) {
                    Text("Responsável")
                }
            }
            Spacer(modifier = Modifier.height(40.dp)) // Espaço entre os botões de seleção e o botão "Próximo"
            Button(
                onClick = { /* Ação de próximo */ },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = buttonBackgroundColor,
                    contentColor = buttonTextColor
                )
            ) {
                Text("Próximo")
            }
           // Texto "Já tenho uma conta" clicável e sublinhado
            Spacer(modifier = Modifier.height(36.dp))
            Text(
                text = "Já tenho uma conta",
                modifier = Modifier
                    .clickable { /* Ação para entrar na conta */ }
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
fun GreetingPreview2() {
    MobileSeniorCareTheme {
        Greeting2("Android")
    }
}
