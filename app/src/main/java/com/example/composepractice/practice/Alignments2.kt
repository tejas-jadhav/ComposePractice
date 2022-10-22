package com.example.composepractice.practice

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composepractice.R
import com.example.composepractice.data.model.AlignmentData
import com.example.composepractice.data.model.ArrangementData
import com.example.composepractice.ui.theme.ComposeBlue
import com.example.composepractice.ui.theme.ComposeDarkBlue
import com.example.composepractice.ui.theme.ComposeDarkestBlue
import com.example.composepractice.ui.theme.ComposeGreen
import kotlin.math.roundToInt


@Composable
fun Alignments2() {
    var noOfItems by remember {
        mutableStateOf(2)
    }
    var isRow by remember {
        mutableStateOf(true)
    }
    var alignment by remember {
        mutableStateOf(AlignmentData.CENTER)
    }
    var arrangement by remember {
        mutableStateOf(ArrangementData.CENTER)
    }

    LaunchedEffect(key1 = isRow, block = {
        alignment = AlignmentData.getEquivalentValue(alignment)
        arrangement = ArrangementData.getEquivalentValue(arrangement)
    })


    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.background(ComposeDarkestBlue)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.57f),
            contentAlignment = Alignment.Center
        ) {
            if (isRow) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(ComposeDarkBlue.copy(0.9f))
                        .padding(vertical = 20.dp),
                    horizontalArrangement = arrangement.getArrangementRow(),
                    verticalAlignment = alignment.getAlignmentRow()
                ) {
                    for (i in 1..noOfItems) {
                        Image(
                            painter = painterResource(id = R.drawable.compose_icon),
                            contentDescription = null,
                            modifier = Modifier.size(44.dp)
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(260.dp)
                        .padding(top = 20.dp)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(ComposeDarkBlue.copy(0.9f))
                        .padding(horizontal = 20.dp),
                    verticalArrangement = arrangement.getArrangementColumn(),
                    horizontalAlignment = alignment.getAlignmentColumn()
                ) {
                    for (i in 1..noOfItems) {
                        Image(
                            painter = painterResource(id = R.drawable.compose_icon),
                            contentDescription = null,
                            modifier = Modifier.size(44.dp)
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize(0.8f)
                    .clip(RoundedCornerShape(24.dp))
                    .shadow(12.dp, RoundedCornerShape(24.dp), clip = true)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(10.dp))

                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Customize",
                            fontSize = 20.sp,
                            color = ComposeDarkestBlue
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 40.dp)
                    ) {
                        RadioStuff(
                            selected = isRow,
                            label = "Row",
                            modifier = Modifier.weight(0.5f),
                            onClick = {
                                isRow = true
                            })
                        RadioStuff(
                            selected = !isRow,
                            label = "Column",
                            modifier = Modifier.weight(0.5f),
                            onClick = {
                                isRow = false
                            })
                    }

                    Spacer(modifier = Modifier.height(14.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column {
                            Text(
                                "Alignment:",
                                color = Color.Gray,
                                modifier = Modifier.padding(start = 4.dp), fontSize = 12.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            TrySpinner(
                                items = if (isRow) AlignmentData.getAllAlignmentRow()
                                else AlignmentData.getAllAlignmentColumn(),
                                selected = alignment.text
                            ) {
                                alignment = it
                            }
                        }
                        Column {
                            Text(
                                "Arrangement:",
                                color = Color.Gray,
                                modifier = Modifier.padding(start = 12.dp),
                                fontSize = 12.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            TrySpinner(
                                items = if (isRow) ArrangementData.getAllArrangementsRow()
                                else ArrangementData.getAllArrangementColumn(),
                                selected = arrangement.text
                            ) {
                                arrangement = it
                            }
                        }
                    }



                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "Item Count: $noOfItems", modifier = Modifier
                            .padding(20.dp, 0.dp)
                            .align(Alignment.Start)
                    )
                    Slider(
                        value = noOfItems.toFloat(), onValueChange = {
                            noOfItems = it.roundToInt()
                        }, valueRange = 1f..7f, steps = 5, modifier = Modifier.fillMaxWidth(0.96f),
                        colors = SliderDefaults.colors(
                            thumbColor = ComposeGreen,
                            activeTrackColor = ComposeGreen,
                            inactiveTrackColor = ComposeBlue.copy(alpha = 0.4f),
                            activeTickColor = ComposeGreen,
                            inactiveTickColor = ComposeGreen.copy(alpha = 0.6f)
                        )
                    )

                }

            }
        }
    }
}

@Composable
fun <T> TrySpinner(items: List<T>, selected: String, onItemClick: (T) -> Unit) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Card {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .clickable(interactionSource = remember {
                    MutableInteractionSource()
                }, indication = rememberRipple(), onClick = {
                    expanded = true
                })
                .padding(12.dp, 4.dp)

        ) {
            Text(text = selected, color = Color.Black)
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                for (item in items) {
                    DropdownMenuItem(onClick = {
                        onItemClick(item)
                        expanded = false
                    }) {
                        Text(item.toString(), color = Color.Black)
                    }
                }
            }
        }

    }
}


@Composable
fun RadioStuff(
    selected: Boolean,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = modifier.clickable(
            interactionSource = remember {
                MutableInteractionSource()
            },
            indication = rememberRipple(),
            onClick = onClick
        )
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(selectedColor = ComposeBlue)
        )
        Text(text = label, color = Color.Black)
    }
}


@Composable
fun LayoutItem(text: String) {

    Box(modifier = Modifier.padding(12.dp, 8.dp)) {
        Text(text = text, color = Color.Black)
    }
}

@Preview
@Composable
fun PreviewItem() {
    LayoutItem(text = "Hello")
}

@Preview
@Composable
fun PreviewLayout() {
    Alignments2()
}