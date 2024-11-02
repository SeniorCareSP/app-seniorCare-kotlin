package com.example.mobileseniorcare.telas.cadastro

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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

class Cadastro5 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileSeniorCareTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Cadastros(modifier = Modifier.padding(innerPadding), activity = this)
                }
            }
        }
    }
}

@Composable
fun Cadastros(modifier: Modifier = Modifier, activity: ComponentActivity) {
    var text by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf("") }
    val borderColor = Color(0xFF077DB0)
    val buttonBackgroundColor = Color(0xFF077DB0)
    val buttonTextColor = Color.White
    val buttonWhiteTextColor = Color(0xFF077DB0)
    val buttonWhiteBackgroundColor = Color.White

    val options = listOf(
        stringResource(R.string.label_cnh),
        stringResource(R.string.label_diploma_enfermagem),
        stringResource(R.string.label_certificado_primeiros_socorros),
        stringResource(R.string.label_cuidador)
    )

    val canProceed = text.isNotBlank() && selectedOption.isNotBlank()

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
                                    onClick = { selectedOption = option },
                                    shape = RoundedCornerShape(7.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (selectedOption == option) buttonBackgroundColor else buttonWhiteBackgroundColor,
                                        contentColor = if (selectedOption == option) buttonTextColor else buttonWhiteTextColor
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
                        onValueChange = { newText -> text = newText },
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
                                    activity.startActivity(Intent(activity, Cadastro6::class.java))
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
                            onClick = { activity.startActivity(Intent(activity, Cadastro4::class.java)) },
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview9() {
    MobileSeniorCareTheme {
        Cadastros(activity = ComponentActivity())
    }
}
