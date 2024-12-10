package com.example.mobileseniorcare.telas

import ListagemViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobileseniorcare.R
import com.example.mobileseniorcare.dataclass.Idoso
import com.example.mobileseniorcare.dataclass.TipoUsuario
import com.example.mobileseniorcare.dataclass.usuario.UsuarioTokenDto
import com.example.mobileseniorcare.telas.ui.theme.MobileSeniorCareTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Favoritos : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileSeniorCareTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting2(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting2( name: String, modifier: Modifier = Modifier, viewModel: ListagemViewModel = viewModel()) {
    var nome by remember { mutableStateOf("") }
    var idade by remember { mutableStateOf("") }
    var doencasCronicas by remember { mutableStateOf("") }
    var precisaDeAjudaCom by remember { mutableStateOf(false) }
    var descricao by remember { mutableStateOf("") }
    var mobilidade by remember { mutableStateOf(false) }
    var lucido by remember { mutableStateOf(false) }
    var cuidadosMin by remember { mutableStateOf(false) }
    var dtNascimento by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var sobre by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var id by remember { mutableStateOf(null) }
    var responsavel by remember { mutableStateOf(null) } // ID do responsável



    val labelColor = Color(0xFF000000)
    val borderColor = Color(0xFF077DB0)
    val buttonBackgroundColor = Color(0xFF077DB0)
    val buttonTextColor = Color.White
    val textColor = Color.Black

    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")


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

    // Scrollable Column
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.75f)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)
            )
            .padding(20.dp)
            .verticalScroll(scrollState), // Scrollable Modifier
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        // Nome
        OutlinedTextField(
            label = { Text("Nome", color = labelColor) },
            value = nome,
            onValueChange = { nome = it },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor,
                focusedTextColor = textColor,
                unfocusedTextColor = textColor
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Idade
        OutlinedTextField(
            label = { Text("Idade", color = labelColor) },
            value = idade,
            onValueChange = { idade = it },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor,
                focusedTextColor = textColor,
                unfocusedTextColor = textColor
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Doenças Crônicas
        OutlinedTextField(
            label = { Text("Doenças Crônicas", color = labelColor) },
            value = doencasCronicas,
            onValueChange = { doencasCronicas = it },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor,
                focusedTextColor = textColor,
                unfocusedTextColor = textColor
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Precisa de ajuda
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Precisa de ajuda?", color = textColor)
            Spacer(modifier = Modifier.width(8.dp))
            Checkbox(
                checked = precisaDeAjudaCom,
                onCheckedChange = { precisaDeAjudaCom = it }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Descrição
        OutlinedTextField(
            label = { Text("Descrição", color = labelColor) },
            value = descricao,
            onValueChange = { descricao = it },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor,
                focusedTextColor = textColor,
                unfocusedTextColor = textColor
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Mobilidade
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Mobilidade", color = textColor)
            Spacer(modifier = Modifier.width(8.dp))
            Checkbox(
                checked = mobilidade,
                onCheckedChange = { mobilidade = it }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Lúcido
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Lúcido", color = textColor)
            Spacer(modifier = Modifier.width(8.dp))
            Checkbox(
                checked = lucido,
                onCheckedChange = { lucido = it }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Cuidados mínimos
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Cuidados Mínimos", color = textColor)
            Spacer(modifier = Modifier.width(8.dp))
            Checkbox(
                checked = cuidadosMin,
                onCheckedChange = { cuidadosMin = it }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Data de Nascimento
        OutlinedTextField(
            label = { Text("Data de Nascimento", color = labelColor) },
            value = dtNascimento,
            onValueChange = { dtNascimento = it },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor,
                focusedTextColor = textColor,
                unfocusedTextColor = textColor
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Gênero
        OutlinedTextField(
            label = { Text("Gênero", color = labelColor) },
            value = genero,
            onValueChange = { genero = it },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor,
                focusedTextColor = textColor,
                unfocusedTextColor = textColor
            )
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (nome.isNotEmpty() && idade.isNotEmpty()) {
                    isLoading = true

                    // Convertendo a string dtNascimento para LocalDate
                    val parsedDate = try {
                        LocalDate.parse(dtNascimento, dateFormatter)
                    } catch (e: Exception) {
                        null
                    }

                    val idoso = Idoso(
                        id = id,
                        nome = nome,
                        idade = idade.toInt(),
                        doencasCronicas = doencasCronicas,
                        descricao = descricao,
                        mobilidade = mobilidade,
                        lucido = lucido,
                        cuidadosMin = cuidadosMin,
                        dtNascimento = parsedDate,
                        genero = genero,
                        sobre = sobre,
                        responsavel = responsavel,
                        dtNasc = dtNascimento
                    )



                } else {
                    errorMessage = "Preencha todos os campos obrigatórios"
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonBackgroundColor,
                contentColor = buttonTextColor
            )
        ) {
            if (isLoading) {
                Text("Carregando...")
            } else {
                Text("Salvar Alterações")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        errorMessage?.let {
            Text(it, color = Color.Red, modifier = Modifier.padding(top = 10.dp))
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, borderColor, RoundedCornerShape(10.dp))
                .height(36.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = borderColor
            )
        ) {
            Text("Cancelar", color = borderColor)
        }

        Spacer(modifier = Modifier.height(10.dp))
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    MobileSeniorCareTheme {
        Greeting2("Android")
    }
}