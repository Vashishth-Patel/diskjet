package com.vashishth.diskschedulingalgos.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.vashishth.diskschedulingalgos.ui.theme.ButtonBlue

@Composable
fun Button(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    title:String,
    backGroundColor: Color = ButtonBlue,
    elevation: Dp = 4.dp
){
    Surface(modifier = modifier
        .padding(all = 10.dp)
        .height(50.dp)
        .width(150.dp)
        .clickable { onClick.invoke() },
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        color = backGroundColor,
        elevation = elevation) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Text(text = title, modifier = modifier.padding(8.dp), textAlign = TextAlign.Center)
        }
    }

}