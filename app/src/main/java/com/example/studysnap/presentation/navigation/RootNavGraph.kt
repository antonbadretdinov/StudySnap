package com.example.studysnap.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.studysnap.presentation.screens.home.HomeScreen

@Composable
fun RootNavGraph(
    navController: NavHostController,
    startDestination: String = Routes.HomeScreenDestination.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = Routes.HomeScreenDestination.route
        ) {
            HomeScreen()
        }
    }
}