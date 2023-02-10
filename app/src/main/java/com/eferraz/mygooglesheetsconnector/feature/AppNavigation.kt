package com.eferraz.mygooglesheetsconnector.feature

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.ui.list.FixedIncomeListRoute
import com.eferraz.mygooglesheetsconnector.feature.home.ui.HomeRoute
import com.eferraz.mygooglesheetsconnector.feature.login.LoginRoute
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(navController: NavHostController = rememberAnimatedNavController()) {

    BackHandler(enabled = false, onBack = {
        navController.popBackStack()
    })

    AnimatedNavHost(navController = navController, startDestination = "Login") {

        animatedComposable(route = "Login") {
            LoginRoute(onFinish = {
                navController.navigate("HomeRoute") {
                    popUpTo(navController.graph.id) { inclusive = true }
                }
            })
        }

        animatedComposable(route = "FixedIncomeList") {
            FixedIncomeListRoute(onBackClick = navController::popBackStack)
        }

        animatedComposable(route = "HomeRoute") {
            HomeRoute(onFixedIncomeHeaderClicked = {
                navController.navigate("FixedIncomeList")
            })
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.animatedComposable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    content: @Composable (AnimatedVisibilityScope.(NavBackStackEntry) -> Unit)
) = composable(
    route = route,
    arguments = arguments,
    enterTransition = { NavigationTransition.inFromRight },
    exitTransition = { NavigationTransition.outToLeft },
    popEnterTransition = { NavigationTransition.inFromLeft },
    popExitTransition = { NavigationTransition.outToRight },
    content = content
)

private object NavigationTransition {

    private val animation: FiniteAnimationSpec<IntOffset> = tween(easing = FastOutSlowInEasing, durationMillis = 500)

    val outToLeft = slideOutHorizontally(animationSpec = animation) { fullWidth -> -fullWidth }
    val inFromRight = slideInHorizontally(animationSpec = animation) { fullWidth -> fullWidth }
    val outToRight = slideOutHorizontally(animationSpec = animation) { fullWidth -> fullWidth }
    val inFromLeft = slideInHorizontally(animationSpec = animation) { fullWidth -> -fullWidth }
}