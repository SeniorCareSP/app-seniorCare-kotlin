package com.example.mobileseniorcare.telas

import ListagemViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mobileseniorcare.R
import com.example.mobileseniorcare.api.IdosoViewModel
import com.example.mobileseniorcare.api.SeniorCareViewModel
import com.example.mobileseniorcare.dataclass.Idoso
import com.example.mobileseniorcare.dataclass.TipoUsuario
import com.example.mobileseniorcare.dataclass.usuario.UsuarioCuidador
import com.example.mobileseniorcare.dataclass.usuario.UsuarioTokenDto
import java.time.LocalDate
import java.time.format.DateTimeFormatter


fun String?.toTipoUsuario(): TipoUsuario? {
    return try {
        // Converte a string para o valor correspondente de TipoUsuario, se for válido
        TipoUsuario.valueOf(this ?: "")
    } catch (e: IllegalArgumentException) {
        // Retorna null caso a string não seja um valor válido de TipoUsuario
        null
    }
}


fun UsuarioTokenDto.obterUserId(): Int? {
    return this.userId
}


@Composable
fun ListagemUsuarios(sessaoUsuario: UsuarioTokenDto ,modifier: Modifier = Modifier, viewModel: ListagemViewModel = viewModel()) {


    // Converte a string 'tipoUsuario' para o tipo TipoUsuario usando a função de extensão
    val tipoUsuario = sessaoUsuario.tipoUsuario.toTipoUsuario()
    val userId = sessaoUsuario.obterUserId()



    if (tipoUsuario != null && userId != null) {
        viewModel.carregarUsuarios(tipoUsuario)
    }



    val usuariosExibidos = viewModel.usuariosExibidos


    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF077DB0)) // Fundo azul
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.15f), // 35% para a área azul
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
                .fillMaxHeight(1.00f)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)
                )
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top // Conteúdo mais para baixo
        ) {

            usuariosExibidos.forEach { usuario ->
                CardUsuario(usuario)
                Spacer(modifier = Modifier.height(10.dp))
            }
            if (usuariosExibidos.isEmpty()) {
                Text("Nenhum usuário encontrado", modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Composable
fun ListagemConversa(modifier: Modifier = Modifier) {
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
                    .verticalScroll(rememberScrollState())
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                CardConversa()
            }
        }
    }


}

@Composable
fun CadastroIdo(
    navController: NavHostController,
    viewModel: IdosoViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
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
                .align(Alignment.BottomCenter)
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


                        viewModel.cadastrarIdoso(
                            idoso,
                            onSuccess = {
                                isLoading = false
                                navController.navigate("next_screen")
                            },
                            onError = { message ->
                                isLoading = false
                                errorMessage = message
                            }
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
                    navController.popBackStack()
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
}



