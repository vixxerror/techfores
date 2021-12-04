package com.aplication.techforest.viewmodel

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aplication.techforest.R
import com.aplication.techforest.model.Device
import com.aplication.techforest.model.LoginState
import com.aplication.techforest.model.UserResponse
import com.aplication.techforest.repository.UserRepository
import com.aplication.techforest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.annotations.NotNull
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    var userList = mutableStateOf<List<UserResponse>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)


    val state: MutableState<LoginState> = mutableStateOf(LoginState())

    fun login(email: String, password: String) {
        viewModelScope.launch {
            state.value = state.value.copy(displayProgressBar = true)
            val user: Resource<UserResponse> = repository.getUser(email)
            val errorMessage = if (email.isBlank() || password.isBlank()) {
                R.string.error_input_empty
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                R.string.error_not_a_valid_email
            } else if (email != user.data?.usuario || password != user.data?.clave) {
                R.string.error_invalid_credentials
            } else {
                delay(3000)

                state.value = state.value.copy(email = email, password = password)
                state.value = state.value.copy(displayProgressBar = false)
                state.value = state.value.copy(successLogin = true)
            }


            /*
            errorMessage?.let {
                state.value = state.value.copy(errorMessage = it)
                return@launch
            }
            */
        }
    }

    fun hideErrorDialog() {
        state.value = state.value.copy(
            errorMessage = null
        )
    }

}