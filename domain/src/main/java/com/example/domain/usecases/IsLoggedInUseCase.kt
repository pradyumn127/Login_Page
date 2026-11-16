package com.example.domain.usecases

import com.example.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsLoggedInUseCase @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke(): Flow<Boolean> = repository.isLoggedIn()
}