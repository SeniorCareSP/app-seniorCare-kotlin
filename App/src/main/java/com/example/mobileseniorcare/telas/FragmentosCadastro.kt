package com.example.mobileseniorcare.telas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mobileseniorcare.R
import com.example.mobileseniorcare.api.SeniorCareViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.Popup
import androidx.lifecycle.ViewModel
import com.example.mobileseniorcare.api.RetrofitService
import com.example.mobileseniorcare.dataclass.Ajuda
import com.example.mobileseniorcare.dataclass.CepResponse
import com.example.mobileseniorcare.dataclass.Endereco
import com.example.mobileseniorcare.dataclass.Idioma
import com.example.mobileseniorcare.dataclass.usuario.UsuarioCuidador
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import kotlin.text.Typography.dagger


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

fun buscarCep(cep: String, onResult: (CepResponse?) -> Unit) {
    val call = RetrofitService.viaCepService.buscarCep(cep);
    call.enqueue(object : Callback<CepResponse> {
        override fun onResponse(call: Call<CepResponse>, response: Response<CepResponse>) {
            if (response.isSuccessful) {
                onResult(response.body())
            } else {
                onResult(null)
            }
        }

        override fun onFailure(call: Call<CepResponse>, t: Throwable) {
            onResult(null)
        }
    })
}


@Composable
fun Cadastro1(navController: NavHostController, viewModel: SeniorCareViewModel = viewModel(), modifier: Modifier = Modifier) {
   // var usuarioCuidador = UsuarioCuidador();
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }
    var cep by remember { mutableStateOf("") }
    var nome by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf("") }
   // val usuarioCuidador = rememberSaveable { UsuarioCuidador() }


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
    var selectedOptionError by remember { mutableStateOf(false) }

//    fun validarCadastro(confirmarSenha: String): Boolean {
//        emailError = viewModel.usuarioAtual.email.isNullOrEmpty()
//        senhaError = viewModel.usuarioAtual.senha.isNullOrEmpty()
//        confirmarSenhaError = confirmarSenha.isEmpty() || viewModel.usuarioAtual.senha != confirmarSenha
//        cepError = viewModel.usuarioAtual.endereco?.cep.isNullOrEmpty()
//        nomeError = viewModel.usuarioAtual.nome.isNullOrEmpty()
//        selectedOptionError = viewModel.usuarioAtual.tipoDeUsuario.isNullOrEmpty()
//
//        return !(emailError || senhaError || confirmarSenhaError || cepError || nomeError || selectedOptionError)
//    }

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
                value = viewModel.usuarioAtual.nome ?: "",
                onValueChange = {
                    viewModel.usuarioAtual = viewModel.usuarioAtual.copy(nome = it)
                    nomeError = viewModel.usuarioAtual.nome.isNullOrEmpty() },
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
                label = { Text(stringResource(R.string.label_email), color = labelColor) },
                value = viewModel.usuarioAtual.email ?: "",
                onValueChange = { viewModel.usuarioAtual = viewModel.usuarioAtual.copy(email = it)
                    emailError = viewModel.usuarioAtual.email.isNullOrEmpty() },
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
                value = viewModel.usuarioAtual.senha ?: "",
                onValueChange = { viewModel.usuarioAtual = viewModel.usuarioAtual.copy(senha = it) },
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
                onValueChange = {
                    confirmarSenha = it
                    confirmarSenhaError = confirmarSenha.isEmpty() || confirmarSenha != viewModel.usuarioAtual.senha
                },  modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor
                )
            )
            if (confirmarSenhaError) {
                if (confirmarSenha.isEmpty()) {
                    Text(stringResource(R.string.error_confirmar_senha), color = Color.Red, style = TextStyle(fontSize = 12.sp))
                } else {
                    Text(stringResource(R.string.error_senha_diferente), color = Color.Red, style = TextStyle(fontSize = 12.sp))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                label = { Text(stringResource(R.string.label_cep), color = labelColor) },
                value = viewModel.usuarioAtual.endereco?.cep ?: "",
                onValueChange = { cepValue ->
                    val enderecoAtualizado = viewModel.usuarioAtual.endereco?.copy(cep = cepValue)
                        ?: Endereco(cep = cepValue) // Cria um novo Endereco com o CEP

                    // Atualiza o UsuarioCuidador com o endereco atualizado
                    viewModel.usuarioAtual = viewModel.usuarioAtual.copy(endereco = enderecoAtualizado)

                    // Atualiza o erro de cep
                    cepError = cepValue.isEmpty()
                },
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
                    onClick = { selectedOption = "Cuidador"; viewModel.usuarioAtual.tipoDeUsuario = selectedOption; selectedOptionError = false },
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
                    onClick = { selectedOption = "Responsável";  viewModel.usuarioAtual.tipoDeUsuario = selectedOption; selectedOptionError = false },
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
                    emailError = viewModel.usuarioAtual.email.isNullOrEmpty()
                    senhaError = viewModel.usuarioAtual.senha.isNullOrEmpty()
                    confirmarSenhaError = confirmarSenha.isEmpty() || confirmarSenha != viewModel.usuarioAtual.senha
                    cepError = viewModel.usuarioAtual.endereco?.cep.isNullOrEmpty()
                    nomeError = viewModel.usuarioAtual.nome.isNullOrEmpty()
                    selectedOptionError = viewModel.usuarioAtual.tipoDeUsuario.isNullOrEmpty()

                    if (confirmarSenha != viewModel.usuarioAtual.senha) {
                        confirmarSenhaError = true
                        return@Button;
                    }


                    if (!emailError && !senhaError && !confirmarSenhaError && !cepError && !nomeError  && !selectedOptionError) {
                        navController.navigate(route = "cadastro2")
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




@Composable
fun Cadastro2(navController: NavHostController, viewModel: SeniorCareViewModel = viewModel(), modifier: Modifier = Modifier, ) {

    var logradouro by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }
    var complemento by remember { mutableStateOf("") }
    var cidade by remember { mutableStateOf("") }
    var bairro by remember { mutableStateOf("") }

    val labelColor = Color(0xFF000000)
    val borderColor = Color(0xFF077DB0)
    val buttonBackgroundColor = Color(0xFF077DB0)
    val buttonTextColor = Color.White
    val buttonWhiteTextColor = Color(0xFF077DB0)
    val buttonWhiteBackgroundColor = Color.White
    val textColor = Color.Black

    var logradouroError by remember { mutableStateOf(false) }
    var numeroError by remember { mutableStateOf(false) }
    var bairroError by remember { mutableStateOf(false) }
    var cidadeError by remember { mutableStateOf(false) }

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
                label = { Text(stringResource(R.string.label_logradouro), color = labelColor) },
                value = logradouro,
                onValueChange = { logradouro = it; logradouroError = logradouro.isEmpty() },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor
                )
            )
            if (logradouroError) {
                Text(stringResource(R.string.error_logradouro), color = Color.Red, style = TextStyle(fontSize = 12.sp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                label = { Text(stringResource(R.string.label_numero), color = labelColor) },
                value = numero,
                onValueChange = { numero = it; numeroError = numero.isEmpty() },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor
                )
            )
            if (numeroError) {
                Text(stringResource(R.string.error_numero), color = Color.Red, style = TextStyle(fontSize = 12.sp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                label = { Text(stringResource(R.string.label_complemento), color = labelColor) },
                value = complemento,
                onValueChange = { complemento = it },
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
                label = { Text(stringResource(R.string.label_cidade), color = labelColor) },
                value = cidade,
                onValueChange = { cidade = it; cidadeError = cidade.isEmpty() },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor
                )
            )
            if (cidadeError) {
                Text(stringResource(R.string.error_cidade), color = Color.Red, style = TextStyle(fontSize = 12.sp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                label = { Text(stringResource(R.string.label_bairro), color = labelColor) },
                value = bairro,
                onValueChange = { bairro = it; bairroError = bairro.isEmpty() },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor
                )
            )
            if (bairroError) {
                Text(stringResource(R.string.error_bairro), color = Color.Red, style = TextStyle(fontSize = 12.sp))
            }
            Spacer(modifier = Modifier.height(40.dp)) // Espaço antes dos botões

            // Botões Próximo e Voltar
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp) // Espaço entre os botões
            ) {
                Button(
                    onClick = {
                        logradouroError = logradouro.isEmpty()
                        numeroError = numero.isEmpty()
                        bairroError = bairro.isEmpty()
                        cidadeError = cidade.isEmpty()

                        if (!logradouroError && !numeroError && !bairroError && !cidadeError) {
                            navController.navigate(route = "cadastro3")
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = buttonBackgroundColor,
                        contentColor = buttonTextColor
                    )
                ) {
                    Text(stringResource(R.string.botao_proximo_cadastro2))
                }
                Button(
                    onClick = {navController.navigate(route = "cadastro1") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .border(1.dp, borderColor, shape = RoundedCornerShape(8.dp)),
                    shape = RoundedCornerShape(10.dp),
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = buttonWhiteBackgroundColor,
                        contentColor = buttonWhiteTextColor
                    )
                ) {
                    Text(stringResource(R.string.botao_voltar_cadastro2))
                }
            }
        }
    }
}

fun convertMillisToLocalDate(millis: Long?): LocalDate? {
    return millis?.let {
        Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cadastro3(navController: NavHostController, viewModel: SeniorCareViewModel = viewModel(), modifier: Modifier = Modifier) {
    var dtNascimento by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }
    var idioma by remember { mutableStateOf("") }
    var tempoExperiencia by remember { mutableStateOf("") }

    val labelColor = Color(0xFF000000)
    val borderColor = Color(0xFF077DB0)
    val buttonBackgroundColor = Color(0xFF077DB0)
    val buttonTextColor = Color.White
    val buttonWhiteTextColor = Color(0xFF077DB0)
    val buttonWhiteBackgroundColor = Color.White
    val textColor = Color.Black

    var dtNascimentoError by remember { mutableStateOf(false) }
    var celularError by remember { mutableStateOf(false) }
    var idiomaError by remember { mutableStateOf(false) }
    var tempoExperienciaError by remember { mutableStateOf(false) }

    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    //val selectedDate = datePickerState.selectedDateMillis?.let { convertMillisToDate(it) } ?: ""

    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

// Converter LocalDate para String usando o formatador
    val selectedDateString = viewModel.usuarioAtual.dtNascimento?.format(dateFormatter) ?: ""

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

            // Atualiza a data no ViewModel quando o usuário escolhe uma data
//            LaunchedEffect(selectedDate) {
//                if (selectedDate.isNotEmpty()) {
//                    val newDate = LocalDate.parse(selectedDate, dateFormatter)
//                    viewModel.usuarioAtual = viewModel.usuarioAtual.copy(dtNascimento = newDate)
//                }

            OutlinedTextField(
                label = {
                    Text(
                        stringResource(R.string.label_data_nascimento),
                        color = labelColor
                    )
                },
                value = selectedDateString,
                onValueChange = { },
                readOnly = true,
                isError = dtNascimentoError,
                trailingIcon = {
                    IconButton(onClick = { showDatePicker = !showDatePicker }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Selecionar data"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Gray,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            if (showDatePicker) {
                Popup(
                    onDismissRequest = { showDatePicker = false },
                    alignment = Alignment.TopStart
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth() // Certifique-se de que a Box preenche toda a largura
                            .offset(y = 64.dp)
                            .shadow(elevation = 4.dp)
                            .background(MaterialTheme.colorScheme.surface)
                          //  .padding(16.dp)
                    ) {
                        DatePicker(
                            state = datePickerState,
                            showModeToggle = false,
                        )

                        // Certifique-se de que o botão esteja dentro do Box
                        Button(
                            onClick = {
                                val selectedDate =
                                    convertMillisToLocalDate(datePickerState.selectedDateMillis)
                                if (selectedDate != null) {
                                    viewModel.usuarioAtual =
                                        viewModel.usuarioAtual.copy(dtNascimento = selectedDate)
                                }
                                showDatePicker = false
                            },
                           modifier = Modifier.align(Alignment.BottomEnd)// Alinha o botão à direita dentro do Box
                        ) {
                            Text("Selecionar")
                        }
                    }
                }
            }



            if (dtNascimentoError) {
                Text(
                    stringResource(R.string.error_data_nascimento),
                    color = Color.Red,
                    style = TextStyle(fontSize = 12.sp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                label = { Text(stringResource(R.string.label_celular), color = labelColor) },
                value = viewModel.usuarioAtual.telefone ?: "",
                onValueChange = {
                    viewModel.usuarioAtual = viewModel.usuarioAtual.copy(telefone = it)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor
                )
            )
            if (celularError) {
                Text(
                    stringResource(R.string.error_celular),
                    color = Color.Red,
                    style = TextStyle(fontSize = 12.sp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
                  IdiomasSelect(viewModel)
            if (idiomaError) {
                Text(
                    stringResource(R.string.error_idioma),
                    color = Color.Red,
                    style = TextStyle(fontSize = 12.sp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                label = {
                    Text(
                        stringResource(R.string.label_tempo_experiencia),
                        color = labelColor
                    )
                },
                value = viewModel.usuarioAtual.experiencia ?: "",
                onValueChange = {
                    viewModel.usuarioAtual = viewModel.usuarioAtual.copy(experiencia = it)
                    tempoExperienciaError = viewModel.usuarioAtual.experiencia.isNullOrEmpty()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor
                )
            )
            if (tempoExperienciaError) {
                Text(
                    stringResource(R.string.error_tempo_experiencia),
                    color = Color.Red,
                    style = TextStyle(fontSize = 12.sp)
                )
            }
            Spacer(modifier = Modifier.height(72.dp)) // Espaço antes dos botões

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp) // Espaço entre os botões
            ) {
                Button(
                    onClick = {
                        dtNascimentoError = viewModel.usuarioAtual.dtNascimento == null
                        tempoExperienciaError = viewModel.usuarioAtual.experiencia.isNullOrEmpty()
                        idiomaError = viewModel.usuarioAtual.idiomas.isNullOrEmpty()
                        celularError = viewModel.usuarioAtual.telefone.isNullOrEmpty()


                        if (!dtNascimentoError && !tempoExperienciaError && !celularError && !idiomaError) {
                            if (viewModel.usuarioAtual.tipoDeUsuario.equals("Cuidador")) {
                                navController.navigate(route = "cadastro4")
                            } else {
                                navController.navigate(route = "cadastro6")
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = buttonBackgroundColor,
                        contentColor = buttonTextColor
                    )
                ) {
                    Text(stringResource(R.string.botao_proximo_cadastro3))
                }
                Button(
                    onClick = { navController.navigate(route = "cadastro2") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .border(1.dp, borderColor, shape = RoundedCornerShape(8.dp)),
                    shape = RoundedCornerShape(10.dp),
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = buttonWhiteBackgroundColor,
                        contentColor = buttonWhiteTextColor
                    )
                ) {
                    Text(stringResource(R.string.botao_voltar_cadastro3))
                }
            }
        }
    }
}






@Composable
fun Cadastro4(navController: NavHostController, viewModel: SeniorCareViewModel = viewModel(), modifier: Modifier = Modifier) {

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
                value = viewModel.usuarioAtual.qtdIdoso?.toString() ?: "",
                onValueChange = { newText -> val newQtdIdoso = newText.toIntOrNull()
                    viewModel.usuarioAtual = viewModel.usuarioAtual.copy(qtdIdoso = newQtdIdoso)
                }, modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
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
                value = viewModel.usuarioAtual.precoHora?.toString() ?: "", // Converte para String para exibição
                onValueChange = { newText ->
                    // Tente converter o texto inserido para Double
                    val newPrecoHora = newText.toDoubleOrNull() ?: 0.0 // Usa 0.0 como valor padrão se for nulo
                    viewModel.usuarioAtual = viewModel.usuarioAtual.copy(precoHora = newPrecoHora) // Atualiza o ViewModel
                }, modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Decimal
                ),
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
                        qtdIdososError = viewModel.usuarioAtual.qtdIdoso == null
                        precoHoraError = viewModel.usuarioAtual.precoHora.isNaN() || viewModel.usuarioAtual.precoHora <= 0.0
                        ajudaError = viewModel.usuarioAtual.ajuda.isNullOrEmpty()

                        if (!qtdIdososError && !precoHoraError && !ajudaError) {
                            navController.navigate(route = "cadastro5")
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
                    onClick = { navController.navigate(route = "cadastro3") },
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

@Composable
fun Cadastro5(navController: NavHostController , viewModel: SeniorCareViewModel = viewModel(), modifier: Modifier = Modifier) {
       // var text by remember { mutableStateOf("") }
      //  val selectedOptions = remember { mutableStateOf(mutableSetOf<String>()) }
        val borderColor = Color(0xFF077DB0)
        val buttonBackgroundColor = Color(0xFF077DB0)
        val buttonTextColor = Color.White
        val buttonWhiteTextColor = Color(0xFF077DB0)
        val buttonWhiteBackgroundColor = Color.White
        var text by remember { mutableStateOf(viewModel.usuarioAtual.apresentacao ?: "") }
        val selectedOptions = remember { mutableStateOf(mutableSetOf<String>()) }


    val options = listOf(
            stringResource(R.string.label_cnh),
            stringResource(R.string.label_diploma_enfermagem),
            stringResource(R.string.label_certificado_primeiros_socorros),
            stringResource(R.string.label_cuidador)
        )

    fun updateAjuda() {
        viewModel.usuarioAtual.ajuda = selectedOptions.value.map { option ->
            Ajuda(nome  = option)
        }
    }

        val canProceed = text.isNotBlank() && selectedOptions.value.isNotEmpty()

        Column(modifier = modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
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
                        modifier = Modifier.size(150.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp))
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 20.dp)
                        .padding(top = 20.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(bottom = 30.dp).padding(start = 30.dp)
                    ) {
                        Text(text = stringResource(R.string.label_voce_possui), fontSize = 20.sp)
                    }

                    Row(
                        modifier = modifier.padding(20.dp).padding(top = 15.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        options.chunked(2).forEach { columnOptions ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(15.dp),
                                modifier = modifier.weight(0.5f)
                            ) {
                                columnOptions.forEach { option ->
                                    Button(
                                        modifier = Modifier.height(60.dp)
                                            .width(160.dp)
                                            .border(1.dp, borderColor, shape = RoundedCornerShape(8.dp)),
                                        onClick = {
                                            // Adiciona ou remove a opção do conjunto de selecionados
                                            if (selectedOptions.value.contains(option)) {
                                                selectedOptions.value.remove(option)
                                            } else {
                                                selectedOptions.value.add(option)
                                            }
                                            updateAjuda()
                                        },
                                        shape = RoundedCornerShape(7.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = if (selectedOptions.value.contains(option)) buttonBackgroundColor else buttonWhiteBackgroundColor,
                                            contentColor = if (selectedOptions.value.contains(option)) buttonTextColor else buttonWhiteTextColor
                                        )
                                    ) {
                                        Text(text = option)
                                    }
                                }
                            }
                        }
                    }

                    Column(
                        modifier = modifier
                            .padding(top = 185.dp)
                            .padding(start = 25.dp)
                            .padding(end = 25.dp)
                            .padding(bottom = 23.dp)
                    ) {
                        Text(text = stringResource(R.string.label_apresente_se), fontSize = 20.sp)
                        OutlinedTextField(
                            value = text,
                            onValueChange = { newText -> text = newText ;  viewModel.usuarioAtual.apresentacao = newText },
                            modifier = Modifier.fillMaxWidth().height(200.dp),
                            maxLines = 5,
                            singleLine = false
                        )

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = {
                                    if (canProceed) {
                                        navController.navigate(route = "cadastro6")
                                    }
                                },
                                enabled = canProceed,
                                shape = RoundedCornerShape(7.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = buttonBackgroundColor,
                                    contentColor = Color.White
                                ),
                                modifier = Modifier.fillMaxWidth().padding(top = 25.dp)
                            ) {
                                Text(text = stringResource(R.string.botao_proximo_cadastro5))
                            }
                            Spacer(modifier = Modifier.height(10.dp))

                            Button(
                                onClick = {  navController.navigate(route = "cadastro4") },
                                shape = RoundedCornerShape(7.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White,
                                    contentColor = borderColor
                                ),
                                modifier = Modifier.fillMaxWidth()
                                    .border(1.dp, borderColor, shape = RoundedCornerShape(10.dp))
                            ) {
                                Text(text = stringResource(R.string.botao_voltar_cadastro5))
                            }
                        }
                    }
                }
            }
        }
    }


    @Composable
fun Cadastro6(navController: NavHostController , viewModel: SeniorCareViewModel = viewModel(), modifier: Modifier = Modifier) {
    val checkboxesState = remember {
        mutableStateListOf(
            false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false
        )
    }

    // Days of the week
    val daysOfWeek = listOf(
        "Segunda-Feira",
        "Terça-feira",
        "Quarta-feira",
        "Quinta-feira",
        "Sexta-feira",
        "Sábado",
        "Domingo"
    )

    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(8.dp)
            )
        }

        Box(
            modifier = Modifier
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(
                            topEnd = 30.dp,
                            topStart = 30.dp
                        )
                    )
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 30.dp)
            ) {

                Column {
                    Column(
                        modifier = Modifier.padding(start = 44.dp).padding(bottom = 4.dp)
                            .padding(top = 10.dp)
                    ) {// Text(text = "Estou disponivel")
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 5.dp)
                                .padding(start = 35.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 6.dp), // Add space below buttons
                            horizontalArrangement = Arrangement.Center
                        ) {

                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 2.dp)
                            .padding(end = 7.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Dias disponíveis",
                            fontSize = 18.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(end = 12.dp) // Add space between text and icons
                        )
                        Image(
                            painter = painterResource(id = R.drawable.icone_manha),
                            contentDescription = "Icon 1",
                            modifier = Modifier.size(34.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp).padding(start = 15.dp))
                        Image(
                            painter = painterResource(id = R.drawable.icone_tarde),
                            contentDescription = "Icon 2",
                            modifier = Modifier.size(34.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp).padding(start = 15.dp))
                        Image(
                            painter = painterResource(id = R.drawable.icone_noite),
                            contentDescription = "Icon 3",
                            modifier = Modifier.size(34.dp)
                        )
                    }




                    LazyColumn(modifier = Modifier.padding(top = 3.dp)) {
                        items(7) { rowIndex ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(2.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                // Day Button
                                Button(
                                    onClick = { /* Handle button click */ },
                                    modifier = Modifier
                                        .width(150.dp) // Adjust the width as needed
                                        .padding(end = 1.dp),
                                    shape = RoundedCornerShape(7.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(7, 125, 176),
                                        contentColor = Color.White
                                    ),
                                ) {
                                    Text(text = daysOfWeek[rowIndex])

                                }

                                // Checkbox Row
                                Row(
                                    modifier = Modifier
                                        .padding(start = 1.dp),
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    for (columnIndex in 0 until 3) {
                                        val index = rowIndex * 3 + columnIndex
                                        Checkbox(
                                            checked = checkboxesState[index],
                                            onCheckedChange = {
                                                checkboxesState[index] = it
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = modifier.fillMaxWidth().padding(18.dp)
                    ) {
                        Button(
                            onClick = {
                                // Verifique se ao menos um checkbox está marcado
                                if (checkboxesState.any { it }) {
                                    errorMessage = ""
                                    navController.navigate(route = "telaMain")
                                } else {
                                    errorMessage = "Por favor, selecione ao menos um dia."
                                }
                            }, shape = RoundedCornerShape(17.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(7, 125, 176),
                                contentColor = Color.White
                            ),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 8.dp,
                                pressedElevation = 12.dp
                            ),
                            modifier = Modifier.fillMaxWidth().padding(top = 22.dp)
                        ) {
                            Text(text = "Concluir")
                        }
                        Spacer(modifier = Modifier.height(10.dp)) // Espaço entre os botões

                    }
                }
            }

        }
    }
}

@Composable
fun LoginSenior(navController: NavHostController, modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    val seniorCareViewModel: SeniorCareViewModel = viewModel()

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
                            navController.navigate("mainActivity2") { // Navegação para MainActivity2
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

//@Composable
//fun dropDownMenu() {
//
//    var expanded by remember { mutableStateOf(false) }
//    val idiomasDisponiveis = listOf("Português", "Inglês", "Espanhol", "Francês", "Alemão")
//    val suggestions = listOf("Kotlin", "Java", "Dart", "Python")
//    var selectedText by remember { mutableStateOf("") }
//
//    var textfieldSize by remember { mutableStateOf(Size.Zero)}
//
//    val icon = if (expanded)
//        Icons.Filled.KeyboardArrowUp
//    else
//        Icons.Filled.KeyboardArrowDown
//
//
//    Column(Modifier.padding(20.dp)) {
//        OutlinedTextField(
//            value = selectedText,
//            onValueChange = { selectedText = it },
//            modifier = Modifier
//                .fillMaxWidth()
//                .onGloballyPositioned { coordinates ->
//                    //This value is used to assign to the DropDown the same width
//                    textfieldSize = coordinates.size.toSize()
//                },
//            label = {Text("Label")},
//            trailingIcon = {
//                Icon(icon,"contentDescription",
//                    Modifier.clickable { expanded = !expanded })
//            }
//        )
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false },
//            modifier = Modifier
//                .width(with(LocalDensity.current){textfieldSize.width.toDp()})
//        ) {
//            suggestions.forEach { label ->
//                DropdownMenuItem(onClick = {
//                    selectedText = label
//                    expanded = false
//                }) {
//                    Text(text = label)
//                }
//            }
//        }
//    }


    @Composable
    fun IdiomasSelect( viewModel: SeniorCareViewModel = viewModel(), modifier: Modifier = Modifier) {
        var expandida by remember { mutableStateOf(false) }
        var novaFruta by remember { mutableStateOf<Idioma?>(null) }
        val idiomasDisponiveis = listOf("Português", "Inglês", "Espanhol", "Francês", "Alemão")
        var expanded by remember { mutableStateOf(false) }
        var selectedIdioma by remember { mutableStateOf("Selecione um idioma") }

        val frutas = remember {
            mutableStateListOf<Idioma>(
                Idioma("Inglês"),
                Idioma("Inglês"),
                Idioma("Maçã")
            )
        }

        Column(modifier = modifier) {
            ClickableText(
                text = AnnotatedString(
                    if (novaFruta == null) "Selecione a fruta"
                    else novaFruta!!.idioma
                ),
                onClick = { expandida = !expandida },
                modifier = modifier.padding(5.dp)
            )
            Box { // O DropdownMenu precisa estar dentro de um Box para ficar 'por cima' dos outros elementos
                DropdownMenu(
                    expanded = expandida, // indica se está expandido
                    onDismissRequest = { expandida = false } // ação ao clicar fora
                ) {
                    frutas.toList().forEach {
                        DropdownMenuItem( // item da lista drop down
                            text = { Text("${it.idioma} ") },
                            onClick = {
                                novaFruta = it
                                expandida = false
                            }
                        )
                    }
                }
//             //    Atualiza a lista de idiomas no ViewModel
//                    val currentIdiomas = viewModel.usuarioAtual.idiomas?.toMutableList() ?: mutableListOf()
//                    if (!currentIdiomas.any { it.idioma == idioma }) {
//                        currentIdiomas.add(Idioma(idioma))
//                    }
//
//                    // Atualiza o estado do usuário com a nova lista de idiomas
//                    viewModel.usuarioAtual = viewModel.usuarioAtual.copy(idiomas = currentIdiomas)
//            }
                if (novaFruta != null) {
                    Text("Escolhida: ${novaFruta?.idioma}")
                }

            }
        }
    }



//        @Composable
//fun IdiomasSelectss(viewModel: SeniorCareViewModel) {
//    val idiomasDisponiveis = listOf("Português", "Inglês", "Espanhol", "Francês", "Alemão")
//    var expanded by remember { mutableStateOf(false) }
//    var selectedIdioma by remember { mutableStateOf("Selecione um idioma") }
//
//    Column {
//        Text(
//            text = "Selecione os Idiomas",
//            style = MaterialTheme.typography.bodyMedium,
//            color = Color.Black,
//            modifier = Modifier.padding(bottom = 8.dp)
//        )
//
//        // Botão que ao ser clicado abre o menu de seleção
//        OutlinedButton(
//            onClick = { expanded = !expanded },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            // Passamos o texto diretamente aqui
//            Text(text = selectedIdioma.ifEmpty { "Selecione um idioma" })
//        }
//
//        // Dropdown menu
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            idiomasDisponiveis.forEach { idioma ->
//                DropdownMenuItem(  text = {
//                    Text("${it.nome} - R$${it.preco}") } },
//                    onClick = {
//                    selectedIdioma = idioma
//                    expanded = false
//
//                    // Atualiza a lista de idiomas no ViewModel
//                    val currentIdiomas = viewModel.usuarioAtual.idiomas?.toMutableList() ?: mutableListOf()
//                    if (!currentIdiomas.any { it.idioma == idioma }) {
//                        currentIdiomas.add(Idioma(idioma))
//                    }
//
//                    // Atualiza o estado do usuário com a nova lista de idiomas
//                    viewModel.usuarioAtual = viewModel.usuarioAtual.copy(idiomas = currentIdiomas)
//                }) {
//                    Text(text = idioma)
//                }
//            }
//        }
//
//        // Exibe os idiomas selecionados
//        if (!viewModel.usuarioAtual.idiomas.isNullOrEmpty()) {
//            Text(
//                text = "Idiomas selecionados: ${viewModel.usuarioAtual.idiomas?.joinToString(", ") { it.idioma }}",
//                style = MaterialTheme.typography.bodyMedium,
//                color = Color.Black,
//                modifier = Modifier.padding(top = 8.dp)
//            )
//        }
//    }
//}
//
//fun isIdiomaSelecionado(idioma: String, idiomasSelecionados: List<Idioma>): Boolean {
//    return idiomasSelecionados.any { it.idioma == idioma }
//}



