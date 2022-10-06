package com.example.composepractice.data.repository

import com.example.composepractice.data.model.Practice
import com.example.composepractice.data.model.PracticeItem

class PracticeRepository {
    fun getPracticeExamples(): List<PracticeItem> {
        return listOf(
            PracticeItem(
                "Rows and Columns",
                "Practicing the use of Rows and Columns. I've tried to create a " +
                        "complex layout by nesting Rows, Columns and Boxes.",
                Practice.RowsAndColumns_Impl,
                1,
            ),
            PracticeItem(
                "Alignments",
                "Aligning Items within Rows and Columns. I've also used a random color generator.",
                Practice.Alignments_Impl,
                2,
            ),
            PracticeItem(
                "Counter",
                "Basic example of state. Increment, decrement and reset the counter",
                Practice.Counter_Impl,
                3,
            ),
            PracticeItem(
                "Login",
                "Opening a new Activity and Passing Login data to it. " +
                        "I've also added form validation. All the logic is present in the ViewModel",
                Practice.Login_Impl,
                4,
            ),

            )
    }
}