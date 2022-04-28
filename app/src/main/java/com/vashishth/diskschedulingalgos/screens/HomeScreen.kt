package com.vashishth.diskschedulingalgos.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.vashishth.diskschedulingalgos.component.FeatureItem
import com.vashishth.diskschedulingalgos.component.FeatureItemHome
import com.vashishth.diskschedulingalgos.component.detailText
import com.vashishth.diskschedulingalgos.model.BottomNavItem
import com.vashishth.diskschedulingalgos.model.HomeData
import com.vashishth.diskschedulingalgos.model.getHomeData
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
        HomeScreen1(contentPadding = contentpadding,navController)
    }
}

@Composable
fun HomeScreen1(contentPadding : PaddingValues,navController: NavController){
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


            //test
            var homedata : List<HomeData> = getHomeData()
            LazyColumn(contentPadding = contentPadding,
                verticalArrangement = Arrangement.Center){
                items(items = homedata) {
                    FeatureItemHome(feature = it){
                        navController.navigate(route = Screen.homeDetailScreen.route + "/$it")
                    }
                }
            }


            //test end
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

