package com.example.composepractice.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.composepractice.data.repository.PracticeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MyViewModel : ViewModel() {
    private val repository = PracticeRepository()
    private var _practiceExamples = mutableStateOf(
        repository.getPracticeExamples()
    )

    fun getPracticeExamples() = _practiceExamples

}