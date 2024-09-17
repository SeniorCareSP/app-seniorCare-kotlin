package com.example.mobileseniorcare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileseniorcare.ui.theme.MobileSeniorCareTheme

class EdicaoPerfil : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileSeniorCareTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting7(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting7(name: String, modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }
    var cep by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf("") }
    val labelColor = Color(0xFF000000)
    val borderColor = Color(0xFF077DB0)
    val buttonBackgroundColor = Color(0xFF077DB0)
    val buttonTextColor = Color.White
    val buttonWhiteTextColor = Color(0xFF077DB0)
    val buttonWhiteBackgroundColor = Color.White
    val textColor = Color.Black
    var text by remember { mutableStateOf("Digite algo aqui...") }

    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF077DB0)) // Fundo azul
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Parte superior azul com logo e texto
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                //    .heightIn(max = 0.35f * LocalConfiguration.current.screenHeightDp.dp), // 35% para a área azul
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top // Logo mais pra cima
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
                    .verticalScroll(rememberScrollState())
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(
                            topEnd = 30.dp,
                            topStart = 30.dp
                        )
                    )
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top // Conteúdo mais para baixo
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.seta), // Substitua pelo seu recurso de imagem
                        contentDescription = "Logo do Mobile Senior Care",
                        modifier = Modifier.size(35.dp) // Ajuste do tamanho da imagem
                    )
                    Text(text = "Voltar", modifier = Modifier.padding(start = 12.dp))
                }
                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(text = "Edição do perfil")
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.senior_care),
                            contentDescription = "Imagem do Chat",
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.Gray, CircleShape)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Nome")
                    OutlinedTextField(
                        label = { Text("E-mail", color = labelColor) },
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = borderColor,
                            unfocusedBorderColor = borderColor,
                            focusedTextColor = textColor,
                            unfocusedTextColor = textColor
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Sobre", fontSize = 16.sp)
                    OutlinedTextField(
                        value = text,
                        onValueChange = { newText -> text = newText },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        maxLines = 5,
                        singleLine = false,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = borderColor,
                            unfocusedBorderColor = borderColor,
                            focusedTextColor = textColor,
                            unfocusedTextColor = textColor
                        )
                    )

                    Text(text = "Logradouro")
                    OutlinedTextField(
                        label = { Text("E-mail", color = labelColor) },
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = borderColor,
                            unfocusedBorderColor = borderColor,
                            focusedTextColor = textColor,
                            unfocusedTextColor = textColor
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(15.dp),
                            modifier = Modifier.weight(0.5f)
                        ) {
                            Text(text = "CEP")
                            OutlinedTextField(
                                label = { Text("E-mail", color = labelColor) },
                                value = cep,
                                onValueChange = { cep = it },
                                modifier = Modifier.fillMaxWidth(),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = borderColor,
                                    unfocusedBorderColor = borderColor,
                                    focusedTextColor = textColor,
                                    unfocusedTextColor = textColor
                                )
                            )

                            Text(text = "Número")
                            OutlinedTextField(
                                label = { Text("Número", color = labelColor) },
                                value = email,
                                onValueChange = { email = it },
                                modifier = Modifier.fillMaxWidth(),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = borderColor,
                                    unfocusedBorderColor = borderColor,
                                    focusedTextColor = textColor,
                                    unfocusedTextColor = textColor
                                )
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(15.dp),
                            modifier = Modifier.weight(0.5f)
                        ) {
                            Text(text = "Bairro")
                            OutlinedTextField(
                                label = { Text("E-mail", color = labelColor) },
                                value = cep,
                                onValueChange = { cep = it },
                                modifier = Modifier.fillMaxWidth(),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = borderColor,
                                    unfocusedBorderColor = borderColor,
                                    focusedTextColor = textColor,
                                    unfocusedTextColor = textColor
                                )
                            )

                            Text(text = "Cidade")
                            OutlinedTextField(
                                label = { Text("Número", color = labelColor) },
                                value = email,
                                onValueChange = { email = it },
                                modifier = Modifier.fillMaxWidth(),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = borderColor,
                                    unfocusedBorderColor = borderColor,
                                    focusedTextColor = textColor,
                                    unfocusedTextColor = textColor
                                )
                            )
                        }


                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(text = "Logradouro")
                    OutlinedTextField(
                        label = { Text("E-mail", color = labelColor) },
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = borderColor,
                            unfocusedBorderColor = borderColor,
                            focusedTextColor = textColor,
                            unfocusedTextColor = textColor
                        )
                    )

                    Row(){

                    }
                    Column(modifier = Modifier
                        .border(1.dp, borderColor, shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)),) {
                        Button(
                            onClick = { /* Ação de voltar */ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
                                .border(1.dp, borderColor, shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)),
                            shape = RoundedCornerShape(10.dp),
                            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                                containerColor = buttonWhiteBackgroundColor,
                                contentColor = buttonWhiteTextColor
                            )
                        ) {
                            Text("Voltar")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview8() {
    MobileSeniorCareTheme {
        Greeting7("Android")
    }
}
