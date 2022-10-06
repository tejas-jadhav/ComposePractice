package com.example.composepractice.practice

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composepractice.R
import com.example.composepractice.util.Resource
import com.example.composepractice.viewmodel.LoginFormEvent
import com.example.composepractice.viewmodel.MyViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@Composable
fun Login(myViewModel: MyViewModel = viewModel()) {
    val userDetails = myViewModel.loginState

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        myViewModel.loginEvent.collect { loginResult ->
            when (loginResult) {
                is Resource.Success -> {
                    Toast.makeText(context, "Login succeeded", Toast.LENGTH_SHORT).show()
                }
                is Resource.Error -> {}
                is Resource.Loading -> {}
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Logo()
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Login", fontWeight = FontWeight.Bold, fontSize = 30.sp)
        Spacer(modifier = Modifier.height(20.dp))

        EmailTextField(
            userDetails.email,
            hasError = userDetails.emailError != null,
            errorMessage = userDetails.emailError,
            onValueChange = {
            myViewModel.onLoginFormEvent(LoginFormEvent.EmailChanged(it))
        })
        Spacer(modifier = Modifier.height(20.dp))

        PasswordTextField(
            userDetails.password,
            hasError = userDetails.passwordError != null,
            errorMessage = userDetails.passwordError,
            onValueChange = {
                myViewModel.onLoginFormEvent(LoginFormEvent.PasswordChanged(it))
            },
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            myViewModel.onLoginFormEvent(LoginFormEvent.Submit)
        }, modifier = Modifier.fillMaxWidth(0.8f)) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(20.dp))

        TextButton(onClick = {
            Toast.makeText(
                context, "Then remember it !", Toast.LENGTH_SHORT
            ).show()
        }) {
            Text("Forgot password ?")
        }

    }
}

@Composable
private fun PasswordTextField(
    password: String,
    onValueChange: (String) -> Unit,
    hasError: Boolean = false,
    errorMessage: String? = null
) {
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    TextField(
        value = password,
        onValueChange = onValueChange,
        label = { Text("Password") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(0.8f),
        leadingIcon = {
            Icon(painterResource(id = R.drawable.ic_baseline_key_24), null)
        },
        trailingIcon = {
            val iconId = if (isPasswordVisible) R.drawable.ic_baseline_visibility_off_24
            else R.drawable.ic_baseline_visibility_24

            val description = if (isPasswordVisible) "Hide Password" else "Show Password"

            if (password.isNotBlank()) {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(painterResource(id = iconId), description)
                }
            }
        },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None
        else PasswordVisualTransformation()
    )

    if (hasError) {
        Text(
            text = errorMessage ?: "Error",
            color = Color.Red,
            modifier = Modifier.fillMaxWidth(0.8f),
            textAlign = TextAlign.Start
        )
    }
}

@Composable
private fun EmailTextField(
    username: String,
    onValueChange: (String) -> Unit,
    hasError: Boolean = false,
    errorMessage: String? = null
) {
    TextField(value = username,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(0.8f),
        label = { Text("Email") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        singleLine = true,
        leadingIcon = {
            Icon(
                painterResource(id = R.drawable.ic_baseline_alternate_email_24), null
            )
        })

    if (hasError) {
        Text(
            text = errorMessage ?: "Error",
            color = Color.Red,
            modifier = Modifier.fillMaxWidth(0.8f),
            textAlign = TextAlign.Start
        )
    }
}


@Composable
fun Logo() {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(Color.DarkGray)
            .padding(4.dp)
            .border(8.dp, Color.Gray, CircleShape)
            .padding(20.dp)

    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_switch_account_24),
            contentDescription = "Login",
            modifier = Modifier.size(60.dp)
        )
    }
}



