package com.vashishth.diskschedulingalgos.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRightAlt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.vashishth.diskschedulingalgos.R
import com.vashishth.diskschedulingalgos.ui.theme.*

@Composable
fun detailText(title : String,information : String){
    Surface(
        modifier = Modifier
            .padding(15.dp),
        shape = RoundedCornerShape(corner = CornerSize(5.dp)),
        elevation = 5.dp,
        color = BlueViolet2
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.ArrowRightAlt, contentDescription = "Arrow", tint = ButtonBlue, modifier = Modifier.size(45.dp))
                Text(text = "$title",
                    modifier = Modifier.padding(2.dp),
                    fontFamily = FontFamily.Monospace,
                    style = MaterialTheme.typography.h5,
                    color = Color.Black
                )
            }
                Text(text = "$information",
                    modifier = Modifier.padding(5.dp),
                    style = MaterialTheme.typography.body1,
                    color = Color.Black
                    )
        }
    }
}