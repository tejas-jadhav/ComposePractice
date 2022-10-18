package com.example.composepractice.ui.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composepractice.data.model.PracticeItem
import com.example.composepractice.ui.SecondActivity
import com.example.composepractice.ui.theme.ComposeDarkestBlue
import com.example.composepractice.viewmodel.MyViewModel


@Composable
fun MainScreen(myViewModel: MyViewModel = viewModel(), practiceListEvents: PracticeListEvents) {
    val practiceExamples by remember {
        myViewModel.getPracticeExamples()
    }

    Surface(modifier = Modifier.fillMaxSize(), color = ComposeDarkestBlue) {
        PracticeList(practiceExamples, practiceListEvents)
    }
}
