package com.khedr.ecobot.ui.presentaion.ui

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.khedr.ecobot.MainContent
import com.khedr.ecobot.R
import com.khedr.ecobot.ui.poppins
import com.khedr.ecobot.ui.presentaion.viewModel.LoginViewModel

class LoginScreen:Screen {
    @Composable
    override fun Content() {
        LoginContent()

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun LoginContent() {
        var  username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        val context = LocalContext.current
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = LoginViewModel()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Login",
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppins,
                    color = colorResource(id = R.color.app_color),
                    modifier = Modifier.align(Alignment.Center)
                )
                IconButton(onClick = {
                    navigator.push(MainContent())

                }, modifier = Modifier.align(Alignment.CenterStart)) {
                    Icon(
                        Icons.Default.ArrowBack, contentDescription = null,
                        tint = colorResource(id = R.color.app_color)
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "Login Illustration",
                modifier = Modifier.size(150.dp).background(Color.White, shape = CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text ="User name",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppins,
                textAlign = TextAlign.Start,
                color = colorResource(id = R.color.black_app),
                modifier = Modifier.fillMaxSize()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = username,
                onValueChange = {username = it},
                placeholder = { Text(text = "Enter User Name",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppins, color = colorResource(id = R.color.gray2)
                ) },
                leadingIcon = { Icon(
                    Icons.Default.Person, contentDescription = null,
                    tint = colorResource(id = R.color.gray2)
                ) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),

                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.gray),
                    unfocusedBorderColor = colorResource(id = R.color.gray),
                ),

                )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Password" ,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppins,
                textAlign = TextAlign.Start,
                color = colorResource(id = R.color.black_app),
                modifier = Modifier.fillMaxSize()
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Password TextField
            var passwordVisibility by remember { mutableStateOf(false) }
            OutlinedTextField(
                value = password,
                onValueChange = {password = it},
                placeholder = { Text(text ="enter Password" ,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppins,
                    color = colorResource(id = R.color.gray2)
                ) },
                leadingIcon = { Icon(
                    Icons.Default.Lock, contentDescription = null,
                    tint = colorResource(id = R.color.gray2)
                ) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.gray),
                    unfocusedBorderColor = colorResource(id = R.color.gray),
                ),
                shape = RoundedCornerShape(8.dp),
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Image(
                            painter = if (passwordVisibility) painterResource(id = R.drawable.eye_slash) else painterResource(id = R.drawable.eye_slash) ,
                            contentDescription = null
                        )
                    }
                },
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Forgot Password
            TextButton(onClick = {

            }) {
                Text(text = stringResource(id = R.string.forgot_password),
                    modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.End,
                    fontSize = 13.sp, color = colorResource(id = R.color.app_color),
                    fontWeight = FontWeight.Bold,fontFamily = poppins)
            }

            // Login Button
            Button(
                onClick = {
                    if (username.isEmpty() || password.isEmpty()) {
                        Toast.makeText(context, "Please enter username and password", Toast.LENGTH_SHORT).show()
                    }else {
                        login(username,password,context,navigator, viewModel = viewModel)

                    }


                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(8.dp),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.app_color)
                )
            ) {
                Text(text = stringResource(id = R.string.login_button),
                    modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.white),)
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Divider with "or"
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {

                Divider(modifier = Modifier.weight(1f))
                Text(text = "or",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = poppins,
                    modifier = Modifier.padding(horizontal = 8.dp))
                Divider(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(16.dp))


            TextButton(onClick = {
                navigator.push(RegisterScreen())

            }) {
                Text(text ="Don't Have Account ? ",
                    color = colorResource(id = R.color.gray2),
                    fontWeight = FontWeight.Medium,
                    fontFamily = poppins,
                    fontSize = 16.sp)
                Text(text = "Sign Up",
                    color = colorResource(id = R.color.app_color),
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppins,
                    fontSize = 16.sp)
            }
        }
    }

    fun login(email: String, password: String, context: Context, navigator: Navigator, viewModel : LoginViewModel){

        try {
            viewModel.login(email,password,{
                Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                navigator.replaceAll(MainContent())
            },{
                Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show() }
            )
        }catch (e:Exception){
            Log.e("mohamed", "login: ${e.message}")
        }


    }
}