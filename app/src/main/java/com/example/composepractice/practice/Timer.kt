package com.example.composepractice.practice

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.example.composepractice.ui.theme.ComposeDarkestBlue
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun Timer() {
    var timerValue by remember { mutableStateOf(60) }
    var isRunning by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()

    ) {

        Spacer(modifier = Modifier.height(100.dp))
        ActualTimer(
            totalTime = timerValue * 1000L,
            modifier = Modifier.size(200.dp)
        ) { isRunning = it }

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = if (timerValue != 0) timerValue.toString() else "",
            onValueChange = {
                if (it.isDigitsOnly() && it.length < 6) {
                    timerValue = if (it.isNotBlank()) it.toInt() else 0
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier.fillMaxWidth(0.4f),
            singleLine = true,
            maxLines = 1,
            enabled = !isRunning
        )
    }
}

@Composable
fun ActualTimer(
    modifier: Modifier = Modifier,
    totalTime: Long,
    strokeWidth: Dp = 4.dp,
    inactiveColor: Color = Color.Gray,
    activeColor: Color = Color.Green.copy(alpha = 0.9f),
    handleColor: Color = Color.Green,
    initialPercent: Float = 1f,
    onActionButtonClick: (Boolean) -> Unit = {},
) {
    var size by remember { mutableStateOf(IntSize.Zero) }
    var currentTime by remember { mutableStateOf(totalTime) }
    var timerPercent by remember { mutableStateOf(initialPercent) }
    var isTimerRunning by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
        if (currentTime > 0f && isTimerRunning) {
            delay(100)
            currentTime -= 100L
            timerPercent = currentTime / totalTime.toFloat()
        }
    }

    LaunchedEffect(key1 = totalTime) {
        currentTime = totalTime
        timerPercent = 1f
        isTimerRunning = false
    }

    Box(contentAlignment = Alignment.Center, modifier = modifier.onSizeChanged { size = it }) {
        Canvas(modifier = modifier) {
            drawArc(
                color = inactiveColor,
                startAngle = -215f,
                sweepAngle = 250f,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )

            drawArc(
                color = activeColor,
                startAngle = -215f,
                sweepAngle = (250f * timerPercent),
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = (totalTime * timerPercent / 1000).roundToInt().toString(),
                fontSize = 40.sp,
            )
            Button(
                onClick = {
                    isTimerRunning = !isTimerRunning
                    onActionButtonClick(isTimerRunning)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = if (isTimerRunning) Color.Red else Color.Green)
            ) {
                Text(if (isTimerRunning) "Stop" else "Start")
            }
        }
    }
}

