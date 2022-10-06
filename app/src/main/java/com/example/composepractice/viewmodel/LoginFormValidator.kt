package com.example.composepractice.viewmodel

import android.util.Patterns
import com.example.composepractice.util.ValidationResult

object LoginFormValidator {


    fun validateEmail(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(false, "Email cannot be empty")
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(false, "Enter a valid email")
        }

        return ValidationResult(true)
    }

    fun validatePassword(password: String): ValidationResult {
        if (password.length < 8) {
            return ValidationResult(
                false,
                "Password should contain minimum 8 characters"
            )
        }
        val hasDigit = password.any { it.isDigit() }
        val hasLetter = password.any { it.isLetter() }
        val hasUpperCaseLetter = password.any { it.isUpperCase() }
        val hasLowerCaseLetter = password.any { it.isLowerCase() }

        val isValidPassword = hasDigit
                && hasLetter
                && hasUpperCaseLetter
                && hasLowerCaseLetter
        if (!isValidPassword) {


            if (!hasUpperCaseLetter && !hasDigit) {
                return ValidationResult(
                    false,
                    "Password should include at least one number and one uppercase character"
                )
            }

            if (!hasLowerCaseLetter && !hasDigit) {
                return ValidationResult(
                    false,
                    "Password should include at least one number and one lowercase character"
                )
            }

            if (!hasLetter) {
                return ValidationResult(
                    false,
                    "Password should contain at least one letter"
                )
            }


            if (!hasLowerCaseLetter) {
                return ValidationResult(
                    false,
                    "Password should contain at least one lowercase letter"
                )
            }

            if (!hasUpperCaseLetter) {
                return ValidationResult(
                    false,
                    "Password should contain at least one uppercase letter"
                )
            }


            if (!hasDigit) {
                return ValidationResult(
                    false,
                    "Password should contain at least one digit"
                )
            }


        }

        return ValidationResult(true)
    }

}