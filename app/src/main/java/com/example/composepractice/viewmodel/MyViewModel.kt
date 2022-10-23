package com.example.composepractice.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composepractice.data.model.LoginFormState
import com.example.composepractice.data.repository.PracticeRepository
import com.example.composepractice.util.Constants
import com.example.composepractice.util.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    private val TAG = "MyViewModel"

    private val repository = PracticeRepository()
    private var _practiceExamples = mutableStateOf(
        repository.getPracticeExamples().sortedByDescending { it.orderNumber }
    )

    private var _sortType = Constants.DESCENDING
    val sortType get() = _sortType

    fun getPracticeExamples() = _practiceExamples

    private fun sortByLatest() {
        _practiceExamples.value = _practiceExamples.value.sortedByDescending { it.orderNumber }
        _sortType = Constants.DESCENDING
    }

    private fun sortByOldest() {
        _practiceExamples.value = _practiceExamples.value.sortedBy { it.orderNumber }
        _sortType = Constants.ASCENDING
    }

    fun filterByQuery(text: String) {
        if (text.isBlank()) {
            _practiceExamples.value = repository.getPracticeExamples()
            if (sortType == Constants.DESCENDING) {
                sortByLatest()
            }
            return
        }
        _practiceExamples.value = repository.getPracticeExamples().filter {
            Log.e(TAG, "filterByQuery: ${it.name} in viewmodel")
            it.name.contains(
                text,
                ignoreCase = true
            ) || it.description.contains(text, ignoreCase = true)
        }
    }

    fun onFilterEvent(event: MainActivityEvents.FilterEvent) {
        when (event) {
            is MainActivityEvents.FilterEvent.ExpandedClick -> {
                when (_sortType) {
                    Constants.ASCENDING -> sortByLatest()
                    Constants.DESCENDING -> sortByOldest()
                }
            }
            is MainActivityEvents.FilterEvent.CollapsedClick -> {

            }
        }
    }


    // for login
    var loginState by mutableStateOf(LoginFormState())
    private val loginAction = Channel<Resource<LoginFormState>>()
    val loginEvent = loginAction.receiveAsFlow()

    fun onLoginFormEvent(event: LoginFormEvent) {
        when (event) {
            is LoginFormEvent.EmailChanged -> {
                loginState = loginState.copy(email = event.email)
            }
            is LoginFormEvent.PasswordChanged -> {
                loginState = loginState.copy(password = event.password)
            }
            LoginFormEvent.Submit -> if (isLoginFormValid()) {
                Log.e(TAG, "onLoginFormEvent: form is valid so submitting")
                loginFormSubmit()
            }
        }
    }

    private fun loginFormSubmit() {
        viewModelScope.launch {
            loginAction.send(Resource.Loading())
            loginState = loginState.copy(isSubmitting = true)
//            simulate api call
            delay(2000)
            loginAction.send(Resource.Success(loginState))
        }
    }

    private fun isLoginFormValid(): Boolean {
        val emailResult = LoginFormValidator.validateEmail(loginState.email)
        val passwordResult = LoginFormValidator.validatePassword(loginState.password)

        val hasError = listOf(
            emailResult,
            passwordResult
        ).any {
            !it.successful
        }

        if (hasError) {
            loginState = loginState.copy(emailError = emailResult.errorMessage)
            loginState = loginState.copy(passwordError = passwordResult.errorMessage)
        } else {
            loginState = loginState.copy(emailError = null)
            loginState = loginState.copy(passwordError = null)
        }

        return !hasError
    }
}