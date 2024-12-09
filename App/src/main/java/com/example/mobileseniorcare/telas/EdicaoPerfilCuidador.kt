package com.example.mobileseniorcare.telas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mobileseniorcare.R
import com.example.mobileseniorcare.ui.theme.MobileSeniorCareTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.mobileseniorcare.api.SeniorCareViewModel

class EdicaoPerfilCuidador : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val viewModel: SeniorCareViewModel = viewModel()

            MobileSeniorCareTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EdicaoPerfilCuidadorScreen(
                        rememberNavController(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun EdicaoPerfilCuidadorScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    var nome by remember { mutableStateOf("") }
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            TopSectionCuidador()

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)
                    )
                    .padding(20.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    BackButtons()
                    Spacer(modifier = Modifier.height(20.dp))
                    HeaderTexts(stringResource(id = R.string.edicao_perfil))

                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        UserProfileImages()
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    InputFieldsCuidador(
                        nome = nome,
                        onNomeChange = { nome = it },
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
                        text = "Posso ajudar com:",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 18.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Left,
                        fontSize = 16.sp,
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        val options = listOf("Trabalho de Casa", "Culinária", "Banho", "Outros")

                        options.chunked(2).forEach { rowOptions ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                rowOptions.forEach { option ->
                                    ChipCuidador(
                                        text = option,
                                        isSelected = selectedOptions.contains(option),
                                        onClick = {
                                            setSelectedOptions(
                                                if (selectedOptions.contains(option)) {
                                                    selectedOptions - option
                                                } else {
                                                    selectedOptions + option
                                                }
                                            )
                                        }
                                    )
                                    Spacer(modifier = Modifier.width(1.dp))
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Informe seu valor hora",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 18.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Left,
                        fontSize = 16.sp,
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
                                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                            )
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { /* Ação para salvar alterações */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFF077DB0))
                            .padding(16.dp)
                    ) {
                        Text(stringResource(R.string.botao_salvar_alteracoes))
                    }
                }
            }
        }
    }
}

// Função de Chip para seleção
@Composable
fun ChipCuidador(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) Color(0xFF077DB0) else Color(0xFFE0E0E0)
    val textColor = if (isSelected) Color.White else Color.Black
    Surface(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .clickable(onClick = onClick)
            .background(backgroundColor)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        color = backgroundColor
    ) {
        Text(text = text, color = textColor, style = MaterialTheme.typography.bodyMedium)
    }
}

// Seção superior do perfil
@Composable
fun TopSectionCuidador() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            painter = painterResource(id = R.drawable.logo_mobile),
            contentDescription = "Logo Mobile Senior Care",
            modifier = Modifier.size(150.dp)
        )
    }
}

// Função para os campos de input
@Composable
fun InputFieldsCuidador(
    nome: String,
    onNomeChange: (String) -> Unit,
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
    Column {
        TextField(
            value = nome,
            onValueChange = onNomeChange,
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

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
            label = { Text("Sobre você") },
            modifier = Modifier.fillMaxWidth()
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
}

@Preview(showBackground = true)
@Composable
fun EdicaoPerfilCuidadorPreview() {
    MobileSeniorCareTheme {
        EdicaoPerfilCuidadorScreen(rememberNavController())
    }
}
