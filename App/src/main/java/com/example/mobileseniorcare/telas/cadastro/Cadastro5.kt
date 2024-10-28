package com.example.mobileseniorcare.telas.cadastro
import android.content.Intent
import com.example.mobileseniorcare.R
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileseniorcare.ui.theme.MobileSeniorCareTheme


class Cadastro5 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileSeniorCareTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Cadastros(
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
fun Cadastros(name: String, modifier: Modifier = Modifier, activity: ComponentActivity) {
    var text by remember { mutableStateOf("Digite algo aqui...") }
    var selectedOption by remember { mutableStateOf("") } // Estado para controle de seleção
    val labelColor = Color(0xFF000000)
    val borderColor = Color(0xFF077DB0)
    val buttonBackgroundColor = Color(0xFF077DB0)
    val buttonTextColor = Color.White
    val buttonWhiteTextColor = Color(0xFF077DB0)
    val buttonWhiteBackgroundColor = Color.White
    val textColor = Color.Black

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
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
                    .padding(bottom = 20.dp)
                    .padding(top = 20.dp)
            ) {
                Column(modifier = Modifier.padding(bottom = 30.dp).padding(start = 30.dp))
                {
                    Text(text = "Você possui:", fontSize = 20.sp)
                }
                Row(
                    modifier = modifier.padding(20.dp).padding(top = 15.dp),
                    horizontalArrangement = Arrangement.Center,

                    ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                        modifier = modifier.weight(0.5f)
                    ) {
                        Button(
                            modifier = Modifier.height(60.dp)
                                .width(160.dp)
                                .border(
                                    width = 1.dp,
                                    color = if (selectedOption == "Cuidador") Color.White else borderColor,
                                    shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
                                ),
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(7.dp),
                            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                                containerColor = if (selectedOption == "Cuidador") buttonBackgroundColor else buttonWhiteBackgroundColor,
                                contentColor = if (selectedOption == "Cuidador") buttonTextColor else buttonWhiteTextColor
                            )
                        ) {
                            Text(text = "CNH")
                        }
                        Button(
                            modifier = Modifier.height(60.dp)
                                .width(160.dp)
                                .border(
                                    width = 1.dp,
                                    color = if (selectedOption == "Diploma de enfermagem") Color.White else borderColor,
                                    shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
                                ),
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(7.dp),
                            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                                containerColor = if (selectedOption == "Diploma de enfermagem") buttonBackgroundColor else buttonWhiteBackgroundColor,
                                contentColor = if (selectedOption == "Diploma de enfermagem") buttonTextColor else buttonWhiteTextColor
                            )
                        ) {
                            Text(text = "Diploma de enfermagem")
                        }
                    }


                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                        modifier = modifier.weight(0.5f)
                    ) {
                        Button(
                            modifier = Modifier.height(60.dp)
                                .width(160.dp)
                                .border(
                                    width = 1.dp,
                                    color = if (selectedOption == "Certificado de primeiros socorros") Color.White else borderColor,
                                    shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
                                ),
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(7.dp),
                            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                                containerColor = if (selectedOption =="Certificado de primeiros socorros") buttonBackgroundColor else buttonWhiteBackgroundColor,
                                contentColor = if (selectedOption == "Certificado de primeiros socorros") buttonTextColor else buttonWhiteTextColor
                            )
                        ) {
                            Text(text = "Certificado de primeiros socorros")
                        }
                        Button(
                            modifier = Modifier.height(60.dp)
                                .width(160.dp)
                                .border(
                                    width = 1.dp,
                                    color = if (selectedOption == "Cuidador") Color.White else borderColor,
                                    shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
                                ),
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(7.dp),
                            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                                containerColor = if (selectedOption == "Cuidador") buttonBackgroundColor else buttonWhiteBackgroundColor,
                                contentColor = if (selectedOption == "Cuidador") buttonTextColor else buttonWhiteTextColor
                            )
                        ) {
                            Text(text = "Certificado de cuidados geriatricos")
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
                    Text(text = "Apresente-se", fontSize = 20.sp)
                    OutlinedTextField(
                        value = text,
                        onValueChange = { newText -> text = newText },
//                        label = { Text("Digite aqui") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        maxLines = 5,
                        singleLine = false
                    )
                    //-------------------------------------------------------
                    //    Text(text = "Apresente-se", modifier = Modifier.padding(top = 5.dp))
                    //------------------------------------------------------

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = {  activity.startActivity(Intent(activity, Cadastro6::class.java))  },
                            shape = RoundedCornerShape(7.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(7,125,176),
                                contentColor = Color.White
                            ),
//                            elevation = ButtonDefaults.buttonElevation(
//                                defaultElevation = 8.dp,
//                                pressedElevation = 12.dp
//                            ),
                            modifier = Modifier.fillMaxWidth().padding(top=25.dp)
                        ) {
                            Text(text = "Próximo")
                        }
                        Spacer(modifier = Modifier.height(10.dp)) // Espaço entre os botões

                        Button(
                            onClick = { activity.startActivity(Intent(activity, Cadastro4::class.java))  },
                            shape = RoundedCornerShape(7.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Color(7,125,176),
                            ),
//                            elevation = ButtonDefaults.buttonElevation(
//                                defaultElevation = 8.dp,
//                                pressedElevation = 12.dp
//                            ),
                            modifier = Modifier.fillMaxWidth()
                                .border(1.dp, borderColor, shape = RoundedCornerShape(10.dp)),
                        ) {
                            Text(text = "Volta")
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
        Cadastros("Android",  activity = ComponentActivity())
    }
}