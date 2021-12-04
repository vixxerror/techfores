package com.aplication.techforest.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aplication.techforest.R
import com.aplication.techforest.model.Device
import com.aplication.techforest.presentation.components.Feature
import com.aplication.techforest.repository.DeviceRepository
import com.aplication.techforest.ui.theme.BlueViolet1
import com.aplication.techforest.ui.theme.BlueViolet2
import com.aplication.techforest.ui.theme.BlueViolet3
import com.aplication.techforest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: DeviceRepository
) : ViewModel() {

    var deviceList = mutableStateOf<List<Device>>(listOf())
    var featureList = mutableListOf<Feature>()
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    init {
        loadDevicesHome()
    }

    fun loadDevicesHome() {
        viewModelScope.launch {
            isLoading.value = true
            when (val result = repository.getDevice()) {
                is Resource.Success -> {
                    val devicesEntries = result.data!!
                    devicesEntries.forEach {
                        val feature = Feature(
                            it.name,
                            R.drawable.ic_baseline_circle_24,
                            BlueViolet1,
                            BlueViolet2,
                            BlueViolet3,
                            it.thumb
                        )
                        featureList.add(feature)
                    }
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