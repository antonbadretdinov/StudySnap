package com.example.studysnap.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    companion object {
        const val MAX_NOTE_LENGTH = 5000
    }

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private val _navigationEvent = Channel<HomeNavigationEvent>(Channel.BUFFERED)
    val navigationEvent = _navigationEvent.receiveAsFlow()

    fun onTextChanged(newText: String) {
        val limitedText = newText.take(MAX_NOTE_LENGTH)

        _state.update {
            it.copy(
                text = limitedText,
                isInputTextValid = true,
                inputError = null
            )
        }
    }

    fun onSummarizeClick() {
        val validatedText = validateAndPrepare() ?: return

        viewModelScope.launch {
            // TODO: вызов usecase для summary
            _navigationEvent.send(HomeNavigationEvent.NavigateToSummary)
            setLoading(false)
        }
    }

    fun onSimplifyClick() {
        val validatedText = validateAndPrepare() ?: return

        viewModelScope.launch {
            // TODO: вызов usecase для easier explain
            _navigationEvent.send(HomeNavigationEvent.NavigateToEasierExplain)
            setLoading(false)
        }
    }

    fun onQuestionsClick() {
        val validatedText = validateAndPrepare() ?: return

        viewModelScope.launch {
            // TODO: вызов usecase для questions
            _navigationEvent.send(HomeNavigationEvent.NavigateToMakeQuestions)
            setLoading(false)
        }
    }

    private fun validateAndPrepare(): String? {
        val text = _state.value.text
        val error = validateText(text)

        if (error != null) {
            _state.update {
                it.copy(
                    isInputTextValid = false,
                    inputError = error,
                    isLoading = false
                )
            }
            return null
        }

        _state.update {
            it.copy(
                isInputTextValid = true,
                inputError = null,
                isLoading = true
            )
        }

        return text
    }

    private fun setLoading(isLoading: Boolean) {
        _state.update {
            it.copy(isLoading = isLoading)
        }
    }

    private fun validateText(text: String): String? {
        return when {
            text.isBlank() -> "Введите текст лекции или конспекта"
            text.length > MAX_NOTE_LENGTH -> "Текст не должен превышать $MAX_NOTE_LENGTH символов"
            else -> null
        }
    }
}