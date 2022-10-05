package com.example.composepractice.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composepractice.R
import com.example.composepractice.ui.theme.ComposeBlue
import com.example.composepractice.ui.theme.ComposeDarkBlue
import com.example.composepractice.ui.theme.ComposeDarkestBlue
import com.example.composepractice.ui.theme.ComposeGreen

@Composable
fun Header(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(ComposeDarkBlue.copy(0.9f))
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.compose_icon),
                contentDescription = "Compose Logo",
                modifier = Modifier.size(70.dp)
            )

            Text(
                getAnnotatedString(),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

        }

    }

}

@Composable
fun getAnnotatedString(): AnnotatedString {
    return buildAnnotatedString {
        withStyle(
            SpanStyle(
                ComposeBlue
            )
        ) {
            append("Jetpack ")
        }
        withStyle(
            SpanStyle(ComposeGreen)
        ) {
            append("Compose")
        }
    }
}