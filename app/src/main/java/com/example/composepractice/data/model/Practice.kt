package com.example.composepractice.data.model

import androidx.compose.runtime.Composable
import com.example.composepractice.practice.*
import com.google.accompanist.pager.ExperimentalPagerApi

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

    object MusicKnobImpl: Practice() {
        @Composable
        override fun Compose() {
            MusicKnob()
        }
    }

    object Alignments2Impl: Practice() {
        @Composable
        override fun Compose() {
            Alignments2()
        }
    }

    object TimerImpl: Practice() {
        @Composable
        override fun Compose() {
            Timer()
        }
    }

    object BirthdayGreetImpl: Practice() {
        @Composable
        override fun Compose() {
            BirthdayGreet()
        }
    }

    object TabLayoutPracticeImpl: Practice() {
        @OptIn(ExperimentalPagerApi::class)
        @Composable
        override fun Compose() {
            TabLayoutPractice()
        }
    }

    @Composable
    abstract fun Compose()
}
