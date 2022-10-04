package com.example.composepractice.practice

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.composepractice.R
import com.example.composepractice.ui.theme.Shapes

@Composable
fun Login() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Logo()

    }
}

@Composable
fun Logo() {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(Color.DarkGray)
            .padding(8.dp)
            .border(8.dp, Color.Gray, CircleShape)
            .padding(30.dp)

    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_switch_account_24),
            contentDescription = "Login",
            modifier = Modifier.size(80.dp)
        )



    }
}

