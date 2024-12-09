package com.example.mobileseniorcare.telas

import ListagemViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.mobileseniorcare.R
import com.example.mobileseniorcare.dataclass.usuario.UsuarioCuidador
import com.example.mobileseniorcare.dataclass.usuario.UsuarioResponse
import com.example.mobileseniorcare.dataclass.usuario.UsuarioTokenDto
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject


@Composable
fun CardUsuario( usuarioCuidador: UsuarioResponse ,  modifier: Modifier = Modifier,){

  // val imagem = usuarioCuidador.imagemUrl

    Column (

    ){
        Row (
            modifier = Modifier
                .background(
                    color = Color(0xff95BBCB),
                    shape = RoundedCornerShape(16)
                )
                .width(500.dp)
                .height(100.dp)
        ){
            val imagemMod = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White, shape = RoundedCornerShape(16))

            AsyncImage(
                modifier = imagemMod,
                model = "${usuarioCuidador.imagemUrl}",
                contentDescription = "Translated description of what the image contains"
            )

            Spacer(modifier = Modifier. width(20.dp))
            Text(
                usuarioCuidador.apresentacao ?: "Apresentação",
                modifier = Modifier.width(220.dp),

                )
        }
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        Row {
            Column() {
                val infoUsu = Modifier.width(70.dp)
                Text(
                    usuarioCuidador.nome ?: "Nome não informado", textAlign =  TextAlign.Center, modifier = infoUsu
                )
                Text("Idade: ${usuarioCuidador.dtNascimento ?: "Desconhecida"}", textAlign =  TextAlign.Center, modifier = infoUsu
                )
            }
            Spacer(modifier = Modifier.width(150.dp))
            Button(
                onClick = { /* Ação de próximo */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2C7595)
                ),
                modifier = Modifier
                    .height(35.dp)
            ) {
                Text(
                    "Ver detalhes"
                )
            }
        }
    }
}



@Composable
fun CardConversa(){
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
}