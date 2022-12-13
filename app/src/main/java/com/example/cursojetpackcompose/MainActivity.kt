package com.example.cursojetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cursojetpackcompose.ui.theme.CursoJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Screen()
        }
    }
}

@Preview(showBackground = true, widthDp = 150, heightDp = 100)
@Composable
fun TextButton() {
    CursoJetpackComposeTheme() {
        Box(contentAlignment = Alignment.Center) {

            Text(
                text = "Hello Button!",
                modifier = Modifier
                    .clickable { }
                    .border(2.dp, Color.Blue)
                    .background(Color.Cyan)
                    .padding(8.dp, 4.dp)
                    .border(2.dp, Color.Red)
                    .background(Color.LightGray)
                    .padding(4.dp, 8.dp)
            )

        }
    }
}