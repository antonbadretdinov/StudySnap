package com.example.studysnap.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.studysnap.presentation.screens.easierExplain.EasierExplainScreen
import com.example.studysnap.presentation.screens.home.HomeScreen
import com.example.studysnap.presentation.screens.makeQuestions.MakeQuestionsScreen
import com.example.studysnap.presentation.screens.summary.SummaryScreen

private const val INPUT_TEXT_KEY = "input_text"

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
            HomeScreen(
                onNavigateToSummary = { inputText ->
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set(INPUT_TEXT_KEY, inputText)

                    navController.navigate(Routes.SummaryScreenDestination.route)
                },
                onNavigateToEasierExplain = { inputText ->
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set(INPUT_TEXT_KEY, inputText)

                    navController.navigate(Routes.EasierExplainScreenDestination.route)
                },
                onNavigateToMakeQuestions = { inputText ->
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set(INPUT_TEXT_KEY, inputText)

                    navController.navigate(Routes.MakeQuestionsScreenDestination.route)
                }
            )
        }

        composable(
            route = Routes.SummaryScreenDestination.route
        ) {
            SummaryScreen(
                inputText = navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<String>("input_text")
                    .orEmpty(),
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Routes.EasierExplainScreenDestination.route
        ) {
            EasierExplainScreen(
                inputText = navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<String>("input_text")
                    .orEmpty(),
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Routes.MakeQuestionsScreenDestination.route
        ) {
            MakeQuestionsScreen(
                inputText = navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<String>("input_text")
                    .orEmpty(),
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}