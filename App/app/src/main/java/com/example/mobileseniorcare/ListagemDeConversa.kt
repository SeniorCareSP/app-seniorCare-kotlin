package com.example.mobileseniorcare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobileseniorcare.ui.theme.MobileSeniorCareTheme

class ListagemDeConversa : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileSeniorCareTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting6(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting6(modifier: Modifier = Modifier) {
    var searchQuery = "";

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF077DB0))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Linha contendo as imagens
            Row {
                Image(
                    painter = painterResource(id = R.drawable.dsjak),
                    contentDescription = "Logo do Mobile Senior Care",
                    modifier = Modifier.size(100.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.senior_care),
                    contentDescription = "Logo do Mobile Senior Care",
                    modifier = Modifier.size(100.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de busca
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp) // Padding interno
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Buscar...") }, // Placeholder de busca
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp), // Padding para separar o texto da borda
                    shape = RoundedCornerShape(16.dp), // Arredondar a borda do campo de entrada
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color.Transparent, // Removendo a linha inferior padrão
                        unfocusedIndicatorColor = Color.Transparent, // Removendo a linha inferior padrão
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Adicionando o card com a imagem e o texto
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(
                            topEnd = 30.dp,
                            topStart = 30.dp
                        )
                    )
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                // Card 1
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.senior_care),
                            contentDescription = "Imagem do Chat",
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.Gray, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(8.dp)) // Espaço entre a imagem e o texto

                        Column {
                            Text(
                                text = "João Silva",
                                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                                color = Color.Black
                            )
                            Text(
                                text = "Olá, você pode me ajudar com a consulta de amanhã?",
                                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                        }
                    }
                }

                // Card 2
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.senior_care),
                            contentDescription = "Imagem do Chat",
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.Gray, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(8.dp)) // Espaço entre a imagem e o texto

                        Column {
                            Text(
                                text = "Maria Oliveira",
                                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                                color = Color.Black
                            )
                            Text(
                                text = "Bom dia! Você já conferiu a receita que enviamos?",
                                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                        }
                    }
                }

                // Card 3
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.senior_care),
                            contentDescription = "Imagem do Chat",
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.Gray, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(8.dp)) // Espaço entre a imagem e o texto

                        Column {
                            Text(
                                text = "Carlos Pereira",
                                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                                color = Color.Black
                            )
                            Text(
                                text = "Acabei de receber sua mensagem, obrigado!",
                                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                        }
                    }
                }

                // Card 4
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.senior_care),
                            contentDescription = "Imagem do Chat",
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.Gray, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(8.dp)) // Espaço entre a imagem e o texto

                        Column {
                            Text(
                                text = "Ana Costa",
                                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                                color = Color.Black
                            )
                            Text(
                                text = "Podemos agendar uma nova reunião para a próxima semana?",
                                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                        }
                    }
                }

                // Card 5
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.senior_care),
                            contentDescription = "Imagem do Chat",
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.Gray, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(8.dp)) // Espaço entre a imagem e o texto

                        Column {
                            Text(
                                text = "Roberta Lima",
                                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                                color = Color.Black
                            )
                            Text(
                                text = "Preciso de um acompanhamento sobre o último exame.",
                                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview6() {
    MobileSeniorCareTheme {
        Greeting6()
    }
}
