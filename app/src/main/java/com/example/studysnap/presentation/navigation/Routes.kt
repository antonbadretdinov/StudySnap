package com.example.studysnap.presentation.navigation

sealed class Routes(
    val route: String
) {
    data object HomeScreenDestination : Routes("HomeScreenDestination")
    data object SummaryScreenDestination : Routes("SummaryScreenDestination")
    data object EasierExplainScreenDestination : Routes("EasierExplainScreenDestination")
    data object MakeQuestionsScreenDestination : Routes("MakeQuestionsScreenDestination")
}