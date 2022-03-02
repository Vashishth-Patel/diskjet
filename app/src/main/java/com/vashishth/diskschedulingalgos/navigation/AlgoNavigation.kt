package com.vashishth.diskschedulingalgos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vashishth.diskschedulingalgos.SplashScreen
import com.vashishth.diskschedulingalgos.screens.*

@Composable
fun AlgoNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Screen.SplashScreen.route){
        composable(Screen.SplashScreen.route){
                SplashScreen(navController = navController)
        }
        composable(Screen.HomeScreen.route){
            //here we pass where this should lead us to
            HomeScreen(navController = navController)
        }
        composable(Screen.AlgoListScreen.route){
            //here we pass where this should lead us to
            AlgoListScreen(navController = navController)
        }
        composable(Screen.CalculationScreen.route){
            //here we pass where this should lead us to
            CalculationScreen(navController = navController)
        }
        composable(Screen.AlgoDetailScreen.route+"/{algo}",
        arguments = listOf(navArgument(name = "algo"){type = NavType.StringType})){
            backStackEntry ->

            //here we pass where this should lead us to
            AlgoDetailScreen(navController = navController,backStackEntry.arguments?.getString("algo"))
        }
        composable(Screen.homeDetailScreen.route+"/{detail}",
            arguments = listOf(navArgument(name = "detail"){type = NavType.StringType})){
                backStackEntry ->

            //here we pass where this should lead us to
            homeDetailScreen(navController = navController,backStackEntry.arguments?.getString("detail"))
        }
        composable(Screen.GraphScreen.route){
            //here we pass where this should lead us to
            GraphScreen(navController = navController)
        }

    }
}