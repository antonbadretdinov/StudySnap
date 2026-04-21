package com.example.studysnap.presentation.screens.easierExplain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studysnap.domain.repositories.AiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class EasierExplainViewModel @Inject constructor(
    private val aiRepository: AiRepository
) : ViewModel() {

    private val _state = MutableStateFlow(EasierExplainState())
    val state: StateFlow<EasierExplainState> = _state.asStateFlow()

    private var lastInputText: String? = null

    fun loadExplanation(inputText: String) {
        if (inputText.isBlank()) {
            _state.update {
                it.copy(
                    isLoading = false,
                    resultText = "",
                    errorMessage = "Input text is empty"
                )
            }
            return
        }

        if (_state.value.isLoading) return
        if (lastInputText == inputText && _state.value.resultText.isNotBlank()) return

        lastInputText = inputText

        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            runCatching {
                aiRepository.explainEasier(inputText)
            }.onSuccess { result ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        resultText = result.ifBlank { "Empty response from Gemini" },
                        errorMessage = null
                    )
                }
            }.onFailure { throwable ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        resultText = "",
                        errorMessage = throwable.message ?: "Unknown error"
                    )
                }
            }
        }
    }
}