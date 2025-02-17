package com.khedr.ecobot.ui.presentaion.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel :ViewModel(){

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val _currentUser: MutableState<FirebaseUser?> = mutableStateOf(firebaseAuth.currentUser)
    val currentUser: MutableState<FirebaseUser?> = _currentUser
    private val _isLoadingLogin = MutableStateFlow(false)
    val isLoadingLogin: StateFlow<Boolean> get() = _isLoadingLogin

    fun login(email: String, password: String, onLoginSuccess: () -> Unit, onLoginError: (String) -> Unit) {
        _isLoadingLogin.value = true
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _currentUser.value = firebaseAuth.currentUser
                        onLoginSuccess()
                    } else {
                        Log.e("FirebaseAuth", "login:failure", task.exception)
                        onLoginError(task.exception?.message ?: "Login failed: Unknown error.")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e("FirebaseAuth", "login:failure", exception)
                    onLoginError(exception.message ?: "Login failed: Unknown error.")
                }
        } catch (e: Exception) {
            Log.e("FirebaseAuth", "Unexpected error during login", e)
            onLoginError("Unexpected error during login: ${e.localizedMessage}")
        }finally {
            _isLoadingLogin.value = false
        }
    }

}