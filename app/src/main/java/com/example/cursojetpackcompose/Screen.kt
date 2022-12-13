package com.example.cursojetpackcompose

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.cursojetpackcompose.ui.theme.CursoJetpackComposeTheme

@Composable
fun Screen() {
    CursoJetpackComposeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,

            ) {
            Login()
        }
    }
}
