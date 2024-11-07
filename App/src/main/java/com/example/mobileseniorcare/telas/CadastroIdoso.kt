package com.example.mobileseniorcare.telas.cadastro

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileseniorcare.R

import com.example.mobileseniorcare.ui.theme.MobileSeniorCareTheme

class CadastroIdoso : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileSeniorCareTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CadastroIdosoScreen(modifier = Modifier.padding(innerPadding), activity = this)
                }
            }
        }
    }
}

@Composable
fun CadastroIdosoScreen(modifier: Modifier = Modifier, activity: ComponentActivity) {
    var nome by remember { mutableStateOf("") }
    var idade by remember { mutableStateOf("") }
    var doencasCronicas by remember { mutableStateOf("") }
    var precisaDeAjudaCom by remember { mutableStateOf("") }
    var sobre by remember { mutableStateOf("") }

    val labelColor = Color(0xFF000000)
    val borderColor = Color(0xFF077DB0)
    val buttonBackgroundColor = Color(0xFF077DB0)
    val buttonTextColor = Color.White
    val textColor = Color.Black

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
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Cadastro de Idoso",
                color = Color(0xFF077DB0) ,
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                label = { Text(stringResource(R.string.label_nome), color = labelColor) },
                value = nome,
                onValueChange = { nome = it },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                label = { Text(stringResource(R.string.label_idade), color = labelColor) },
                value = idade,
                onValueChange = { newValue ->
                    // Permite apenas números (dígitos) no campo de idade
                    if (newValue.all { it.isDigit() }) {
                        idade = newValue
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                label = { Text(stringResource(R.string.label_doencas_cronicas), color = labelColor) },
                value = doencasCronicas,
                onValueChange = { doencasCronicas = it },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                label = { Text(stringResource(R.string.label_precisa_ajuda_com), color = labelColor) },
                value = precisaDeAjudaCom,
                onValueChange = { precisaDeAjudaCom = it },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                label = { Text(stringResource(R.string.label_sobre), color = labelColor) },
                value = sobre,
                onValueChange = { sobre = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp), // Aumenta a altura do campo "Sobre"
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor
                )
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    // Adicione a lógica para salvar os dados ou prosseguir
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = buttonBackgroundColor,
                    contentColor = buttonTextColor
                )
            ) {
                Text(stringResource(R.string.botao_salvar_alteracoes)) // Texto do botão "Salvar Alterações"
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    // Adicione a lógica para cancelar ou voltar
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, borderColor, RoundedCornerShape(10.dp)) // Borda azul
                    .height(36.dp), // Altura igual ao botão "Salvar Alterações"
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent, // Fundo transparente
                    contentColor = borderColor // Cor do texto azul
                )
            ) {
                Text("Cancelar", color = borderColor) // Texto do botão "Cancelar"
            }

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CadastroIdosoPreview() {
    MobileSeniorCareTheme {
        CadastroIdosoScreen(modifier = Modifier.fillMaxSize(), activity = ComponentActivity())
    }
}
