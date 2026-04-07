package com.example.studysnap.domain.repositories

interface AiRepository {
    suspend fun summarize(inputText: String): String
    suspend fun explainEasier(inputText: String): String
    suspend fun makeQuestions(inputText: String): String
}