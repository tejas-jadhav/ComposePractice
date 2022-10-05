package com.example.composepractice.data.model

import androidx.compose.runtime.Composable
import com.example.composepractice.practice.*

sealed class Practice: java.io.Serializable {
    object RowsAndColumns_Impl: Practice() {
        @Composable
        override fun Compose() {
            RowsAndColumns()
        }
    }

    object Alignments_Impl: Practice() {
        @Composable
        override fun Compose() {
            Alignments()
        }
    }

    object Counter_Impl: Practice() {
        @Composable
        override fun Compose() {
            Counter()
        }
    }

    object Login_Impl: Practice() {
        @Composable
        override fun Compose() {
            Login()
        }
    }


    @Composable
    abstract fun Compose()
}
