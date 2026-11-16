package com.example.domain.repository

import com.example.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<User>
    suspend fun logout()
    fun isLoggedIn(): Flow<Boolean>
    fun getUserEmail(): Flow<String?>
}