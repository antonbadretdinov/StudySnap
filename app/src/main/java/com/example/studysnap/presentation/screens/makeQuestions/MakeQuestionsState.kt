package com.example.studysnap.presentation.screens.makeQuestions

data class MakeQuestionsState(
    val isLoading: Boolean = false,
    val resultText: String = "",
    val errorMessage: String? = null
)