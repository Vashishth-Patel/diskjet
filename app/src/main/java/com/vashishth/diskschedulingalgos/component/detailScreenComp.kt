package com.vashishth.diskschedulingalgos.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRightAlt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vashishth.diskschedulingalgos.R

@Composable
fun detailScreenComp(title:String,
about:String,
advantage:String,
disadvantage:String,
bgColor: Color
                     ){
    Card(modifier = Modifier
        .fillMaxSize(),
    backgroundColor = colorResource(id = R.color.black)
    ) {
        Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(8.dp)) {
            Spacer(modifier = Modifier.height(30.dp))

            Surface(modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(10.dp)),
            color = bgColor,
            elevation = 5.dp) {
                Column() {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.ArrowRightAlt, contentDescription = "Arrow", tint = colorResource(
                            id = R.color.blue
                        ), modifier = Modifier.size(45.dp))

                        Text(
                            text = "$title",
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier.padding(4.dp),
                            color = Color.Black,
                            fontSize = 28.sp
                        )}
                        Text(
                            text = "$about",
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier
                                .padding(4.dp),
                            fontSize = 20.sp,
                            color = Color.Black,
                            lineHeight = 30.sp
                        )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            Surface(modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                color = bgColor,
                elevation = 5.dp) {
                Column() {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.ArrowRightAlt, contentDescription = "Arrow", tint = colorResource(
                            id = R.color.blue
                        ), modifier = Modifier.size(45.dp))
                        Text(
                            text = "Advantages",
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier.padding(4.dp),
                            color = Color.Black,
                            fontSize = 28.sp
                        )}
                        Text(
                            text = "$advantage",
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier
                                .padding(4.dp),
                            fontSize = 20.sp,
                            color = Color.Black,
                            lineHeight = 30.sp
                        )

                }
            }

            Spacer(modifier = Modifier.height(15.dp))
            Surface(modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                color = bgColor,
                elevation = 5.dp) {
                Column() {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.ArrowRightAlt, contentDescription = "Arrow", tint = colorResource(
                            id = R.color.blue
                        ), modifier = Modifier.size(45.dp))
                        Text(
                            text = "Dis Advantages",
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier.padding(4.dp),
                            color = Color.Black,
                            fontSize = 28.sp
                        )}
                        Text(
                            text = "$disadvantage",
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier
                                .padding(4.dp),
                            fontSize = 20.sp,
                            color = Color.Black,
                            lineHeight = 30.sp
                        )
                }
            }

        }

    }

}