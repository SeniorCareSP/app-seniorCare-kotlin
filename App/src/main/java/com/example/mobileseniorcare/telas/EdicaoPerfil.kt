package com.example.mobileseniorcare.telas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobileseniorcare.R
import com.example.mobileseniorcare.api.SeniorCareViewModel
import com.example.mobileseniorcare.ui.theme.MobileSeniorCareTheme

class EdicaoPerfil : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val viewModel: SeniorCareViewModel = viewModel()

            MobileSeniorCareTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EdicaoPerfilScreen(rememberNavController(), modifier = Modifier.padding(innerPadding))
//                    NavHost(
//                        navController = navController,
//                        startDestination = "main",
//                        modifier = Modifier.padding(innerPadding)
//                    ) {
//                        composable("novoIdoso") {
//                            CadastroIdosoScreen(navController, viewModel)
//                        }
//                    }
                }
            }
        }
    }
}

@Composable
fun EdicaoPerfilScreen( navController: NavHostController, modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var textoSobre by remember { mutableStateOf("") }
    var logradouro by remember { mutableStateOf("") }
    var cep by remember { mutableStateOf("") }
    var bairro by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }
    var cidade by remember { mutableStateOf("") }

    val (selectedOptions, setSelectedOptions) = remember { mutableStateOf(setOf<String>()) }

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

            // Box com fundo branco e bordas arredondadas para o conteúdo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp))
                    .padding(20.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
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

                    Text(
                        text = "Idosos Cadastrados:",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF077DB0),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Left,
                        fontSize = 18.sp,
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .border(1.dp, Color(0xFF077DB0), RoundedCornerShape(10.dp))
                            .background(Color.White, RoundedCornerShape(10.dp))
                    ) {
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
            }

            Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        Log.d("Navigation", "Navigating to cadastroveio")
                        navController.navigate("cadastroveio")
                    },

                        modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .border(1.dp, borderColor, shape = RoundedCornerShape(10.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = buttonBackgroundColor)
            ) {
                Text(stringResource(R.string.botao_novo_idoso))
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Preciso de ajuda com:",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp),
                color = Color.White,
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Centralizar as opções e organizá-las em duas linhas
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp) // Aumentei o espaço entre as opções
            ) {
                val options = listOf("Trabalho de Casa", "Culinária", "Banho", "Outros")

                options.chunked(2).forEach { rowOptions ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        rowOptions.forEach { option ->
                            Chip(
                                text = option,
                                isSelected = selectedOptions.contains(option),
                                onClick = {
                                    if (selectedOptions.contains(option)) {
                                        setSelectedOptions(selectedOptions - option) // Remove a opção
                                    } else {
                                        setSelectedOptions(selectedOptions + option) // Adiciona a opção
                                    }
                                }
                            )
                            Spacer(modifier = Modifier.width(16.dp)) // Adicionando espaçamento entre os chips
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Informe o valor que está disposto a pagar",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp),
                color = Color.White,
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
            )

            Spacer(modifier = Modifier.height(16.dp))

            var valorHora by remember { mutableStateOf("") }

            TextField(
                value = valorHora,
                onValueChange = { valorHora = it },
                label = { Text("Valor por hora") },
                leadingIcon = {
                    Text(
                        text = "R$",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray) // Exibindo "R$" no campo
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp) // Espaço ao redor do TextField
            )
            Button(
                onClick = { /* Ação para salvar alterações */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .border(1.dp, borderColor, shape = RoundedCornerShape(10.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = buttonBackgroundColor)
            ) {
                Text(stringResource(R.string.botao_salvar_alteracoes))
            }
        }
    }
}

@Composable
fun Chip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) Color(0xFF077DB0) else Color(0xFFE0E0E0)
    val textColor = if (isSelected) Color.White else Color.Black

    Surface(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() },
        color = backgroundColor,
        contentColor = textColor,
        tonalElevation = 2.dp
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
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
    email: String, onEmailChange: (String) -> Unit,
    textoSobre: String, onTextoSobreChange: (String) -> Unit,
    logradouro: String, onLogradouroChange: (String) -> Unit,
    cep: String, onCepChange: (String) -> Unit,
    bairro: String, onBairroChange: (String) -> Unit,
    numero: String, onNumeroChange: (String) -> Unit,
    cidade: String, onCidadeChange: (String) -> Unit
) {
    TextField(
        value = email,
        onValueChange = onEmailChange,
        label = { Text("Email") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    TextField(
        value = textoSobre,
        onValueChange = onTextoSobreChange,
        label = { Text("Sobre") },
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    )

    Spacer(modifier = Modifier.height(8.dp))

    TextField(
        value = logradouro,
        onValueChange = onLogradouroChange,
        label = { Text("Logradouro") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    TextField(
        value = cep,
        onValueChange = onCepChange,
        label = { Text("CEP") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    TextField(
        value = bairro,
        onValueChange = onBairroChange,
        label = { Text("Bairro") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    TextField(
        value = numero,
        onValueChange = onNumeroChange,
        label = { Text("Número") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    TextField(
        value = cidade,
        onValueChange = onCidadeChange,
        label = { Text("Cidade") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun EdicaoPerfilPreview() {
    MobileSeniorCareTheme {
        EdicaoPerfilScreen(rememberNavController())
    }
}
