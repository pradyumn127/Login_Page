package com.example.repository.repository

import android.util.Log
import com.example.domain.model.User
import com.example.domain.repository.AuthRepository
import com.example.repository.local.TokenDataStore
import com.example.repository.mapper.createRequestBody
import com.example.repository.mapper.toDomain
import com.example.repository.remote.LoginApiService
import com.example.repository.remote.dto.LoginResponseDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val loginApiService: LoginApiService, private val tokenDataStore: TokenDataStore
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<User> {
        return try {
            val responseToken = loginApiService.login(createRequestBody(email, password))
            tokenDataStore.saveUser(email, responseToken.token)
            Result.success(responseToken.toDomain(email))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout() {
        tokenDataStore.clear()
    }

    override fun isLoggedIn(): Flow<Boolean> {
        return tokenDataStore.token.map { token -> !token.isNullOrBlank() }
    }

    override fun getUserEmail(): Flow<String?> = tokenDataStore.email

}