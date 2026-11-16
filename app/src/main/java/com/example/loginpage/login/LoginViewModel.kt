package com.example.loginpage.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.LoginUseCase
import com.example.loginpage.state.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.NoError)
    val loginUiState = _loginUiState.asStateFlow()

    fun login(email: String?, password: String?) {
        if (email.isNullOrBlank() || password.isNullOrBlank()) {
            _loginUiState.value = LoginUiState.Error("Email and Password are required")
            return
        }
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = loginUseCase(email, password)
                result.fold(onSuccess = { user ->
                    _loginUiState.value = LoginUiState.Success(user)
                }, onFailure = { exception ->
                    val message = when {
                        exception.message?.contains("400") == true -> "Invalid credential"
                        else -> "Login failed"
                    }
                    _loginUiState.value = LoginUiState.Error(message)
                })
            }
        }
    }

    fun clearError() {
        _loginUiState.value = LoginUiState.NoError
    }
}