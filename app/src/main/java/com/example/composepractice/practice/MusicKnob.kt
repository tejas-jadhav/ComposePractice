package com.example.composepractice.practice

import android.util.Log
import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composepractice.R
import com.example.composepractice.ui.theme.ComposeDarkestBlue
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.roundToInt

data class Point(
    val x: Float,
    val y: Float,
)

@Composable
fun MusicKnob() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ComposeDarkestBlue),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(1.dp, Color.Green, RoundedCornerShape(10.dp))
                .padding(30.dp, 20.dp)
        ) {
            var volume by remember {
                mutableStateOf(0f)
            }
            val barCount = 20
            TheMusicKnob(modifier = Modifier.size(100.dp)) {
                volume = it
            }
            Spacer(modifier = Modifier.width(20.dp))
            VolumeBars(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                activeBars = (volume * barCount).roundToInt(),
                barCount = barCount
            )
        }
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TheMusicKnob(
    modifier: Modifier = Modifier,
    limitingAngle: Float = 25f,
    onValueChange: (Float) -> Unit

) {
    var rotation by remember {
        mutableStateOf(limitingAngle)
    }
    var touch by remember {
        mutableStateOf(Point(0f, 0f))
    }
    var center by remember {
        mutableStateOf(Point(0f, 0f))
    }
    Image(painter = painterResource(id = R.drawable.music_knob),
        contentDescription = "Knob",
        modifier = modifier
            .onGloballyPositioned {
                val windowBounds = it.boundsInWindow()
                center = center.copy(
                    x = windowBounds.size.width / 2f,
                    y = windowBounds.size.height / 2f
                )

            }
            .pointerInteropFilter { event ->
                touch = touch.copy(x = event.x, y = event.y)
                val angle = -atan2(center.x - touch.x, center.y - touch.y) * (180f / PI).toFloat()
                when (event.action) {
                    MotionEvent.ACTION_DOWN,
                    MotionEvent.ACTION_MOVE -> {
                        if (angle !in -limitingAngle..limitingAngle) {
//                            after half rotation 180deg, angle becomes -180deg and starts decreasing
                            val fixedAngle =
                                if (angle in -180f..-limitingAngle) 360f + angle else angle

                            rotation = fixedAngle
                            val percent = (fixedAngle - limitingAngle) / (360f - 2 * limitingAngle)
                            onValueChange(percent)
                            true
                        } else false
                    }
                    else -> false
                }
            }
            .clip(CircleShape)
            .rotate(rotation)
    )

}

@Composable
fun VolumeBars(
    modifier: Modifier = Modifier,
    activeBars: Int = 0,
    barCount: Int = 10
) {
    BoxWithConstraints(modifier = modifier, contentAlignment = Alignment.Center) {
        val barWidth = remember {
            constraints.maxWidth / (2f * barCount)
        }
        Canvas(modifier = modifier) {
            for (i in 0 until barCount) {
                drawRoundRect(
                    color = if (i in 0..activeBars) Color.Green else Color.Gray,
                    topLeft = Offset(i * barWidth * 2f + barWidth / 2f, 0f),
                    size = Size(barWidth, constraints.maxHeight.toFloat()),
                    cornerRadius = CornerRadius(0f)
                )
            }
        }
    }

}

@Preview
@Composable
fun MusicKnobPreview() {
    MusicKnob()
}