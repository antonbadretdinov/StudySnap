package com.example.studysnap.data.repositories

import com.example.studysnap.data.utils.PromptUtils.EXPLAIN_EASIER_PROMPT
import com.example.studysnap.data.utils.PromptUtils.MAKE_QUESTIONS_PROMPT
import com.example.studysnap.data.utils.PromptUtils.MODEL_NAME
import com.example.studysnap.data.utils.PromptUtils.SUMMARIZE_PROMPT
import com.example.studysnap.data.utils.PromptUtils.buildPrompt
import com.example.studysnap.domain.repositories.AiRepository
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import javax.inject.Inject

class AiRepositoryImpl @Inject constructor() : AiRepository {

    private val model by lazy {
        Firebase.ai(backend = GenerativeBackend.googleAI()).generativeModel(MODEL_NAME)
    }

    override suspend fun summarize(inputText: String): String {
        val response = model.generateContent(
            prompt = buildPrompt(
                basePrompt = SUMMARIZE_PROMPT,
                inputText = inputText
            )
        )
        return response.text?.trim().orEmpty()
    }

    override suspend fun explainEasier(inputText: String): String {
        val response = model.generateContent(
            prompt = buildPrompt(
                basePrompt = EXPLAIN_EASIER_PROMPT,
                inputText = inputText
            )
        )
        return response.text?.trim().orEmpty()
    }

    override suspend fun makeQuestions(inputText: String): String {
        val response = model.generateContent(
            prompt = buildPrompt(
                basePrompt = MAKE_QUESTIONS_PROMPT,
                inputText = inputText
            )
        )
        return response.text?.trim().orEmpty()
    }
}