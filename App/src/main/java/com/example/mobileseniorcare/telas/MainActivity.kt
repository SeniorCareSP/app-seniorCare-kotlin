package com.example.mobileseniorcare.telas

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.ClickableText
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.mobileseniorcare.R
import com.example.mobileseniorcare.telas.cadastro.Cadastro1
import com.example.mobileseniorcare.telas.cadastro.Cadastros
import com.example.mobileseniorcare.ui.theme.MobileSeniorCareTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileSeniorCareTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TelaInicial(
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
fun TelaInicial(name: String, modifier: Modifier = Modifier, activity: ComponentActivity) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF077DB0)) // Fundo azul
    ) {
        // Parte superior azul com logo e texto
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
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top // Conteúdo mais para baixo
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(id = R.string.slogan_inicial),
                color = Color.Black,
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Normal
                )
            )
            Spacer(modifier = Modifier.height(60.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = {  activity.startActivity(Intent(activity, Cadastro1::class.java)) },
                    modifier = Modifier
                        .width(150.dp)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF077DB0)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Cadastre-se",
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Button(
                    onClick = {
                        activity.startActivity(Intent(activity, Login::class.java))
                    },
                    modifier = Modifier
                        .width(150.dp)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF077DB0)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Login",
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Texto com links clicáveis
                val annotatedString = buildAnnotatedString {
                    append("Ao assinar, você aceita nossos ")

                    // Adiciona "Termos de uso" em azul
                    withStyle(style = SpanStyle(color = Color(0xFF077DB0), fontWeight = FontWeight.Bold)) {
                        pushStringAnnotation(tag = "terms", annotation = "terms")
                        append("Termos de uso")
                        pop()
                    }

                    // Adiciona "e política de privacidade" em azul
                    withStyle(style = SpanStyle(color = Color(0xFF077DB0), fontWeight = FontWeight.Bold)) {
                        pushStringAnnotation(tag = "privacy", annotation = "privacy")
                        append(" e política de privacidade")
                        pop()
                    }
                }

                ClickableText(
                    text = annotatedString,
                    onClick = { offset ->
                        annotatedString.getStringAnnotations(start = offset, end = offset)
                            .firstOrNull()?.let { annotation ->
                                when (annotation.tag) {
                                    "terms" -> {
                                        // Ação para "Termos de uso"
                                    }
                                    "privacy" -> {
                                        // Ação para "política de privacidade"
                                    }
                                }
                            }
                    },
                    modifier = Modifier.padding(top = 16.dp),
                    style = TextStyle(textAlign = TextAlign.Center)
                )

                Spacer(modifier = Modifier.height(36.dp))
                Text(
                    text = "Problemas para entrar?",
                    modifier = Modifier
                        .clickable { /* Ação */ }
                        .padding(top = 16.dp),

                    textAlign = TextAlign.Center,
                    color = Color(0xFF077DB0)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TelaInicialPreview() {
    MobileSeniorCareTheme {
        TelaInicial(name = "Preview" , activity = ComponentActivity())
    }
}
