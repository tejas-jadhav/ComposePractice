package com.example.composepractice.ui

import android.content.Intent
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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composepractice.data.model.PracticeItem
import com.example.composepractice.practice.*
import com.example.composepractice.ui.components.MainScreen
import com.example.composepractice.ui.theme.ComposePracticeTheme
import com.example.composepractice.viewmodel.MyViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {

    private var _viewModel: MyViewModel? = null
    private val viewModel: MyViewModel get() = _viewModel!!

    private val handlePracticeItemClick: (PracticeItem) -> Unit = { practiceItem ->
        val outputIntent = Intent(this, SecondActivity::class.java)
        outputIntent.putExtra(SecondActivity.PRACTICE_ITEM, practiceItem.practice)
        startActivity(outputIntent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewModel = ViewModelProvider(this)[MyViewModel::class.java]

        setContent {
            MainScreen(viewModel, onPracticeItemClick = handlePracticeItemClick)
        }
    }


}

