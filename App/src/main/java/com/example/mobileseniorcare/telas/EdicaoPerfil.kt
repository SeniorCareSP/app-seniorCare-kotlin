package com.example.mobileseniorcare.telas

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileseniorcare.R
import com.example.mobileseniorcare.ui.theme.MobileSeniorCareTheme

class EdicaoPerfil : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileSeniorCareTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EdicaoPerfilScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun EdicaoPerfilScreen(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var textoSobre by remember { mutableStateOf("Sobre") }
    var logradouro by remember { mutableStateOf("") }
    var cep by remember { mutableStateOf("") }
    var bairro by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }
    var cidade by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()
    val borderColor = Color(0xFF077DB0)
    val buttonBackgroundColor = Color(0xFF077DB0)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = buttonBackgroundColor)
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)) {
            TopSection()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp))
                    .padding(20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                BackButton()
                Spacer(modifier = Modifier.height(20.dp))
                HeaderText(stringResource(id = R.string.edicao_perfil))

                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    UserProfileImage()
                }

                Spacer(modifier = Modifier.height(16.dp))

                InputFields(
                    email = email,
                    onEmailChange = { email = it },
                    textoSobre = textoSobre,
                    onTextoSobreChange = { textoSobre = it },
                    logradouro = logradouro,
                    onLogradouroChange = { logradouro = it },
                    cep = cep,
                    onCepChange = { cep = it },
                    bairro = bairro,
                    onBairroChange = { bairro = it },
                    numero = numero,
                    onNumeroChange = { numero = it },
                    cidade = cidade,
                    onCidadeChange = { cidade = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .border(1.dp, Color(0xFF077DB0), RoundedCornerShape(10.dp))
                        .background(Color.White, RoundedCornerShape(10.dp))
                ) {
                    Text("Este é o retângulo", color = Color.Black)
                    data class Idoso(val nome: String, val idade: Int)
                    var idosos by remember { mutableStateOf(listOf<Idoso>()) }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .border(1.dp, Color(0xFF077DB0), RoundedCornerShape(10.dp))
                            .background(Color.White, RoundedCornerShape(10.dp))
                            .padding(16.dp)
                    ) {
                        Column {
                            idosos.forEach { idoso ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = "${idoso.nome}, ${idoso.idade} anos", modifier = Modifier.weight(1f))

                                    IconButton(onClick = { /* Ação para editar */ }) {
                                        Icon(
                                            imageVector = Icons.Filled.Edit, // Ícone de lápis
                                            contentDescription = "Editar"
                                        )
                                    }

                                    IconButton(onClick = { /* Ação para excluir */ }) {
                                        Icon(
                                            imageVector = Icons.Filled.Delete, // Ícone de lixeira
                                            contentDescription = "Excluir"
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp)) // Espaço entre os itens
                            }
                        }

                    }
                }
            }

            Button(
                onClick = { /* Ação para salvar alterações */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .border(1.dp, borderColor, shape = RoundedCornerShape(10.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = buttonBackgroundColor)
            ) {
                Text("Salvar Alterações")
            }
        }
    }
}


@Composable
fun TopSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            painter = painterResource(id = R.drawable.logo_mobile),
            contentDescription = stringResource(id = R.string.slogan_inicial),
            modifier = Modifier.size(150.dp)
        )
    }
}

@Composable
fun BackButton() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        Image(
            painter = painterResource(id = R.drawable.seta),
            contentDescription = stringResource(id = R.string.button_voltar_perfil),
            modifier = Modifier.size(35.dp)
        )
        Text(text = stringResource(id = R.string.button_voltar_perfil), modifier = Modifier.padding(start = 12.dp))
    }
}

@Composable
fun HeaderText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp), // Reduzindo o tamanho da fonte
        color = Color(0xFF077DB0),
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun UserProfileImage() {
    Image(
        painter = painterResource(id = R.drawable.senior_care),
        contentDescription = stringResource(id = R.string.imagem_perfil),
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .border(2.dp, Color.Gray, CircleShape)
    )
}

@Composable
fun InputFields(
    email: String,
    onEmailChange: (String) -> Unit,
    textoSobre: String,
    onTextoSobreChange: (String) -> Unit,
    logradouro: String,
    onLogradouroChange: (String) -> Unit,
    cep: String,
    onCepChange: (String) -> Unit,
    bairro: String,
    onBairroChange: (String) -> Unit,
    numero: String,
    onNumeroChange: (String) -> Unit,
    cidade: String,
    onCidadeChange: (String) -> Unit
) {
    OutlinedTextField(
        label = { Text(stringResource(id = R.string.nome), color = Color.Black) },
        value = email,
        onValueChange = onEmailChange,
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF077DB0),
            unfocusedBorderColor = Color(0xFF077DB0),
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        )
    )

    Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(
        value = textoSobre,
        onValueChange = onTextoSobreChange,
        modifier = Modifier.fillMaxWidth().height(100.dp),
        maxLines = 5,
        singleLine = false,
        placeholder = { Text("Digite algo aqui...") },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF077DB0),
            unfocusedBorderColor = Color(0xFF077DB0),
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        )
    )

    Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(
        label = { Text("Logradouro", color = Color.Black) },
        value = logradouro,
        onValueChange = onLogradouroChange,
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF077DB0),
            unfocusedBorderColor = Color(0xFF077DB0),
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        )
    )

    Spacer(modifier = Modifier.height(16.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(
            label = { Text("CEP", color = Color.Black) },
            value = cep,
            onValueChange = onCepChange,
            modifier = Modifier.weight(1f).padding(end = 8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF077DB0),
                unfocusedBorderColor = Color(0xFF077DB0),
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )

        OutlinedTextField(
            label = { Text("Bairro", color = Color.Black) },
            value = bairro,
            onValueChange = onBairroChange,
            modifier = Modifier.weight(1f).padding(start = 8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF077DB0),
                unfocusedBorderColor = Color(0xFF077DB0),
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(
            label = { Text("Número", color = Color.Black) },
            value = numero,
            onValueChange = onNumeroChange,
            modifier = Modifier.weight(1f).padding(end = 8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF077DB0),
                unfocusedBorderColor = Color(0xFF077DB0),
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )

        OutlinedTextField(
            label = { Text("Cidade", color = Color.Black) },
            value = cidade,
            onValueChange = onCidadeChange,
            modifier = Modifier.weight(1f).padding(start = 8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF077DB0),
                unfocusedBorderColor = Color(0xFF077DB0),
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )
    }

}

@Preview(showBackground = true)
@Composable
fun EdicaoPerfilPreview() {
    MobileSeniorCareTheme {
        EdicaoPerfilScreen()
    }
}
