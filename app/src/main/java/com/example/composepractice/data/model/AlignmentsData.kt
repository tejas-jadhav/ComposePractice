package com.example.composepractice.data.model

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment


data class AlignmentData(
    val text: String = "",
    val alignment: Int = 0
) {
    fun getAlignmentColumn() = when (alignment) {
        0 -> Alignment.Start
        1 -> Alignment.End
        2 -> Alignment.CenterHorizontally
        else -> Alignment.CenterHorizontally
    }

    fun getAlignmentRow() = when (alignment) {
        0 -> Alignment.Top
        1 -> Alignment.Bottom
        2 -> Alignment.CenterVertically
        else -> Alignment.CenterVertically
    }

    override fun toString(): String {
        return text
    }

    companion object {
        val START = AlignmentData("Start", 0)
        val END = AlignmentData("End", 1)

        val CENTER = AlignmentData("Center", 2)

        val TOP = AlignmentData("Top", 0)
        val BOTTOM = AlignmentData("Bottom", 1)

        fun getAllAlignmentColumn() = listOf(
            START, END, CENTER
        )

        fun getAllAlignmentRow() = listOf(
            TOP, BOTTOM, CENTER
        )
        fun getEquivalentValue(alignmentData: AlignmentData) = when (alignmentData) {
            TOP -> START
            START -> TOP
            BOTTOM -> END
            END -> BOTTOM
            else -> alignmentData
        }
    }

}

data class ArrangementData(
    val text: String = "",
    val arrangement: Int = 0
) {
    override fun toString(): String {
        return text
    }

    fun getArrangementRow(): Arrangement.Horizontal {
        return when (arrangement) {
            0 -> Arrangement.SpaceBetween
            1 -> Arrangement.SpaceEvenly
            2 -> Arrangement.SpaceAround
            3 -> Arrangement.Start
            4 -> Arrangement.End
            5 -> Arrangement.Center
            else -> Arrangement.Center
        }
    }

    fun getArrangementColumn() = when (arrangement) {
        0 -> Arrangement.SpaceBetween
        1 -> Arrangement.SpaceEvenly
        2 -> Arrangement.SpaceAround
        3 -> Arrangement.Top
        4 -> Arrangement.Bottom
        5 -> Arrangement.Center
        else -> Arrangement.Center
    }

    companion object {
        val SPACE_BETWEEN = ArrangementData("Space Between", 0)
        val SPACE_EVENLY = ArrangementData("Space Evenly", 1)
        val SPACE_AROUND = ArrangementData("Space Around", 2)
        val CENTER = ArrangementData("Center", 5)

        val START = ArrangementData("Start", 3)
        val END = ArrangementData("End", 4)

        val TOP = ArrangementData("Top", 3)
        val BOTTOM = ArrangementData("Bottom", 4)

        fun getAllArrangementsRow() = listOf(
            SPACE_BETWEEN,
            SPACE_EVENLY,
            SPACE_AROUND,
            CENTER,
            START,
            END
        )

        fun getAllArrangementColumn() = listOf(
            SPACE_BETWEEN,
            SPACE_EVENLY,
            SPACE_AROUND,
            CENTER,
            TOP,
            BOTTOM
        )

        fun getEquivalentValue(arrangementData: ArrangementData) = when (arrangementData) {
            START -> TOP
            END -> BOTTOM
            TOP -> START
            BOTTOM -> END
            else -> arrangementData
        }

    }
}