package com.example.studysnap.presentation.screens.home

data class HomeState(
    val isLoading: Boolean = false,
    val isInputTextValid: Boolean = true,
    val text: String = "",
    val inputError: String? = null
)