package com.example.cursojetpackcompose

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun Login() {
    LoginForm()

}

@Composable
fun LoginForm(modifier: Modifier = Modifier) {
    var user by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val enabled = user.isNotEmpty() && password.isNotEmpty()
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
    ) {
        TextField(value = user, onValueChange = { user = it }, label = { Text("User") })
        TextField(value = password, onValueChange = { password = it }, label = { Text("Password") })
        Button(
            onClick = {
                user = ""
                password = ""
            },
            enabled = enabled
        ) {
            Text("Login")
        }


    }
}

fun doLogin(user: String, pass: String, context: Context) {
    when {
        user.isNotBlank() && pass.isNotEmpty() -> toast("Login correcto", context)
        else -> toast("Login error", context)
    }
}

fun toast(text: String, context: Context) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}
