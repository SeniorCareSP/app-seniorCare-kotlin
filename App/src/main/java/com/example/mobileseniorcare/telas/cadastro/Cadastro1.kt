package com.example.mobileseniorcare.telas.cadastro

import android.content.Intent
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileseniorcare.R
import com.example.mobileseniorcare.telas.Login
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
                        modifier = Modifier.padding(innerPadding),
                        activity = this
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier, activity: ComponentActivity) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }
    var cep by remember { mutableStateOf("") }
    var nome by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf("") }

    val labelColor = Color(0xFF000000)
    val borderColor = Color(0xFF077DB0)
    val buttonBackgroundColor = Color(0xFF077DB0)
    val buttonTextColor = Color.White
    val textColor = Color.Black

    var emailError by remember { mutableStateOf(false) }
    var senhaError by remember { mutableStateOf(false) }
    var confirmarSenhaError by remember { mutableStateOf(false) }
    var cepError by remember { mutableStateOf(false) }
    var nomeError by remember { mutableStateOf(false) }
    var celularError by remember { mutableStateOf(false) }
    var selectedOptionError by remember { mutableStateOf(false) }

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
            Spacer(modifier = Modifier.height(36.dp))
            OutlinedTextField(
                label = { Text(stringResource(R.string.label_nome), color = labelColor) },
                value = nome,
                onValueChange = { nome = it; nomeError = nome.isEmpty() },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor
                )
            )
            if (nomeError) {
                Text(stringResource(R.string.error_nome), color = Color.Red, style = TextStyle(fontSize = 12.sp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                label = { Text(stringResource(R.string.label_celular), color = labelColor) },
                value = celular,
                onValueChange = { celular = it; celularError = celular.isEmpty() },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor
                )
            )
            if (celularError) {
                Text(stringResource(R.string.error_celular), color = Color.Red, style = TextStyle(fontSize = 12.sp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                label = { Text(stringResource(R.string.label_email), color = labelColor) },
                value = email,
                onValueChange = { email = it; emailError = email.isEmpty() },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor
                )
            )
            if (emailError) {
                Text(stringResource(R.string.error_email), color = Color.Red, style = TextStyle(fontSize = 12.sp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                label = { Text(stringResource(R.string.label_senha), color = labelColor) },
                value = senha,
                onValueChange = { senha = it; senhaError = senha.isEmpty() },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor
                )
            )
            if (senhaError) {
                Text(stringResource(R.string.error_senha), color = Color.Red, style = TextStyle(fontSize = 12.sp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                label = { Text(stringResource(R.string.label_confirmar_senha), color = labelColor) },
                value = confirmarSenha,
                onValueChange = { confirmarSenha = it; confirmarSenhaError = confirmarSenha.isEmpty() },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor
                )
            )
            if (confirmarSenhaError) {
                Text(stringResource(R.string.error_confirmar_senha), color = Color.Red, style = TextStyle(fontSize = 12.sp))
            } else if (senha != confirmarSenha) {
                Text(stringResource(R.string.error_senha_diferente), color = Color.Red, style = TextStyle(fontSize = 12.sp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                label = { Text(stringResource(R.string.label_cep), color = labelColor) },
                value = cep,
                onValueChange = { cep = it; cepError = cep.isEmpty() },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor
                )
            )
            if (cepError) {
                Text(stringResource(R.string.error_cep), color = Color.Red, style = TextStyle(fontSize = 12.sp))
            }
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { selectedOption = "Cuidador"; selectedOptionError = false },
                    modifier = Modifier
                        .weight(1f)
                        .border(
                            width = 1.dp,
                            color = if (selectedOption == "Cuidador") Color.White else borderColor,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = if (selectedOption == "Cuidador") buttonBackgroundColor else Color.White,
                        contentColor = if (selectedOption == "Cuidador") buttonTextColor else buttonBackgroundColor
                    )
                ) {
                    Text("Cuidador")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = { selectedOption = "Responsável"; selectedOptionError = false },
                    modifier = Modifier
                        .weight(1f)
                        .border(
                            width = 1.dp,
                            color = if (selectedOption == "Responsável") Color.White else borderColor,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = if (selectedOption == "Responsável") buttonBackgroundColor else Color.White,
                        contentColor = if (selectedOption == "Responsável") buttonTextColor else buttonBackgroundColor
                    )
                ) {
                    Text("Responsável")
                }
            }
            if (selectedOptionError) {
                Text(stringResource(R.string.error_opcao), color = Color.Red, style = TextStyle(fontSize = 12.sp))
            }
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = {
                    emailError = email.isEmpty()
                    senhaError = senha.isEmpty()
                    confirmarSenhaError = confirmarSenha.isEmpty()
                    cepError = cep.isEmpty()
                    nomeError = nome.isEmpty()
                    celularError = celular.isEmpty()
                    selectedOptionError = selectedOption.isEmpty()

                    if (!emailError && !senhaError && !confirmarSenhaError && !cepError && !nomeError && !celularError && !selectedOptionError) {
                        activity.startActivity(Intent(activity, Cadastro2::class.java))
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = buttonBackgroundColor,
                    contentColor = buttonTextColor
                )
            ) {
                Text(stringResource(R.string.botao_proximo_cadastro1))
            }

            Spacer(modifier = Modifier.height(36.dp))
            Text(
                text = stringResource(R.string.texto_jah_tem_conta),
                modifier = Modifier
                    .clickable { /* Ação para entrar na conta */ }
                    .padding(top = 16.dp),
                color = labelColor,
                textAlign = TextAlign.Center,
                style = TextStyle(textDecoration = TextDecoration.Underline)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    MobileSeniorCareTheme {
        Greeting2("Android", activity = ComponentActivity())
    }
}
