package com.tayyib.myapplication.models.login

data class AuthResponse(
    val tokenResponse: TokenResponse,
    val loginResponse: LoginResponse
)
