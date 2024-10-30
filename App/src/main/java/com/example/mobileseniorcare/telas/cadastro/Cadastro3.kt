package com.example.mobileseniorcare.telas.cadastro

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileseniorcare.R
import com.example.mobileseniorcare.ui.theme.MobileSeniorCareTheme

class Cadastro3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileSeniorCareTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting4(
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
fun Greeting4(name: String, modifier: Modifier = Modifier, activity: ComponentActivity) {
    var dtNascimento by remember { mutableStateOf("") }
    var idioma by remember { mutableStateOf("") }
    var tempoExperiencia by remember { mutableStateOf("") }
//    var faixaEtariaExp by remember { mutableStateOf("") }
    val labelColor = Color(0xFF000000)
    val borderColor = Color(0xFF077DB0)
    val buttonBackgroundColor = Color(0xFF077DB0)
    val buttonTextColor = Color.White
    val buttonWhiteTextColor = Color(0xFF077DB0)
    val buttonWhiteBackgroundColor = Color.White
    val textColor = Color.Black


    var dtNascimentoError by remember { mutableStateOf(false) }
    var idiomaError by remember { mutableStateOf(false) }
    var tempoExperienciaError by remember { mutableStateOf(false) }
//    var faixaEtariaExpError by remember { mutableStateOf(false) }


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
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(
                        topEnd = 30.dp,
                        topStart = 30.dp
                    )
                )
                .align(Alignment.BottomCenter)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(36.dp))
            OutlinedTextField(
                label = { Text("Data de Nascimento", color = labelColor) },
                value = dtNascimento,
                onValueChange = { dtNascimento = it ; dtNascimentoError = dtNascimento.isEmpty() },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor
                )
            )
            if (dtNascimentoError) {
                Text("Preencha o campo de data de nascimento", color = Color.Red, style = TextStyle(fontSize = 12.sp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                label = { Text("Idioma", color = labelColor) },
                value = idioma,
                onValueChange = { idioma = it ; idiomaError = idioma.isEmpty()},
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor
                )
            )
            if (idiomaError) {
                Text("Preencha o campo de idioma", color = Color.Red, style = TextStyle(fontSize = 12.sp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                label = { Text("Tempo de experiência", color = labelColor) },
                value = tempoExperiencia,
                onValueChange = { tempoExperiencia = it ; tempoExperienciaError = tempoExperiencia.isEmpty() },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor
                )
            )
            if (tempoExperienciaError) {
                Text("Preencha o campo de tempo de experiencia", color = Color.Red, style = TextStyle(fontSize = 12.sp))
            }
//            Spacer(modifier = Modifier.height(16.dp))
//            OutlinedTextField(
//                label = { Text("Faixa etária de experiencia", color = labelColor) },
//                value = faixaEtariaExp,
//                onValueChange = { faixaEtariaExp = it ; faixaEtariaExpError = faixaEtariaExp.isEmpty()  },
//                modifier = Modifier.fillMaxWidth(),
//                colors = OutlinedTextFieldDefaults.colors(
//                    focusedBorderColor = borderColor,
//                    unfocusedBorderColor = borderColor,
//                    focusedTextColor = textColor,
//                    unfocusedTextColor = textColor
//                )
//            )
//            if (faixaEtariaExpError) {
//                Text("Preencha o campo de faixa etaria", color = Color.Red, style = TextStyle(fontSize = 12.sp))
//            }
            Spacer(modifier = Modifier.height(72.dp)) // Espaço antes dos botões

            // Botões Próximo e Voltar
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp) // Espaço entre os botões
            ) {
                Button(
                    onClick = {
                        dtNascimentoError = dtNascimento.isEmpty()
                        tempoExperienciaError = tempoExperiencia.isEmpty()
                        idiomaError = idioma.isEmpty()
//                        faixaEtariaExpError = faixaEtariaExp.isEmpty()

                        if (!dtNascimentoError && !tempoExperienciaError && !idiomaError) {
                            activity.startActivity(Intent(activity, Cadastro4::class.java))
                        }},
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
                Button(
                    onClick = {  activity.startActivity(Intent(activity, Cadastro2::class.java))  },
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    MobileSeniorCareTheme {
        Greeting4("Android",  activity = ComponentActivity())
    }
}