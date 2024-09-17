package com.example.mobileseniorcare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobileseniorcare.ui.theme.MobileSeniorCareTheme

class ListagemResponsavel : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileSeniorCareTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ListagemR(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun ListagemR(name: String, modifier: Modifier = Modifier) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF077DB0)) // Fundo azul
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.35f), // 35% para a área azul
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top // Logo mais pra cima
        ) {
            Spacer(modifier = Modifier.height(32.dp)) // Espaço para mover a logo mais para cima
            Image(
                painter = painterResource(id = R.drawable.logo_mobile), // Substitua pelo seu recurso de imagem
                contentDescription = "Logo do Mobile Senior Care",
                modifier = Modifier.size(150.dp), // Ajuste do tamanho da imagem
            )
        }

        // Parte inferior branca com campos de login e botões
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f) // A parte branca ocupará 75% da tela
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
            verticalArrangement = Arrangement.Top // Conteúdo mais para baixo
        ) {
            BottomAppBarExample()
        }
    }
}
@Composable
fun BottomAppBarExample() {

    Scaffold(
        modifier = Modifier
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(16)
            ),
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xff077DB0),
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                ,
                actions = {
                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.width(500.dp)) {
                        val iconMod = Modifier.size(40.dp)
                        IconButton(onClick = { /* do something */ }) {
                            Icon(
                                painter = painterResource(R.drawable.chat),
                                contentDescription = "Localized description",
                                modifier = iconMod,
                                Color.White
                            )
                        }
                        IconButton(onClick = { /* do something */ }) {
                            Icon(
                                painter = painterResource(R.drawable.home),
                                contentDescription = "Localized description",
                                modifier = iconMod,
                                Color.White
                            )
                        }
                        IconButton(onClick = { /* do something */ }) {
                            Icon(
                                painter = painterResource(R.drawable.logout),
                                contentDescription = "Localized description",
                                modifier = iconMod,
                                Color.White
                            )
                        }
                    }
                }
            )
        },
    ) { innerPadding ->
        Column ( modifier = Modifier.padding(innerPadding)
            .verticalScroll(rememberScrollState())
        ){
            Card()
            Card()
            Card()
            Card()
            Card()
            Card()
            Card()
        }

        Text(
            modifier = Modifier.padding(innerPadding),
            text = ""
        )
    }
}
@Preview(showBackground = true)
@Composable
fun ListagemR() {
    MobileSeniorCareTheme {
        ListagemR("Android")
    }
}
