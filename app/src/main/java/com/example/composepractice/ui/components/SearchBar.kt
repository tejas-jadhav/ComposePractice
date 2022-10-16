package com.example.composepractice.ui.components

import android.graphics.drawable.Icon
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composepractice.ui.theme.ComposeBlue
import com.example.composepractice.ui.theme.ComposeDarkBlue
import com.example.composepractice.ui.theme.ComposeDarkestBlue
import com.example.composepractice.ui.theme.ComposeGreen

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "Search...",
    onFocusChanged: (FocusState) -> Unit = {},
    onValueChange: (String) -> Unit
) {
    var text by remember {
        mutableStateOf("")
    }

    var isFocused by remember {
        mutableStateOf(false)
    }
    val focusManager = LocalFocusManager.current


    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp, 4.dp), contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(CircleShape)
                .background(ComposeDarkBlue.copy(0.9f)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Spacer(modifier = Modifier.width(12.dp))
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = ComposeGreen
            )
            BasicTextField(
                value = text, onValueChange = {
                    text = it
                    onValueChange(it)
                },
                textStyle = TextStyle(color = ComposeBlue, fontSize = 18.sp),
                singleLine = true,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 0.dp)
                    .onFocusChanged {
                        isFocused = it.isFocused
                        onFocusChanged(it)
                    },
                cursorBrush = SolidColor(ComposeGreen)
            )
            AnimatedVisibility(
                isFocused,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                IconButton(onClick = {
                    text = ""
                    focusManager.clearFocus()
                }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear",
                        tint = ComposeBlue
                    )
                }
            }
        }
        AnimatedVisibility(
            !isFocused, enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            TextHint(hint)
        }
    }
}

@Composable
private fun TextHint(hint: String) {
    Box() {
        Row {
            Spacer(modifier = Modifier.width(50.dp))
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier.alignByBaseline(),
                fontSize = 18.sp
            )
        }
    }
}


@Preview(
    showBackground = true, backgroundColor = 0xFF041618
)
@Composable
fun Preview() {
    SearchBar() {

    }
}