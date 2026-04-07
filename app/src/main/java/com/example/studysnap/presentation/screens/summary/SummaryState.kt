package com.example.studysnap.presentation.screens.summary

data class SummaryState(
    val isLoading: Boolean = false,
    val resultText: String = "",
    val errorMessage: String? = null
)