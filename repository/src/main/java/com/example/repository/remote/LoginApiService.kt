package com.example.repository.remote

import com.example.repository.remote.dto.LoginRequestDto
import com.example.repository.remote.dto.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {
    @POST("login")
    suspend fun login(@Body request: LoginRequestDto): LoginResponseDto
}