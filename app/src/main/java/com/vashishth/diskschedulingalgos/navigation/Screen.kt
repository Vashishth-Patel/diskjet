package com.vashishth.diskschedulingalgos.navigation

sealed class Screen(val route : String){
    object HomeScreen : Screen("home_screen")
    object SplashScreen : Screen("splash_screen")
    object homeDetailScreen : Screen("home_detail_screen")
    object AlgoDetailScreen : Screen("algo_detail_screen")
    object AlgoListScreen : Screen("algo_list_screen")
    object CalculationScreen : Screen("calculation_screen")
    object GraphScreen : Screen("graph_screen")
}
