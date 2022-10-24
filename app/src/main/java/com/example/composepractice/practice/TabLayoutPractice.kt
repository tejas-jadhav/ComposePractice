package com.example.composepractice.practice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@ExperimentalPagerApi
@Composable
fun TabLayoutPractice() {
    val pagerState = rememberPagerState(pageCount = 3)
    Column {
        TopAppBar {
            Text(
                text = "Tab Layout Example",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 12.dp),
                fontSize = 20.sp,
                color = Color.White
            )
        }
        Tabs(pagerState = pagerState)
        TabContent(pagerState = pagerState)

    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(pagerState: PagerState) {
    val list = listOf(
        "Home" to Icons.Default.Home,
        "Contacts" to Icons.Default.AccountCircle,
        "Settings" to Icons.Default.Settings
    )

    val scope = rememberCoroutineScope()
    TabRow(selectedTabIndex = pagerState.currentPage) {
        list.forEachIndexed { index, pair ->
            Tab(icon = {
                Icon(imageVector = pair.second, contentDescription = null)
            }, text = {
                Text(text = pair.first)
            }, selected = pagerState.currentPage == index, onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(index)
                }
            })
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent(pagerState: PagerState) {
    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> TabContentScreen(modifier = Modifier.background(Color.LightGray)) {
                Text(text = "Welcome To Home Page")
            }
            1 -> TabContentScreen(modifier = Modifier.background(Color.Gray)) {
                Text(text = "Welcome to contact Page")
            }
            2 -> TabContentScreen(modifier = Modifier.background(Color.DarkGray)) {
                Text(text = "Welcome to the settings Page", color = Color.White)
            }
        }
    }
}

@Composable
fun TabContentScreen(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        content()
    }
}

