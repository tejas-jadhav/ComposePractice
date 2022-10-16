package com.example.composepractice.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.composepractice.data.model.PracticeItem
import com.example.composepractice.practice.Login
import com.example.composepractice.ui.components.MainScreen
import com.example.composepractice.ui.components.SearchBar
import com.example.composepractice.ui.theme.ComposeDarkestBlue
import com.example.composepractice.viewmodel.MyViewModel

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

