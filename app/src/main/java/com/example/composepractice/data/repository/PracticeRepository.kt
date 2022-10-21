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
                Practice.RowsAndColumnsImpl,
                1,
            ),
            PracticeItem(
                "Alignments",
                "Aligning Items within Rows and Columns. I've also used a random color generator.",
                Practice.AlignmentsImpl,
                2,
            ),
            PracticeItem(
                "Counter",
                "Basic example of state. Increment, decrement and reset the counter",
                Practice.CounterImpl,
                3,
            ),
            PracticeItem(
                "Login",
                "Using EditTexts to take input. I've also performed input validation." +
                        "Following best practices, all the logic is present in the ViewModel.",
                Practice.LoginImpl,
                4,
            ),
            PracticeItem(
                "Custom Progress Bar",
                "Get acquainted with Compose 'Canvas' to draw custom pieces of UI. " +
                        "Here, I've created an custom progress bar with some animations.",
                Practice.CustomProgressBarImpl,
                5,
            ),
            PracticeItem(
                "Music Knob",
                "More of Canvas to create custom UI. The knob can be dragged and rotated, " +
                        "and the music bar will be reflect the changes in volume.",
                Practice.MusicKnobImpl,
                6,
            )

        )
    }
}