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
import com.example.composepractice.viewmodel.MyViewModel


@Composable
fun PracticeList(myViewModel: MyViewModel = viewModel(), onItemClick: (PracticeItem) -> Unit) {
    val practiceExamples by remember {
        myViewModel.getPracticeExamples()
    }

    val offset = 2


    LazyColumn(modifier = Modifier.padding(top = 20.dp)) {
        items(practiceExamples.size + offset) {
            if (it == 0) {
                Header()
                return@items
            }
            if (it == 1) {
                ActionRow(modifier = Modifier.padding(vertical = 20.dp))
                return@items
            }
            PracticeListItem(
                practiceExamples[it - offset],
                onClick = {
                    onItemClick(practiceExamples[it - offset])
                }
            )
        }
    }
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