package com.khedr.ecobot

import android.annotation.SuppressLint
import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.khedr.ecobot.ui.poppins
import com.khedr.ecobot.ui.presentaion.ui.DetailsScreen
import com.khedr.ecobot.ui.presentaion.ui.HomeScreen
import com.khedr.ecobot.ui.presentaion.ui.LoginScreen
import com.khedr.ecobot.ui.presentaion.ui.SettingsScreen
import com.khedr.ecobot.ui.theme.EcoBotTheme
import kotlinx.coroutines.delay
import java.util.Locale

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val locale = Locale("en")
        val config = resources.configuration
        Locale.setDefault(locale)
        config.setLocale(locale)
        config.setLayoutDirection(locale)
        resources.updateConfiguration(config, resources.displayMetrics)




        setContent {
            Navigator(screen = SplashScreen())

        }
    }
}

class MainContent : Screen {
    @Composable
    override fun Content() {
        var index by rememberSaveable { mutableStateOf(0) }

        Scaffold(
            bottomBar = {
                BottomNavigation(backgroundColor = Color.White) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        BottomNavigationItem(
                            selected = index == 0,
                            onClick = { index = 0 },
                            modifier = Modifier.weight(1f),
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.home_ic),
                                    contentDescription = null,
                                    tint = if (index == 0) colorResource(id = R.color.app_color) else Color.Black,
                                    modifier = Modifier.padding(8.dp)
                                )
                            },
                            label = {
                                Text(
                                    "Home",
                                    color = if (index == 0) colorResource(id = R.color.app_color) else Color.Black,
                                    fontSize = 12.sp,
                                    fontFamily = poppins
                                )
                            }
                        )
                        BottomNavigationItem(
                            selected = index == 1,
                            onClick = { index = 1 },
                            modifier = Modifier.weight(1f),
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.details_ic),
                                    tint = if (index == 1) colorResource(id = R.color.app_color) else Color.Black,
                                    contentDescription = null,
                                    modifier = Modifier.padding(8.dp)
                                )
                            },
                            label = {
                                Text(
                                    "Details",
                                    fontSize = 12.sp,
                                    fontFamily = poppins,
                                    color = if (index == 1) colorResource(id = R.color.app_color) else Color.Black
                                )
                            }
                        )
                        BottomNavigationItem(
                            selected = index == 2,
                            onClick = { index = 2 },
                            modifier = Modifier.weight(1f),
                            icon = {
                                Icon(
                                    Icons.Default.Settings,
                                    tint = if (index == 2) colorResource(id = R.color.app_color) else Color.Black,
                                    contentDescription = null,
                                    modifier = Modifier.padding(8.dp)
                                )
                            },
                            label = {
                                Text(
                                    "Settings",
                                    fontSize = 12.sp,
                                    fontFamily = poppins,
                                    color = if (index == 2) colorResource(id = R.color.app_color) else Color.Black
                                )
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                val navigator = LocalNavigator.currentOrThrow
                when (index) {
                    0 -> HomeScreen().Content()
                    1 -> DetailsScreen(navigator).Content()
                    2 -> SettingsScreen().Content()
                }
            }

        }
    }
}
class SplashScreen () :Screen{

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        var currentUser =FirebaseAuth.getInstance().currentUser

        LaunchedEffect(key1 = true) {
            delay(2000)
            if (currentUser != null){
                navigator.replaceAll(MainContent())
            }else{
                navigator.replaceAll(LoginScreen())
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.gray2)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "Robot Icon",
                modifier = Modifier.size(300.dp)
            )

        }
    }

}
