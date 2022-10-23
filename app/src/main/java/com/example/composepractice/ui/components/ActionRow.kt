package com.example.composepractice.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ActionRow(
    modifier: Modifier = Modifier,
    onSearchBarValueChange: (String) -> Unit = {},
    onFilterButtonClick: (Boolean) -> Unit = {},
    onSearchBarClear: () -> Unit = {}
) {
    var showFilterButtonText by remember {
        mutableStateOf(true)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            onFocusChanged = { showFilterButtonText = !it.isFocused },
            onValueChange = onSearchBarValueChange,
            onSearchBarClear = onSearchBarClear
        )

        Spacer(modifier = Modifier.width(if (showFilterButtonText) 6.dp else 2.dp))

        FilterButton(
            showText = showFilterButtonText,
            onClick = {
                onFilterButtonClick(showFilterButtonText)
            })

    }
}