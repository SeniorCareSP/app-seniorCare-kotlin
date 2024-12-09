package com.example.mobileseniorcare.telas

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobileseniorcare.R
import com.example.mobileseniorcare.api.SeniorCareViewModel
import com.example.mobileseniorcare.dataclass.usuario.UsuarioCuidador
import com.example.mobileseniorcare.telas.cadastro.CadastroIdoso
import com.example.mobileseniorcare.ui.theme.MobileSeniorCareTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: SeniorCareViewModel = SeniorCareViewModel()

        setContent {
            MobileSeniorCareTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "main",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("main") {
                            TelaInicial(navController)
                        }
                        composable("cadastro1") {
                            Cadastro1(navController, viewModel)
                        }
                        composable("cadastro2") {
                                Cadastro2(navController, viewModel)
                        }
                        composable("cadastro3") {
                                Cadastro3(navController, viewModel)
                        }
                        composable("cadastro4") {
                                Cadastro4(navController, viewModel)

                        }
                        composable("cadastro5") {
                                Cadastro5(navController, viewModel)

                        }
                        composable("cadastro6") {
                                Cadastro6(navController,viewModel)

                        }
                        composable("login") {
                           LoginSenior(navController)

                        }
                        composable("telaMain") { // Rota que vai abrir a MainActivity2
                            val context = LocalContext.current
                            LaunchedEffect(Unit) {
                                context.startActivity(Intent(context, MainActivity2::class.java))
                            }
                        }
                        composable("novoidoso") {
                            CadastroIdoso()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TelaInicial(navController: NavHostController, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF077DB0)) // Fundo azul
    ) {
        // Parte superior azul com logo e texto
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
            verticalArrangement = Arrangement.Top
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
                    onClick = { navController.navigate(route = "cadastro1") },
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
                    onClick = { navController.navigate(route = "Login") },
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

                    withStyle(style = SpanStyle(color = Color(0xFF077DB0), fontWeight = FontWeight.Bold)) {
                        pushStringAnnotation(tag = "terms", annotation = "terms")
                        append("Termos de uso")
                        pop()
                    }

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
        TelaInicial(rememberNavController())
    }
}
