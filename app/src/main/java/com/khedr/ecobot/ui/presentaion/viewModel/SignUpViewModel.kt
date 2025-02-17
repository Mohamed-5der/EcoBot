package com.khedr.ecobot.ui.presentaion.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignUpViewModel :ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val _currentUser: MutableState<FirebaseUser?> = mutableStateOf(firebaseAuth.currentUser)
    val currentUser: MutableState<FirebaseUser?> = _currentUser
    private val _isLoadingSignUp = MutableStateFlow(false)
    val isLoadingSignUp: StateFlow<Boolean> get() = _isLoadingSignUp



    fun register(email: String, password: String, onRegisterSuccess: () -> Unit, onRegisterError: (String) -> Unit) {
        _isLoadingSignUp.value = true
        try {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        _currentUser.value = firebaseAuth.currentUser
                        onRegisterSuccess()
                    } else {
                        Log.e("FirebaseAuth", "register:failure", task.exception)
                        onRegisterError(task.exception?.message ?: "Registration failed: Unknown error.")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e("FirebaseAuth", "register:failure", exception)
                    onRegisterError(exception.message ?: "Registration failed: Unknown error.")
                }
        } catch (e: Exception) {
            Log.e("FirebaseAuth", "Unexpected error during registration", e)
            onRegisterError("Unexpected error during registration: ${e.localizedMessage}")
        }finally {
            _isLoadingSignUp.value=false
        }
    }
}