package com.example.composepractice.viewmodel

import androidx.annotation.StringRes
import com.example.composepractice.R

class MainActivityEvents {
    sealed class FilterEvent() {
        class ExpandedClick() : FilterEvent()
        class CollapsedClick() : FilterEvent()
    }
}