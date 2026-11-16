package com.example.loginpage.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.GetUserEmailUseCase
import com.example.domain.usecases.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val logOutUseCase: LogoutUseCase, getUserEmailUseCase: GetUserEmailUseCase
) : ViewModel() {

    val userEmail: StateFlow<String?> = getUserEmailUseCase().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        null
    )

    fun logOut(onLoggedOut: () -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                logOutUseCase()
            }
            onLoggedOut()
        }
    }
}