package com.khedr.ecobot.ui.presentaion.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.khedr.ecobot.R

class DetailsScreen (val navigator: Navigator): Screen {
    @Composable
    override fun Content() {
        DetailsContent()
    }

    @Composable
    fun DetailsContent() {

        val gradient = Brush.verticalGradient(
            colors = listOf( Color.White ,colorResource(id = R.color.app_color),Color.White)
        )
        Column (
            modifier = Modifier.background(gradient)
        ){
            TopBarAdvice(onBackClick = { navigator.pop() },
                title = "Garbage Details ")
            LazyColumn {
                items(330) {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        shape = RoundedCornerShape(12.dp),
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.img),
                                contentDescription = null,
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp)
                                    .clip(RoundedCornerShape(8.dp))
                            )
                            Spacer(modifier = Modifier.height(12.dp))

                            Row {
                                Row (
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Text(text = "Type :", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                    Text(text = " Plastic", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

                                }
                                Spacer(modifier = Modifier.weight(1f))

                                Text(text = "Mansoura", fontSize = 16.sp, color = Color.Black)
                            }


                            Spacer(modifier =Modifier.weight(1f))


                        }


                    }

                }
            }

        }
    }
}




@Composable
fun TopBarAdvice(
    onBackClick: () -> Unit,
    title :String ?= null,
){
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BackButton {
            onBackClick()
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = title?:"",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,

            color = colorResource(id = R.color.app_color)
        )
        Spacer(modifier = Modifier.weight(1f))


        Icon(
            painterResource(id = R.drawable.img),
            modifier = Modifier.size(30.dp),contentDescription = "Back", tint = Color.Transparent
        )
    }
}


