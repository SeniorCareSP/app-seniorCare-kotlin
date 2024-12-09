package com.example.mobileseniorcare.telas

import ListagemViewModel
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.mobileseniorcare.R
import com.example.mobileseniorcare.api.SeniorCareViewModel
import com.example.mobileseniorcare.dataclass.TipoUsuario
import com.example.mobileseniorcare.dataclass.usuario.UsuarioCuidador
import com.example.mobileseniorcare.dataclass.usuario.UsuarioTokenDto


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


// tela de visualização de perfil


@Composable
fun VisuPerfil(name: String, modifier: Modifier = Modifier) {
    var disponibilidade by remember { mutableStateOf(Array(7) { Array(3) { false } }) }

    val checkboxesState = remember {
        mutableStateListOf(
            false, false, false,
            false, false, false,
            false, false, false,
            false, false, false,
            false, false, false,
            false, false, false,
            false, false, false,
            false, false, false,
        )
    }
    var alertDialogVisible by remember { mutableStateOf(false) }
    // Days of the week
    val daysOfWeek = listOf(
        "Segunda-Feira",
        "Terça-feira",
        "Quarta-feira",
        "Quinta-feira",
        "Sexta-feira",
        "Sábado",
        "Domingo"
    )

    Column(
        modifier.background(
            color = Color.Gray
        )
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.seta),
                    contentDescription = "seta voltar",
                    Modifier.size(70.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1.00f)

                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            val imagemMod = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White, shape = RoundedCornerShape(100))
            AsyncImage(
                model = "https://i.mydramalist.com/d8jkd_5f.jpg",
                contentDescription = "chuu",
                modifier = imagemMod
            )

            Text("Nome usu", fontSize = 25.sp, fontWeight = FontWeight.Black)

            Text("Idade: 120 anos")

            Text("descrição do usuario")

            Text("Posso ajudar com:")


            // calendario fudeo
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp)
                    .padding(end = 7.dp),
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
                Spacer(modifier = Modifier
                    .width(16.dp)
                    .padding(start = 15.dp))
                Image(
                    painter = painterResource(id = R.drawable.icone_tarde),
                    contentDescription = "Icon 2",
                    modifier = Modifier.size(34.dp)
                )
                Spacer(modifier = Modifier
                    .width(16.dp)
                    .padding(start = 15.dp))
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

        }



    }

}

