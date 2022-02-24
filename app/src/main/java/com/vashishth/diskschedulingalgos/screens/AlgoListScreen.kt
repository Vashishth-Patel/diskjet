package com.vashishth.diskschedulingalgos.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vashishth.diskschedulingalgos.R
import com.vashishth.diskschedulingalgos.component.BottomNavigationBar
import com.vashishth.diskschedulingalgos.component.FeatureItem
import com.vashishth.diskschedulingalgos.model.Algorithm
import com.vashishth.diskschedulingalgos.model.BottomNavItem
import com.vashishth.diskschedulingalgos.model.getAlgos
import com.vashishth.diskschedulingalgos.navigation.Screen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AlgoListScreen(navController: NavController, buttonList: List<Algorithm> = getAlgos()) {
    Scaffold(bottomBar = {
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
    ) { contentPadding ->
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = colorResource(id = R.color.black)
        ) {

            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                    contentPadding = contentPadding,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    items(items = buttonList) {
//                        algoButton(algorithm = it) { algorithm ->
//                            navController.navigate(route = Screen.AlgoDetailScreen.route + "/$algorithm")
//                        }
                        FeatureItem(feature = it){
                            navController.navigate(route = Screen.AlgoDetailScreen.route + "/$it")
                        }
                    }
                }
            }


        }
    }
}


//@Composable
//fun algoButton(algorithm: Algorithm, onClick: (String) -> Unit) {
//
//    Card(
//        modifier = Modifier
//            .padding(5.dp)
//            .fillMaxWidth()
//            .height(100.dp)
//            .clickable { onClick(algorithm.title) },
//        backgroundColor = colorResource(id = R.color.purple_200),
//        shape = RoundedCornerShape(corner = CornerSize(6.dp)),
//        elevation = 5.dp
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Text(text = "${algorithm.title}")
//        }
//    }
//
//}

