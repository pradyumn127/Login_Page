package com.example.repository.mapper

import com.example.repository.remote.dto.LoginRequestDto

fun createRequestBody(email: String, password: String): LoginRequestDto =
    LoginRequestDto(email, password)