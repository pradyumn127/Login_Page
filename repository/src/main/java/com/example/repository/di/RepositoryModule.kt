package com.example.repository.di

import com.example.domain.repository.AuthRepository
import com.example.repository.repository.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(repositoryImpl: AuthRepositoryImpl): AuthRepository
}