package com.vashishth.diskschedulingalgos.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vashishth.diskschedulingalgos.component.detailScreenComp
import com.vashishth.diskschedulingalgos.component.detailText
import com.vashishth.diskschedulingalgos.model.getAlgos
import com.vashishth.diskschedulingalgos.model.getHomeData

@Composable
fun homeDetailScreen(navController: NavController, homeData: String?) {
    val algo = getHomeData().filter { hdata ->
        hdata.title == homeData
    }
    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Transparent,
            elevation = 5.dp) {
            Row(horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            navController.popBackStack()
                        })
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = algo[0].title)
            }
        }
    }
    ) {
        Surface(modifier = Modifier.background(Color.Black)) {
            detailText(title = algo[0].title, information = algo[0].Info, bgColor = algo[0].mediumColor)
        }
    }
}