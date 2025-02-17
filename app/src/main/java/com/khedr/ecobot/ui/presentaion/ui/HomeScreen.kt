package com.khedr.ecobot.ui.presentaion.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.khedr.ecobot.R

class HomeScreen :Screen {
    @Composable
    override fun Content() {
        HomeContent()
    }

    @Composable
    fun HomeContent(){

        val gradient = Brush.verticalGradient(
            colors = listOf( colorResource(id = R.color.app_color),Color.White)
        )
        val navigator = LocalNavigator.currentOrThrow
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "Welcome to EcoBot",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = "Robot Icon",
                    modifier = Modifier.size(200.dp)
                )
                Spacer(modifier = Modifier.weight(0.5f))

                Button(
                    onClick = { navigator.push(ControlScreen())},
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(55.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF37C545)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "Start Robot", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.weight(0.5f))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    colors = CardDefaults.cardColors(Color.White)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Smart Beach Cleaner",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = colorResource(id = R.color.app_color)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "This app controls an AI-powered robot that cleans waste from beaches efficiently.",
                            fontSize = 14.sp,
                            color = colorResource(id = R.color.app_color),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))

            }
        }
    }

}

@Composable
fun BackButton(onClick: () -> Unit) {
    Card(
        modifier = Modifier.size(32.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFF1F4F7)),
    ) {
        IconButton(onClick = { onClick() }) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Info", tint = Color(0xFF007AFF))
        }
    }
}