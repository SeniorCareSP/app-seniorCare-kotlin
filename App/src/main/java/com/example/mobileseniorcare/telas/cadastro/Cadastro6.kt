package com.example.mobileseniorcare.telas.cadastro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileseniorcare.R
import com.example.mobileseniorcare.ui.theme.MobileSeniorCareTheme

class Cadastro6 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileSeniorCareTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting6(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting6(name: String, modifier: Modifier = Modifier) {
    val checkboxesState = remember { mutableStateListOf(
        false, false, false, false, false, false, false, false, false,
        false, false, false, false, false, false, false, false, false,
        false, false, false, false, false, false, false, false, false,
        false, false, false, false, false, false, false, false, false,
        false, false, false, false, false, false, false, false, false,
        false, false, false, false, false, false, false, false, false,
        false, false, false, false, false, false, false, false, false
    )}

    // Days of the week
    val daysOfWeek = listOf("Segunda-Feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sábado", "Domingo")

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
            ) {

                Column {
                    Column(
                        modifier = Modifier.padding(start = 14.dp).padding(bottom = 4.dp).padding(top = 10.dp)
                    ) {// Text(text = "Estou disponivel")
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 5.dp)
                                .padding(start =35.dp),
                            horizontalArrangement = Arrangement.Start,
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
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 6.dp), // Add space below buttons
                            horizontalArrangement = Arrangement.Center
                        ) {

                            Button(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.padding(end = 6.dp),
                                shape = RoundedCornerShape(7.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(7, 125, 176),
                                    contentColor = Color.White
                                ),
                            ) {
                                Text(text = "Regularmente")
                            }
                            Button(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.padding(start = 2.dp),
                                shape = RoundedCornerShape(7.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(7, 125, 176),
                                    contentColor = Color.White
                                ),
                            ) {
                                Text(text = "Ocasionalmente")
                            }
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 2.dp)
                            .padding(end =7.dp),
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
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(17.dp),
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




@Preview(showBackground = true)
@Composable
fun GreetingPreview7() {
    MobileSeniorCareTheme {
        Greeting6("Android")
    }
}