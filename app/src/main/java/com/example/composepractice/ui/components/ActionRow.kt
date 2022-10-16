package com.example.composepractice.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ActionRow(
    modifier: Modifier = Modifier,
    onSearchBarValueChange: (String) -> Unit = {},
    onFilterButtonClick: () -> Unit = {}
) {
    var searchBarWidthPercent by remember {
        mutableStateOf(0.7f)
    }
    var showFilterButtonText by remember {
        mutableStateOf(true)
    }
    val searchBarWidth by animateFloatAsState(targetValue = searchBarWidthPercent)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        SearchBar(
            modifier = Modifier.fillMaxWidth(searchBarWidth),
            onFocusChanged = { focusState ->
                searchBarWidthPercent = if (focusState.isFocused) 0.82f else 0.7f
                showFilterButtonText = !focusState.isFocused
            },
            onValueChange = {})
        FilterButton(showText = showFilterButtonText)
    }
}