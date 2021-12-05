package com.aplication.techforest.viewmodel

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aplication.techforest.R
import com.aplication.techforest.model.LoginState
import com.aplication.techforest.model.UserResponse
import com.aplication.techforest.repository.DeviceRepository
import com.aplication.techforest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: DeviceRepository
) : ViewModel() {

    var userList = mutableStateOf<List<UserResponse>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)


    val state: MutableState<LoginState> = mutableStateOf(LoginState())

    fun login(email: String, password: String) {
        viewModelScope.launch {

            state.value = state.value.copy(displayProgressBar = true)

            val result: Resource<UserResponse> = repository.getUser(usuario = email)

            val userEntries = result.data!!

            val errorMessage = if (email.isBlank() || password.isBlank()) {
                R.string.error_input_empty
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                R.string.error_not_a_valid_email
            } else if (email != userEntries.usuario || password != userEntries.clave) {
                Log.d(
                    "TAG",
                    "${userEntries.usuario}, ${userEntries.clave}, validar = ${email}, ${password}"
                )
                R.string.error_invalid_credentials
            } else if(email == userEntries.usuario || password == userEntries.clave)  {
                delay(3000)

                Log.d(
                    "TAG",
                    "${userEntries.usuario}, ${userEntries.clave}, validar = ${email}, ${password}"
                )

                state.value = state.value.copy(email = email, password = password)
                state.value = state.value.copy(displayProgressBar = false)
                state.value = state.value.copy(successLogin = true)

            }else null

            errorMessage?.let {
                state.value = state.value.copy(errorMessage = it)
                state.value = state.value.copy(displayProgressBar = false)
                return@launch
            }

        }
    }

    fun hideErrorDialog() {
        state.value = state.value.copy(
            errorMessage = null
        )
    }
/*
    fun login(email: String, password: String) {
        viewModelScope.launch {
            state.value = state.value.copy(displayProgressBar = true)
            when (val result = repository.getUser(usuario = email)) {
                is Resource.Success -> {
                    val userEntries = result.data!!
                    val errorMessage = if (email.isBlank() || password.isBlank()) {
                        R.string.error_input_empty
                    } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        R.string.error_not_a_valid_email
                    } else if (email != userEntries.usuario || password != userEntries.clave) {
                        Log.d(
                            "TAG",
                            "${userEntries.usuario}, ${userEntries.clave}, validar = ${email}, ${password}"
                        )
                        R.string.error_invalid_credentials
                    } else if (email == userEntries.usuario || password == userEntries.clave) {
                        delay(3000)
                        state.value = state.value.copy(email = email, password = password)
                        state.value = state.value.copy(displayProgressBar = false)
                        state.value = state.value.copy(successLogin = true)

                    } else null

                    errorMessage?.let {
                        state.value = state.value.copy(errorMessage = it)
                        state.value = state.value.copy(displayProgressBar = false)
                        return@launch
                    }
                }
                is Resource.Error -> {
                    state.value = state.value.copy(errorMessage = result.message)
                    state.value = state.value.copy(displayProgressBar = false)
                }
                is Resource.Loading -> TODO()
            }
        }
    }
*/
}