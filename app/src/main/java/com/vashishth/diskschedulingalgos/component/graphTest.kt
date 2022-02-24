package com.vashishth.diskschedulingalgos.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vashishth.diskschedulingalgos.ui.theme.ButtonBlue

data class Point(val X: Float = 0f, val Y: Float = 0f)

@Preview
@Composable
fun LineChart() {
    // Used to record the zoom size
    var scale by remember { mutableStateOf(1f) }
    val state = rememberTransformableState {
            zoomChange, panChange, rotationChange ->
        scale*=zoomChange
    }
    val point = listOf(
        Point(21f, 10f), Point(20f, 100f), Point(30f, 30f),
        Point(40f, 200f), Point(50f, 120f), Point(60f, 10f),
        Point(70f, 280f), Point(80f, 100f), Point(90f, 10f),
        Point(100f, 100f), Point(110f, 200f)
    )
    val path = Path()
    for ((index, item) in point.withIndex()) {
        if (index == 0) {
            path.moveTo(item.X*scale, item.Y)
        } else {
            path.lineTo(item.X*scale, item.Y)
        }
    }
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(Color.White)
            // Monitor gesture scaling
            .graphicsLayer(
            ).transformable(state)
    ) {
        // draw  X Axis  Y Axis
        drawLine(
            start = Offset(20f, 300f),
            end = Offset(20f, 0f),
            color = Color.Black,
            strokeWidth = 5f
        )
        drawLine(
            start = Offset(20f, 300f),
            end = Offset(510f, 300f),
            color = Color.Black,
            strokeWidth = 5f
        )
        // draw path
        drawPath(
            path = path,
            color = ButtonBlue,
            style = Stroke(width = 4f)
        )
    }
}
