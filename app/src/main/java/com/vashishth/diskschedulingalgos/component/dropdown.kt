package com.vashishth.diskschedulingalgos.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.vashishth.diskschedulingalgos.R
import com.vashishth.diskschedulingalgos.ui.theme.ButtonBlue

@Composable
fun DropdownMenu(
    colorSelected: Color = colorResource(id = R.color.purple_200),
    colorBackground: Color = colorResource(id = R.color.lightblack),
    expanded: Boolean,
    selectedIndex: Int,
    items: List<String>,
    onSelect: (Int) -> Unit,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) = Box(modifier = Modifier) {
    content()
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.lightblack),
                shape = RoundedCornerShape(30.dp)
            )
    ) {
        items.forEachIndexed { index, s ->
            if (selectedIndex == index) {
                DropdownMenuItem(
                    modifier = Modifier
                        .padding(15.dp)
                        .background(
                            color = colorSelected,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    onClick = { onSelect(index) }
                ) {
                    Text(
                        text = s,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            } else {
                DropdownMenuItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    onClick = { onSelect(index) }
                ) {
                    Text(
                        text = s,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

val items = listOf(
    "FCFS",
    "SSTF",
    "SCAN",
    "C-SCAN"
)

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun TestDropdownMenu() {
    var expanded by remember { mutableStateOf(false) }

    var selectedIndex by remember { mutableStateOf(0) }
    val buttonTitle = items[selectedIndex]
    DropdownMenu(
        expanded = expanded,
        selectedIndex = selectedIndex,
        items = items,
        onSelect = { index ->
            selectedIndex = index
            expanded = false
        },
        onDismissRequest = {
            expanded = false
        }) {

            Button(modifier = Modifier.padding(15.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = ButtonBlue),
                onClick = {
                    expanded = true
                }
            ) {
                Text(
                    text = buttonTitle,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
    }
}