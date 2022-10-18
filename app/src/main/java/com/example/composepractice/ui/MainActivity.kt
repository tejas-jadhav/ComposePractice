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
import com.example.composepractice.ui.components.PracticeListEvents
import com.example.composepractice.ui.components.SearchBar
import com.example.composepractice.ui.theme.ComposeDarkestBlue
import com.example.composepractice.viewmodel.MainActivityEvents
import com.example.composepractice.viewmodel.MyViewModel

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity(), PracticeListEvents {

    private var _viewModel: MyViewModel? = null
    private val viewModel: MyViewModel get() = _viewModel!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewModel = ViewModelProvider(this)[MyViewModel::class.java]

        setContent {
            MainScreen(viewModel, practiceListEvents = this)
        }
    }

    override fun onFilterButtonClick(isExpanded: Boolean) {
        if (isExpanded) {
            viewModel.onFilterEvent(MainActivityEvents.FilterEvent.ExpandedClick())
        } else {
            viewModel.onFilterEvent(MainActivityEvents.FilterEvent.CollapsedClick())
        }
    }

    override fun onPracticeItemClick(practiceItem: PracticeItem) {
        val outputIntent = Intent(this, SecondActivity::class.java)
        outputIntent.putExtra(SecondActivity.PRACTICE_ITEM, practiceItem.practice)
        startActivity(outputIntent)
    }

    override fun onSearchBarValueChange(text: String) {
//        TODO("Not yet implemented")
    }

}

