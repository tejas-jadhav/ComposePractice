package com.example.composepractice.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composepractice.R
import com.example.composepractice.ui.theme.ComposeBlue
import com.example.composepractice.ui.theme.ComposeDarkBlue
import com.example.composepractice.ui.theme.ComposeDarkestBlue
import com.example.composepractice.ui.theme.ComposeGreen

@Composable
fun FilterButton(modifier: Modifier = Modifier, showText: Boolean = true) {

    Box(modifier = modifier) {
        Button(
            contentPadding = if (showText) ButtonDefaults.ContentPadding else PaddingValues(0.dp),
            modifier = Modifier.defaultMinSize(36.dp),
            onClick = { /*TODO*/ },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(backgroundColor = ComposeGreen)
        ) {
            AnimatedVisibility(showText) {
                Text(
                    text = "Latest",
                    letterSpacing = 0.sp,
                    color = ComposeDarkestBlue,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.width(4.dp))
            }
            Icon(
                painterResource(id = R.drawable.ic_baseline_filter_alt_24),
                "Filter", tint = ComposeDarkestBlue
            )
        }

    }
}

@Preview(showBackground = true, backgroundColor = 0xFF041618)
@Composable
fun FilterButtonPreview() {
    FilterButton()
}