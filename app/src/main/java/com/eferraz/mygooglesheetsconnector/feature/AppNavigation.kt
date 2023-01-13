package com.eferraz.mygooglesheetsconnector.feature

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.list.FixedIncomeListRoute
import com.eferraz.mygooglesheetsconnector.feature.home.HomeRoute

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {

    val activity = LocalContext.current as ComponentActivity
    val onBackPressed = {
        if (!navController.popBackStack()) {
            activity.onBackPressed()
        }
    }

    //BackHandler(true) {
    //    Log.w("Enir", "back")
    //    onBackPressed()
   // }

    NavHost(navController = navController, startDestination = "HomeRoute") {

        composable(route = "FixedIncomeList") {
            FixedIncomeListRoute(onBackClick = onBackPressed)
        }

        composable(route = "HomeRoute") {
            HomeRoute(onFixedIncomeHeaderClicked = {
                navController.navigate("FixedIncomeList")
            })
        }
    }
}