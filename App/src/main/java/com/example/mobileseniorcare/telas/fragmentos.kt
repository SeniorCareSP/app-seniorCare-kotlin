package com.example.mobileseniorcare.telas

import ListagemViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
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
fun EdicaoPerfilTela( navController: NavHostController,sessao: UsuarioTokenDto, viewModel: SeniorCareViewModel = viewModel(), modifier: Modifier = Modifier) {
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var textoSobre by remember { mutableStateOf("") }
    var logradouro by remember { mutableStateOf("") }
    var cep by remember { mutableStateOf("") }
    var bairro by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }
    var cidade by remember { mutableStateOf("") }

    val (selectedOptions, setSelectedOptions) = remember  { mutableStateOf(setOf<String>()) }

    val scrollState = rememberScrollState()
    val borderColor = Color(0xFF077DB0)
    val buttonBackgroundColor = Color(0xFF077DB0)


    LaunchedEffect(Unit) {
        val usuario = viewModel.getUsuario()

        if (usuario != null) {
            nome = usuario.nome ?: ""
            email = usuario.email ?: ""
            textoSobre = usuario.apresentacao ?: ""
            logradouro = usuario.endereco?.logradouro ?: ""
            cep = usuario.endereco?.cep ?: ""
            bairro = usuario.endereco?.bairro ?: ""
            numero = usuario.endereco?.numero ?: ""
            cidade = usuario.endereco?.cidade ?: ""
        }
    }



    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = buttonBackgroundColor)
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)) {
            TopSections()

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp))
                    .padding(20.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    BackButtons()
                    Spacer(modifier = Modifier.height(20.dp))
                    HeaderTexts(stringResource(id = R.string.edicao_perfil))

                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        UserProfileImages()
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    InputFields1(
                        nome = nome,
                        onNomeChange = { nome = it },
                        email = email,
                        onEmailChange = { email = it },
                        textoSobre = textoSobre,
                        onTextoSobreChange = { textoSobre = it },
                        logradouro = logradouro,
                        onLogradouroChange = { logradouro = it },
                        cep = cep,
                        onCepChange = { cep = it },
                        bairro = bairro,
                        onBairroChange = { bairro = it },
                        numero = numero,
                        onNumeroChange = { numero = it },
                        cidade = cidade,
                        onCidadeChange = { cidade = it }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Idosos Cadastrados:",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF077DB0),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Left,
                        fontSize = 18.sp,
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .border(1.dp, Color(0xFF077DB0), RoundedCornerShape(10.dp))
                            .background(Color.White, RoundedCornerShape(10.dp))
                    ) {
                        data class Idoso(val nome: String, val idade: Int)
                        var idosos by remember { mutableStateOf(listOf<Idoso>()) }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .border(1.dp, Color(0xFF077DB0), RoundedCornerShape(10.dp))
                                .background(Color.White, RoundedCornerShape(10.dp))
                                .padding(16.dp)
                        ) {
                            Column {
                                idosos.forEach { idoso ->
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(text = "${idoso.nome}, ${idoso.idade} anos", modifier = Modifier.weight(1f))

                                        IconButton(onClick = { /* Ação para editar */ }) {
                                            Icon(
                                                imageVector = Icons.Filled.Edit, // Ícone de lápis
                                                contentDescription = "Editar"
                                            )
                                        }

                                        IconButton(onClick = { /* Ação para excluir */ }) {
                                            Icon(
                                                imageVector = Icons.Filled.Delete, // Ícone de lixeira
                                                contentDescription = "Excluir"
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(8.dp)) // Espaço entre os itens
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {  navController.navigate("novoIdoso") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .border(1.dp, borderColor, shape = RoundedCornerShape(10.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = buttonBackgroundColor)
            ) {
                Text(stringResource(R.string.botao_novo_idoso))
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Preciso de ajuda com:",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp),
                color = Color.White,
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
            )

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp) // Aumentei o espaço entre as opções
            ) {
                val options = listOf("Trabalho de Casa", "Culinária", "Banho", "Outros")

                options.chunked(2).forEach { rowOptions ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        rowOptions.forEach { option ->
                            Chips(
                                text = option,
                                isSelected = selectedOptions.contains(option),
                                onClick = {
                                    if (selectedOptions.contains(option)) {
                                        setSelectedOptions(selectedOptions - option) // Remove a opção
                                    } else {
                                        setSelectedOptions(selectedOptions + option) // Adiciona a opção
                                    }
                                }
                            )
                            Spacer(modifier = Modifier.width(16.dp)) // Adicionando espaçamento entre os chips
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Informe o valor que está disposto a pagar",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp),
                color = Color.White,
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
            )

            Spacer(modifier = Modifier.height(16.dp))

            var valorHora by remember { mutableStateOf("") }

            TextField(
                value = valorHora,
                onValueChange = { valorHora = it },
                label = { Text("Valor por hora") },
                leadingIcon = {
                    Text(
                        text = "R$",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray) // Exibindo "R$" no campo
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp) // Espaço ao redor do TextField
            )
            Button(
                onClick = { /* Ação para salvar alterações */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .border(1.dp, borderColor, shape = RoundedCornerShape(10.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = buttonBackgroundColor)
            ) {
                Text(stringResource(R.string.botao_salvar_alteracoes))
            }
        }
    }
}








@Composable
fun Chips(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) Color(0xFF077DB0) else Color(0xFFE0E0E0)
    val textColor = if (isSelected) Color.White else Color.Black

    Surface(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() },
        color = backgroundColor,
        contentColor = textColor,
        tonalElevation = 2.dp
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun TopSections() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            painter = painterResource(id = R.drawable.logo_mobile),
            contentDescription = stringResource(id = R.string.slogan_inicial),
            modifier = Modifier.size(150.dp)
        )
    }
}

@Composable
fun BackButtons() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        Image(
            painter = painterResource(id = R.drawable.seta),
            contentDescription = stringResource(id = R.string.button_voltar_perfil),
            modifier = Modifier.size(35.dp)
        )
        Text(text = stringResource(id = R.string.button_voltar_perfil), modifier = Modifier.padding(start = 12.dp))
    }
}

@Composable
fun HeaderTexts(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp), // Reduzindo o tamanho da fonte
        color = Color(0xFF077DB0),
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun UserProfileImages() {
    Image(
        painter = painterResource(id = R.drawable.img_perfil_cuidador),
        contentDescription = stringResource(id = R.string.imagem_perfil),
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .border(2.dp, Color.Gray, CircleShape)
    )
}

@Composable
fun InputFields1(
    nome: String, onNomeChange: (String) -> Unit,
    email: String, onEmailChange: (String) -> Unit,
    textoSobre: String, onTextoSobreChange: (String) -> Unit,
    logradouro: String, onLogradouroChange: (String) -> Unit,
    cep: String, onCepChange: (String) -> Unit,
    bairro: String, onBairroChange: (String) -> Unit,
    numero: String, onNumeroChange: (String) -> Unit,
    cidade: String, onCidadeChange: (String) -> Unit
) {

    TextField(
        value = nome,
        onValueChange = onNomeChange,
        label = { Text("Nome") },
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(8.dp))

    TextField(
        value = email,
        onValueChange = onEmailChange,
        label = { Text("Email") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    TextField(
        value = textoSobre,
        onValueChange = onTextoSobreChange,
        label = { Text("Sobre") },
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    )

    Spacer(modifier = Modifier.height(8.dp))

    TextField(
        value = logradouro,
        onValueChange = onLogradouroChange,
        label = { Text("Logradouro") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    TextField(
        value = cep,
        onValueChange = onCepChange,
        label = { Text("CEP") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    TextField(
        value = bairro,
        onValueChange = onBairroChange,
        label = { Text("Bairro") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    TextField(
        value = numero,
        onValueChange = onNumeroChange,
        label = { Text("Número") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    TextField(
        value = cidade,
        onValueChange = onCidadeChange,
        label = { Text("Cidade") },
        modifier = Modifier.fillMaxWidth()
    )
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
    ) {
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
                Spacer(
                    modifier = Modifier
                        .width(16.dp)
                        .padding(start = 15.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.icone_tarde),
                    contentDescription = "Icon 2",
                    modifier = Modifier.size(34.dp)
                )
                Spacer(
                    modifier = Modifier
                        .width(16.dp)
                        .padding(start = 15.dp)
                )
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
fun EdicaoPerfilCuidadorScreen(navController: NavHostController, viewModel: SeniorCareViewModel = viewModel(), modifier: Modifier = Modifier) {
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var textoSobre by remember { mutableStateOf("") }
    var logradouro by remember { mutableStateOf("") }
    var cep by remember { mutableStateOf("") }
    var bairro by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }
    var cidade by remember { mutableStateOf("") }

    val (selectedOptions, setSelectedOptions) = remember { mutableStateOf(setOf<String>()) }

    val scrollState = rememberScrollState()
    val borderColor = Color(0xFF077DB0)
    val buttonBackgroundColor = Color(0xFF077DB0)

    LaunchedEffect(Unit) {
        val usuario = viewModel.getUsuario()

        if (usuario != null) {
            nome = usuario.nome ?: ""
            email = usuario.email ?: ""
            textoSobre = usuario.apresentacao ?: ""
            logradouro = usuario.endereco?.logradouro ?: ""
            cep = usuario.endereco?.cep ?: ""
            bairro = usuario.endereco?.bairro ?: ""
            numero = usuario.endereco?.numero ?: ""
            cidade = usuario.endereco?.cidade ?: ""
        }
    }


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = buttonBackgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            TopSectionCuidador()

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)
                    )
                    .padding(20.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    BackButtons()
                    Spacer(modifier = Modifier.height(20.dp))
                    HeaderTexts(stringResource(id = R.string.edicao_perfil))

                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        UserProfileImages()
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    InputFieldsCuidador(
                        nome = nome,
                        onNomeChange = { nome = it },
                        email = email,
                        onEmailChange = { email = it },
                        textoSobre = textoSobre,
                        onTextoSobreChange = { textoSobre = it },
                        logradouro = logradouro,
                        onLogradouroChange = { logradouro = it },
                        cep = cep,
                        onCepChange = { cep = it },
                        bairro = bairro,
                        onBairroChange = { bairro = it },
                        numero = numero,
                        onNumeroChange = { numero = it },
                        cidade = cidade,
                        onCidadeChange = { cidade = it }
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Posso ajudar com:",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 18.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Left,
                        fontSize = 16.sp,
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        val options = listOf("Trabalho de Casa", "Culinária", "Banho", "Outros")

                        options.chunked(2).forEach { rowOptions ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                rowOptions.forEach { option ->
                                    ChipCuidador(
                                        text = option,
                                        isSelected = selectedOptions.contains(option),
                                        onClick = {
                                            setSelectedOptions(
                                                if (selectedOptions.contains(option)) {
                                                    selectedOptions - option
                                                } else {
                                                    selectedOptions + option
                                                }
                                            )
                                        }
                                    )
                                    Spacer(modifier = Modifier.width(1.dp))
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Informe seu valor hora",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 18.dp),
                        color = Color.Black,
                        textAlign = TextAlign.Left,
                        fontSize = 16.sp,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    var valorHora by remember { mutableStateOf("") }

                    TextField(
                        value = valorHora,
                        onValueChange = { valorHora = it },
                        label = { Text("Valor por hora") },
                        leadingIcon = {
                            Text(
                                text = "R$",
                                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                            )
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { /* Ação para salvar alterações */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFF077DB0))
                            .padding(16.dp)
                    ) {
                        Text(stringResource(R.string.botao_salvar_alteracoes))
                    }
                }
            }
        }
    }
}

// Função de Chip para seleção
@Composable
fun ChipCuidador(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) Color(0xFF077DB0) else Color(0xFFE0E0E0)
    val textColor = if (isSelected) Color.White else Color.Black
    Surface(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .clickable(onClick = onClick)
            .background(backgroundColor)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        color = backgroundColor
    ) {
        Text(text = text, color = textColor, style = MaterialTheme.typography.bodyMedium)
    }
}

// Seção superior do perfil
@Composable
fun TopSectionCuidador() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            painter = painterResource(id = R.drawable.logo_mobile),
            contentDescription = "Logo Mobile Senior Care",
            modifier = Modifier.size(150.dp)
        )
    }
}

// Função para os campos de input
@Composable
fun InputFieldsCuidador(
    nome: String,
    onNomeChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    textoSobre: String,
    onTextoSobreChange: (String) -> Unit,
    logradouro: String,
    onLogradouroChange: (String) -> Unit,
    cep: String,
    onCepChange: (String) -> Unit,
    bairro: String,
    onBairroChange: (String) -> Unit,
    numero: String,
    onNumeroChange: (String) -> Unit,
    cidade: String,
    onCidadeChange: (String) -> Unit
) {
    Column {
        TextField(
            value = nome,
            onValueChange = onNomeChange,
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = textoSobre,
            onValueChange = onTextoSobreChange,
            label = { Text("Sobre você") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = logradouro,
            onValueChange = onLogradouroChange,
            label = { Text("Logradouro") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = cep,
            onValueChange = onCepChange,
            label = { Text("CEP") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = bairro,
            onValueChange = onBairroChange,
            label = { Text("Bairro") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = numero,
            onValueChange = onNumeroChange,
            label = { Text("Número") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = cidade,
            onValueChange = onCidadeChange,
            label = { Text("Cidade") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}


