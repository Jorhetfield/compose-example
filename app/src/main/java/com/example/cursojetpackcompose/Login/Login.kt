package com.example.cursojetpackcompose.Login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun Login(viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    Login(state = viewModel.state, onLogin = viewModel::doLogin)
}

@Composable
fun Login(state: LoginViewModel.UiState, onLogin: (user: String, pass: String) -> Unit) {

    LoginForm(
        state = state, onLogin = onLogin, modifier = Modifier
            .wrapContentSize()
            .background(Color.Gray.copy(alpha = 0.2f))
            .padding(16.dp)
    )
}

@Composable
fun LoginForm(
    state: LoginViewModel.UiState, onLogin: (user: String, pass: String) -> Unit, modifier: Modifier = Modifier
) {
    var user by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val enabled = user.isNotEmpty() && password.isNotEmpty()
    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
    ) {
        UserTextField(
            user = user,
            onUserChange = { user = it },
            isError = state.error != null,
            imeAction = ImeAction.Next
        )
        PasswordTextField(
            password = password,
            onPassChange = { password = it },
            isError = state.error != null,
            imeAction = ImeAction.Done,
            keyboardActions = KeyboardActions { if (enabled) onLogin(user, password) }
        )


        Button(
            onClick = {
                onLogin(user, password)
            }, enabled = enabled
        ) {
            Text("Login")
        }
        if (state.error != null || state.loggedIn) {
            Text(text = state.error?.let { "Error: $it" } ?: "Logged in succesfully", color = state.error?.let { MaterialTheme.colors.error } ?: Color.Unspecified)
        }

    }
}

@Composable
fun UserTextField(
    user: String,
    onUserChange: (String) -> Unit, isError: Boolean,
    imeAction: ImeAction = ImeAction.Default,
    keyboardActions: KeyboardActions = KeyboardActions()
) {
    TextField(
        value = user,
        onValueChange = onUserChange,
        label = { Text("User") },
        isError = isError,
        singleLine = true,
        placeholder = { Text("Your best email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = imeAction),
        keyboardActions = keyboardActions
    )
}

@Composable
fun PasswordTextField(
    password: String,
    onPassChange: (String) -> Unit,
    isError: Boolean,
    imeAction: ImeAction = ImeAction.Default,
    keyboardActions: KeyboardActions = KeyboardActions()
) {
    var passVisible by rememberSaveable { mutableStateOf(false) }
    TextField(
        value = password,
        onValueChange = onPassChange,
        label = { Text("Password") },
        placeholder = { Text("More than 5 characters") },
        isError = isError,
        singleLine = true,
        visualTransformation = if (passVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = imeAction),
        keyboardActions = keyboardActions,
        trailingIcon = {
            IconToggleButton(
                checked = passVisible,
                onCheckedChange = { passVisible = it }
            ) {
                if (passVisible) {
                    Icon(
                        imageVector = Icons.Default.VisibilityOff,
                        contentDescription = "Hide Password"
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Visibility,
                        contentDescription = "Show Password"
                    )
                }
            }
        }
    )
}
