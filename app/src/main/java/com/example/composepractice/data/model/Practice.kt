package com.example.composepractice.data.model

import androidx.compose.runtime.Composable
import com.example.composepractice.practice.*

sealed class Practice: java.io.Serializable {
    object RowsAndColumnsImpl: Practice() {
        @Composable
        override fun Compose() {
            RowsAndColumns()
        }
    }

    object AlignmentsImpl: Practice() {
        @Composable
        override fun Compose() {
            Alignments()
        }
    }

    object CounterImpl: Practice() {
        @Composable
        override fun Compose() {
            Counter()
        }
    }

    object LoginImpl: Practice() {
        @Composable
        override fun Compose() {
            Login()
        }
    }

    object CustomProgressBarImpl: Practice() {
        @Composable
        override fun Compose() {
            CustomProgressBar()
        }
    }


    @Composable
    abstract fun Compose()
}
