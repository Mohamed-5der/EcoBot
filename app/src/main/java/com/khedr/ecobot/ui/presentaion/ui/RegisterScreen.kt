package com.khedr.ecobot.ui.presentaion.ui

import android.content.Context
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
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalAutofillTree
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
import com.khedr.ecobot.ui.presentaion.viewModel.SignUpViewModel

class RegisterScreen :Screen {
    @Composable
    override fun Content() {
        RegisterContent()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun RegisterContent() {
        var  username by remember { mutableStateOf("") }
        var  email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }
        val context = LocalContext.current
        val navigator = LocalNavigator.currentOrThrow
        val  viewModel =SignUpViewModel()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Sign Up",
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppins,
                    color = colorResource(id = R.color.app_color),
                    modifier = Modifier.align(Alignment.Center)
                )
                IconButton(onClick = {

                }, modifier = Modifier.align(Alignment.TopStart)) {
                    Icon(
                        Icons.Default.ArrowBack, contentDescription = null,
                        tint = colorResource(id = R.color.app_color)
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "Login Illustration",
                modifier = Modifier
                    .size(200.dp)
                    .background(Color.White, shape = CircleShape),
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
                text ="Email",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppins,
                textAlign = TextAlign.Start,
                color = colorResource(id = R.color.black_app),
                modifier = Modifier.fillMaxSize()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = email,
                onValueChange = {email = it},
                placeholder = { Text(text = "Enter Email",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppins, color = colorResource(id = R.color.gray2)
                ) },
                leadingIcon = { Icon(
                    Icons.Default.Email, contentDescription = null,
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

            Text(
                text = "Confirm Password" ,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppins,
                textAlign = TextAlign.Start,
                color = colorResource(id = R.color.black_app),
                modifier = Modifier.fillMaxSize()
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Password TextField
            var ConfirmpasswordVisibility by remember { mutableStateOf(false) }
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {confirmPassword = it},
                placeholder = { Text(text ="Enter Confirm Password" ,
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
                    IconButton(onClick = { ConfirmpasswordVisibility = !ConfirmpasswordVisibility }) {
                        Image(
                            painter = if (ConfirmpasswordVisibility) painterResource(id = R.drawable.eye_slash) else painterResource(id = R.drawable.eye_slash) ,
                            contentDescription = null
                        )
                    }
                },
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Login Button
            Button(
                onClick = {
                    if (email.isEmpty() || password.isEmpty() || username.isEmpty()|| confirmPassword.isEmpty()) {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    }else if (password != confirmPassword){
                        Toast.makeText(context, "Password not match", Toast.LENGTH_SHORT).show()
                    }else {
                        register(email,password,navigator,context,username,"", viewModel)

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
                Text(text = "Sign Up",
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
                navigator.push(LoginScreen())
            }) {
                Text(text ="Already Have Account ? ",
                    color = colorResource(id = R.color.gray2),
                    fontWeight = FontWeight.Medium,
                    fontFamily = poppins,
                    fontSize = 16.sp)
                Text(text = "Login ",
                    color = colorResource(id = R.color.app_color),
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppins,
                    fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
    fun register(email: String, password: String, navigator: Navigator, context: Context, name :String, phone : String, viewModel : SignUpViewModel){
        viewModel.register(email,password,{
            navigator.replaceAll(MainContent())
            Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show()
        },{
            Toast.makeText(context, "Registration failed", Toast.LENGTH_SHORT).show()

        })
    }

}