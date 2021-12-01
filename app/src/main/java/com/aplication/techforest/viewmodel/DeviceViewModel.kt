package com.aplication.techforest.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aplication.techforest.model.Device
import com.aplication.techforest.repository.DeviceRepository
import com.aplication.techforest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DeviceViewModel @Inject constructor(
    private val deviceRepository: DeviceRepository
) : ViewModel() {

    var isLoading = mutableStateOf(false)
    private var _getDeviceData: MutableLiveData<List<Device>> = MutableLiveData<List<Device>>()
    var getDeviceData: LiveData<List<Device>> = _getDeviceData

    suspend fun getDeviceData(): Resource<List<Device>> {
        val result = deviceRepository.getDevice()
        if (result is Resource.Success) {
            isLoading.value = true
            _getDeviceData.value = result.data!!
        }
        return result
    }
}