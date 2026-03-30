package com.example.studysnap.presentation.screens.home

sealed interface HomeNavigationEvent {
    data object NavigateToSummary : HomeNavigationEvent
    data object NavigateToEasierExplain : HomeNavigationEvent
    data object NavigateToMakeQuestions : HomeNavigationEvent
}