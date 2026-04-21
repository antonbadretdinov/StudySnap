package com.example.studysnap.presentation.screens.easierExplain

data class EasierExplainState(
    val isLoading: Boolean = false,
    val resultText: String = "",
    val errorMessage: String? = null
)