package com.example.mobileseniorcare.telas.cadastro

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileseniorcare.R
import com.example.mobileseniorcare.ui.theme.MobileSeniorCareTheme

class Cadastro4 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileSeniorCareTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting5(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        activity = this
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting5(name: String, modifier: Modifier = Modifier, activity: ComponentActivity) {
    var qtdIdosos by remember { mutableStateOf("") }
    var precoHora by remember { mutableStateOf("") }
    var ajuda by remember { mutableStateOf(setOf<String>()) }

    var qtdIdososError by remember { mutableStateOf(false) }
    var precoHoraError by remember { mutableStateOf(false) }
    var ajudaError by remember { mutableStateOf(false) }

    val labelColor = Color(0xFF000000)
    val borderColor = Color(0xFF077DB0)
    val buttonBackgroundColor = Color(0xFF077DB0)
    val buttonTextColor = Color.White
    val buttonWhiteTextColor = Color(0xFF077DB0)
    val buttonWhiteBackgroundColor = Color.White
    val textColor = Color.Black

    // Lista de opções para o botão
    val options = listOf("Trabalho de casa", "Culinária", "Banho", "Curativos", "Outros")

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF077DB0))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.35f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Image(
                painter = painterResource(id = R.drawable.logo_mobile),
                contentDescription = "Logo do Mobile Senior Care",
                modifier = Modifier.size(150.dp),
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)
                )
                .align(Alignment.BottomCenter)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(28.dp))
            OutlinedTextField(
                label = { Text(stringResource(R.string.label_qtd_idosos), color = labelColor) },
                value = qtdIdosos,
                onValueChange = { qtdIdosos = it; qtdIdososError = qtdIdosos.isEmpty() },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor
                )
            )

            if (qtdIdososError) {
                Text(stringResource(R.string.error_qtd_idosos), color = Color.Red, style = TextStyle(fontSize = 12.sp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                label = { Text(stringResource(R.string.label_preco_hora), color = labelColor) },
                value = precoHora,
                onValueChange = { precoHora = it; precoHoraError = precoHora.isEmpty() },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor
                )
            )
            if (precoHoraError) {
                Text(stringResource(R.string.error_preco_hora), color = Color.Red, style = TextStyle(fontSize = 12.sp))
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Seção de seleção
            Text(
                text = stringResource(R.string.label_ajuda),
                color = labelColor,
                modifier = Modifier.padding(top = 24.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Exibir botões em uma coluna, com dois por linha
            Column {
                options.chunked(2).forEach { rowOptions ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        rowOptions.forEach { option ->
                            val isSelected = option in ajuda
                            Button(
                                onClick = {
                                    ajuda = if (isSelected) ajuda - option else ajuda + option
                                },
                                modifier = Modifier.weight(1f),
                                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                                    containerColor = if (isSelected) buttonBackgroundColor else buttonWhiteBackgroundColor,
                                    contentColor = if (isSelected) buttonTextColor else buttonWhiteTextColor
                                )
                            ) {
                                Text(option)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
                if (ajudaError) {
                    Text("Selecione pelo menos uma opção de ajuda", color = Color.Red, style = TextStyle(fontSize = 12.sp))
                }
            }

            Spacer(modifier = Modifier.height(30.dp)) // Espaço antes dos botões

            // Botões Próximo e Voltar
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp) // Espaço entre os botões
            ) {
                Button(
                    onClick = {
                        qtdIdososError = qtdIdosos.isEmpty()
                        precoHoraError = precoHora.isEmpty()
                        ajudaError = ajuda.isEmpty()

                        if (!qtdIdososError && !precoHoraError && !ajudaError) {
                            activity.startActivity(Intent(activity, Cadastro5::class.java))
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp)),
                    shape = RoundedCornerShape(10.dp),
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = buttonBackgroundColor,
                        contentColor = buttonTextColor
                    )
                ) {
                    Text(stringResource(R.string.botao_proximo_cadastro4))
                }
                Button(
                    onClick = { activity.startActivity(Intent(activity, Cadastro3::class.java)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .border(1.dp, borderColor, shape = RoundedCornerShape(10.dp)),
                    shape = RoundedCornerShape(10.dp),
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = buttonWhiteBackgroundColor,
                        contentColor = labelColor
                    )
                ) {
                    Text(stringResource(R.string.botao_voltar_cadastro4))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    MobileSeniorCareTheme {
        Greeting5("Android", activity = ComponentActivity())
    }
}
