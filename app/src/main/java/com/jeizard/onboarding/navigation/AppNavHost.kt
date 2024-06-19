package com.jeizard.onboarding.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import com.jeizard.onboarding.ui.screens.HomeScreen
import com.jeizard.onboarding.ui.screens.OnBoardingScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = NavigationItem.OnBoarding.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.OnBoarding.route) {
            OnBoardingScreen(navController)
        }
        composable(NavigationItem.Home.route) {
            HomeScreen()
        }
    }
}