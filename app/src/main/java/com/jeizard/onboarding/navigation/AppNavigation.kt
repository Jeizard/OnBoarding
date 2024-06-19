package com.jeizard.onboarding.navigation

enum class Screen {
    ONBOARDING,
    HOME,
}
sealed class NavigationItem(val route: String) {
    data object OnBoarding : NavigationItem(Screen.ONBOARDING.name)
    data object Home : NavigationItem(Screen.HOME.name)
}