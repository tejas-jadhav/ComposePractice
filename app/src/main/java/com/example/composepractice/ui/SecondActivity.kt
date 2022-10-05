package com.example.composepractice.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.composepractice.data.model.Practice

class SecondActivity : ComponentActivity() {
    companion object {
        const val PRACTICE_ITEM = "practice-item"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val practice = intent.getSerializableExtra(PRACTICE_ITEM) as Practice

        setContent {
            practice.Compose()
        }

    }
}