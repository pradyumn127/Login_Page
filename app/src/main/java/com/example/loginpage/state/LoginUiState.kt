package com.example.loginpage.state

import com.example.domain.model.User

sealed interface LoginUiState {
    data object NoError: LoginUiState
    data object Loading : LoginUiState
    data class Success(val user: User) : LoginUiState
    data class Error(val error: String) : LoginUiState
}