package com.vashishth.diskschedulingalgos.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vashishth.diskschedulingalgos.DiskImg
import com.vashishth.diskschedulingalgos.R
import com.vashishth.diskschedulingalgos.component.BottomNavigationBar
import com.vashishth.diskschedulingalgos.component.detailText
import com.vashishth.diskschedulingalgos.model.BottomNavItem
import com.vashishth.diskschedulingalgos.navigation.Screen
import com.vashishth.diskschedulingalgos.ui.theme.BlueViolet1
import com.vashishth.diskschedulingalgos.ui.theme.BlueViolet2
import com.vashishth.diskschedulingalgos.ui.theme.BlueViolet3

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(items = listOf(
                BottomNavItem(
                    name = "Home",
                    route = Screen.HomeScreen.route,
                    Icon = Icons.Default.Home
                ),
                BottomNavItem(
                    name = "Algos",
                    route = Screen.AlgoListScreen.route,
                    Icon = Icons.Default.Code
                ),
                BottomNavItem(
                    name = "Calculation",
                    route = Screen.CalculationScreen.route,
                    Icon = Icons.Default.Calculate
                )
            ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                })
        }
    ) {contentpadding ->
        HomeScreen1(contentPadding = contentpadding )
    }
}

@Composable
fun HomeScreen1(contentPadding : PaddingValues){
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        color = Color.Black) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top) {
            title()
            Spacer(modifier = Modifier.height(50.dp))
            DiskImg()
            Spacer(modifier = Modifier.height(25.dp))
            LazyColumn (contentPadding = contentPadding){
                items(1) {
                    detailText(
                        title = "Disk Scheduling",
                        information = "As we know, a process needs two type of time, CPU time and IO time. For I/O, it requests the Operating system to access the disk.\n" +
                                "\n" +
                                "However, the operating system must be fare enough to satisfy each request and at the same time, operating system must maintain the efficiency and speed of process execution.\n" +
                                "\n" +
                                "The technique that operating system uses to determine the request which is to be satisfied next is called disk scheduling."
                    )
                    detailText(
                        title = "Types of Disk Scheduling Algorithms",
                        information = "The list of various disks scheduling algorithm is given below. Each algorithm is carrying some advantages and disadvantages. The limitation of each algorithm leads to the evolution of a new algorithm.\n" +
                                "\n" +
                                "FCFS scheduling algorithm\n" +
                                "SSTF (shortest seek time first) algorithm\n" +
                                "SCAN scheduling\n" +
                                "C-SCAN scheduling\n" +
                                "LOOK Scheduling\n" +
                                "C-LOOK scheduling"
                    )
                }
            }
        }
    }
}

@Composable
fun title(){
    Text(text = "Disk Scheduling Algorithms",
        modifier = Modifier.clip(shape = RoundedCornerShape(corner = CornerSize(3.dp))),
        style = MaterialTheme.typography.h5,
    )
}

