package com.example.studysnap.presentation.navigation

sealed class Routes(
    val route: String
) {
    data object HomeScreenDestination : Routes("HomeScreenDestination")
}