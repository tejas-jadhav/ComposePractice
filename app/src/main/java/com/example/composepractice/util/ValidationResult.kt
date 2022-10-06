package com.example.composepractice.util

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)

