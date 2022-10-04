package com.example.composepractice

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.composepractice.practice.Alignments
import com.example.composepractice.practice.Counter
import com.example.composepractice.practice.Login
import com.example.composepractice.practice.RowsAndColumns
import com.example.composepractice.ui.theme.ComposePracticeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        viewmodel
        val intFlow = flow<Int> {
            var i = 60
            while (i >= 0) {
                emit(i)
                delay(1000)
                i--
            }
        }


//        activity


        setContent {
//            RowsAndColumns()
//            Alignments()
//            Counter()
//            Login()

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
    }
}

