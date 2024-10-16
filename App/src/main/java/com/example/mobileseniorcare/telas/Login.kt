package com.example.mobileseniorcare.telas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobileseniorcare.api.SeniorCareViewModel
import com.example.mobileseniorcare.ui.theme.MobileSeniorCareTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileSeniorCareTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(modifier = Modifier.padding(innerPadding), activity = this)
                }
            }
        }
    }
}
@Composable
fun LoginScreen(modifier: Modifier = Modifier, activity: ComponentActivity) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    val seniorCareViewModel: SeniorCareViewModel = viewModel()

    val labelColor = Color(0xFF000000)
    val borderColor = Color(0xFF077DB0)
    val buttonBackgroundColor = Color(0xFF077DB0)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email", color = labelColor) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text(text = "Senha", color = labelColor) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor
            )
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    seniorCareViewModel.login(email, senha)

                    // Verificando o estado do login após a função login ser chamada
                    seniorCareViewModel.usuarioLogado.value?.let {
                        // Login bem-sucedido, prossiga para a próxima tela
                        activity.runOnUiThread {
                            Toast.makeText(activity, "Login bem-sucedido!", Toast.LENGTH_SHORT)
                                .show()
                            // Exemplo: atividade para ir para a tela principal
                            activity.startActivity(Intent(activity, MainActivity2::class.java))
                        }
                    } ?: run {
                        // Falha no login
                        activity.runOnUiThread {
                            Toast.makeText(
                                activity,
                                "Falha no login. Verifique suas credenciais.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            content = {
                Text(text = "Login", color = Color.White)
            }
        )
    }
    }


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MobileSeniorCareTheme {
        LoginScreen(activity = ComponentActivity()) // Preenchendo com uma activity de exemplo
    }
}
