package com.aplication.techforest.repository

import com.aplication.techforest.model.Device
import com.aplication.techforest.network.ApiInterface
import com.aplication.techforest.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class DeviceRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {
    suspend fun getDevice(): Resource<List<Device>>{
        val response = try {
            apiInterface.getDeviceData()
        } catch (e: Exception){
            return Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }
}