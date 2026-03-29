package com.example.studysnap.di

import com.example.studysnap.data.repositories.ExampleRepositoryImpl
import com.example.studysnap.domain.repositories.ExampleRepository
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
    fun provideExampleRepository(): ExampleRepository = ExampleRepositoryImpl()
}