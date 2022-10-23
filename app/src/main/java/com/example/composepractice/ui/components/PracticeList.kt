package com.example.composepractice.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composepractice.data.model.PracticeItem
import com.example.composepractice.util.Constants
import com.example.composepractice.viewmodel.MainActivityEvents
import com.example.composepractice.viewmodel.MyViewModel


@Composable
fun PracticeList(practiceExamples: List<PracticeItem>, practiceListEvents: PracticeListEvents) {

    LazyColumn(modifier = Modifier.padding(top = 20.dp)) {
        items(practiceExamples.size + 2) { index ->
            if (index == 0) {
                Header()
                return@items
            }
            if (index == 1) {
                ActionRow(
                    modifier = Modifier.padding(vertical = 20.dp),
                    onFilterButtonClick = { practiceListEvents.onFilterButtonClick(it) },
                    onSearchBarValueChange = { practiceListEvents.onSearchBarValueChange(it) },
                    onSearchBarClear = { practiceListEvents.onSearchBarClearText() }
                )
                return@items
            }
            PracticeListItem(
                practiceExamples[index - 2],
                onClick = {
                    practiceListEvents.onPracticeItemClick(practiceExamples[index - 2])
                }
            )
        }
    }
}

interface PracticeListEvents {
    fun onFilterButtonClick(isExpanded: Boolean)
    fun onPracticeItemClick(practiceItem: PracticeItem)
    fun onSearchBarValueChange(text: String)
    fun onSearchBarClearText()
}

@Composable
fun PracticeListItem(
    practiceItem: PracticeItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    color = Color.LightGray
                ),
                onClick = onClick
            )
            .padding(18.dp, 14.dp)

    ) {

        Text(
            text = practiceItem.name,
            fontSize = 19.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            modifier = Modifier.padding(bottom = 6.dp)
        )

        Text(
            text = practiceItem.description,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.LightGray,
            maxLines = 3,
        )

    }
}