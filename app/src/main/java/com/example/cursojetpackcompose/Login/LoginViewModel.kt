package com.example.cursojetpackcompose.Login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    data class UiState(
        val loggedIn: Boolean = false,
        val error: String? = null
    )


    fun doLogin(user: String, pass: String) {
        val userValid = user.contains("@")
        val passValid = pass.length > 5

        val error =
            when {
                !userValid -> "User must contain @"
                !passValid -> "Password must be longer than 5 characters"
                else -> null
            }
        state = UiState(loggedIn = userValid && passValid, error = error)

    }


}