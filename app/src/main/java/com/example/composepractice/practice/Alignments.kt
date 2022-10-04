package com.example.composepractice.practice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun Alignments() {
    Column() {
        Row(
            modifier = Modifier
                .background(Color.Red)
                .padding(16.dp, 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for (i in 1..4) {
                Item(text = "Item $i")
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Green)
        ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            for (i in 1..7) {
                Item(text = "Col item $i")
            }

        }

    }

}

@Composable
fun Item(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(randomColor())
            .padding(20.dp, 4.dp)
    )
    {
        Text(text = text)
    }
}

@Composable
fun randomColor(): Color {
    return Color(
        Math.random().toFloat(),
        Math.random().toFloat(),
        Math.random().toFloat(),
        1f
    )
}