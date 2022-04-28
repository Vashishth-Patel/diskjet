package com.vashishth.diskschedulingalgos.screens

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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vashishth.diskschedulingalgos.component.detailScreenComp
import com.vashishth.diskschedulingalgos.model.getAlgos


@Composable
fun AlgoDetailScreen(navController: NavController, algoData: String?) {
    val algo = getAlgos().filter { algo ->
        algo.title == algoData
    }
    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Transparent,
            elevation = 5.dp) {
            Row(horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back",
                    modifier = Modifier.size(35.dp).clickable {
                        navController.popBackStack()
                    })
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = algo[0].title, fontSize = 30.sp)
            }
    }
    }
    ) {
        androidx.compose.material.Surface(modifier = Modifier.fillMaxSize()) {
            detailScreenComp(title = algo[0].title, about = algo[0].about, advantage = algo[0].advantage, disadvantage = algo[0].disadvantage, bgColor = algo[0].mediumColor)
        }
    }
}