package com.example.repository.mapper

import com.example.domain.model.User
import com.example.repository.remote.dto.LoginResponseDto

fun LoginResponseDto.toDomain(email: String): User = User(email, this.token)