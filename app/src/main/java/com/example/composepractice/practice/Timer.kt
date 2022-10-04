package com.example.composepractice.practice

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

@Composable
fun Timer(intFlow: Flow<Int>) {
    var count by remember {
        mutableStateOf(0)
    }
    LaunchedEffect(key1 = true) {
        intFlow.collect {
            count = it
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Text(text = "$count")
    }
}