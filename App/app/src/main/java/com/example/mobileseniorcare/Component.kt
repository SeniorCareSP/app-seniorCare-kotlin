package com.example.mobileseniorcare

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Card(modifier: Modifier = Modifier){
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
            Image(
                painter = painterResource(id = R.drawable.idoso),
                contentDescription = "chuu do loona",
                contentScale = ContentScale.Crop,
                modifier = imagemMod
            )
            Spacer(modifier = Modifier. width(20.dp))
            Text(
                "Senhor Elias precisa de ajuda com as tarefas do dia a dia",
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
                    "Elias", textAlign =  TextAlign.Center, modifier = infoUsu
                )
                Text(
                    "75 anos", textAlign =  TextAlign.Center, modifier = infoUsu
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
fun CardCuidador(modifier: Modifier = Modifier){
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
            Image(
                painter = painterResource(id = R.drawable.jovem),
                contentDescription = "chuu do loona",
                contentScale = ContentScale.Crop,
                modifier = imagemMod
            )
            Spacer(modifier = Modifier. width(20.dp))
            Text(
                "Estou cursando enfermagem a 1 ano e estou a procura de um trabalho",
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
                    "Jonas", textAlign =  TextAlign.Center, modifier = infoUsu
                )
                Text(
                    "18 anos", textAlign =  TextAlign.Center, modifier = infoUsu
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