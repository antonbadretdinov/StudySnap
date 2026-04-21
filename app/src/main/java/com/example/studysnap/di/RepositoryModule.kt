package com.example.studysnap.di

import com.example.studysnap.data.repositories.AiRepositoryImpl
import com.example.studysnap.domain.repositories.AiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAiRepository(): AiRepository = AiRepositoryImpl()
}