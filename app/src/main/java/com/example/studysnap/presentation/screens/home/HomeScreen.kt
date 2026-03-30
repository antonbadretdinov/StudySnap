package com.example.studysnap.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.studysnap.R

@Composable
fun HomeScreen(
    onNavigateToSummary: (String) -> Unit,
    onNavigateToEasierExplain: (String) -> Unit,
    onNavigateToMakeQuestions: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect { event ->
            when (event) {
                HomeNavigationEvent.NavigateToSummary -> {
                    onNavigateToSummary(state.text)
                }

                HomeNavigationEvent.NavigateToEasierExplain -> {
                    onNavigateToEasierExplain(state.text)
                }

                HomeNavigationEvent.NavigateToMakeQuestions -> {
                    onNavigateToMakeQuestions(state.text)
                }
            }
        }
    }

    HomeScreenContent(
        state = state,
        onTextChanged = viewModel::onTextChanged,
        onSummarizeClick = viewModel::onSummarizeClick,
        onSimplifyClick = viewModel::onSimplifyClick,
        onQuestionsClick = viewModel::onQuestionsClick
    )
}

@Composable
private fun HomeScreenContent(
    state: HomeState,
    onTextChanged: (String) -> Unit,
    onSummarizeClick: () -> Unit,
    onSimplifyClick: () -> Unit,
    onQuestionsClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 24.dp)
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.study_header),
                contentDescription = "Иллюстрация обучения",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "AI-помощник для учебных заметок",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.55f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = state.text,
                    onValueChange = onTextChanged,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp),
                    placeholder = {
                        Text(
                            text = "Введите текст лекции или конспекта",
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f)
                        )
                    },
                    shape = RoundedCornerShape(20.dp),
                    textStyle = MaterialTheme.typography.bodyLarge.copy(
                        lineHeight = 22.sp
                    ),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences
                    ),
                    isError = !state.isInputTextValid,
                    supportingText = {
                        state.inputError?.let {
                            Text(text = it)
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface
                    )
                )

                Text(
                    text = "${state.text.length}/${HomeViewModel.MAX_NOTE_LENGTH}",
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 4.dp, end = 4.dp),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            ActionButton(
                text = "Кратко",
                isLoading = state.isLoading,
                onClick = onSummarizeClick
            )

            Spacer(modifier = Modifier.height(12.dp))

            ActionButton(
                text = "Объяснить проще",
                isLoading = state.isLoading,
                onClick = onSimplifyClick
            )

            Spacer(modifier = Modifier.height(12.dp))

            ActionButton(
                text = "Сделать вопросы",
                isLoading = state.isLoading,
                onClick = onQuestionsClick
            )
        }
    }
}

@Composable
private fun ActionButton(
    text: String,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = !isLoading,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                strokeWidth = 2.dp
            )
        } else {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreenContent(
            state = HomeState(),
            onTextChanged = {},
            onSummarizeClick = {},
            onSimplifyClick = {},
            onQuestionsClick = {}
        )
    }
}