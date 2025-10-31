package com.example.note_app_compose.constant

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val userId: String?) : AuthState()
    data class Error(val message: String) : AuthState()
}