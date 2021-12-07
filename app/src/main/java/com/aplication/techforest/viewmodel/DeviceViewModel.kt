package com.aplication.techforest.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aplication.techforest.model.DeviceResponse
import com.aplication.techforest.repository.DeviceRepository
import com.aplication.techforest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceViewModel @Inject constructor(
    private val repository: DeviceRepository
) : ViewModel() {

    var deviceList = mutableStateOf<List<DeviceResponse>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    init {
        loadDevices()
    }

    fun loadDevices() {
        viewModelScope.launch {
            isLoading.value = true
            when (val result = repository.getDevice()) {
                is Resource.Success -> {
                    val devicesEntries = result.data!!

                    loadError.value = ""
                    isLoading.value = false
                    deviceList.value += devicesEntries
                }
                is Resource.Error -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }
                is Resource.Loading -> TODO()
            }
        }
    }
}