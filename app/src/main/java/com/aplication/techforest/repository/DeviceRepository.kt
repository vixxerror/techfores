package com.aplication.techforest.repository

import com.aplication.techforest.model.DeviceResponse
import com.aplication.techforest.model.UserResponse
import com.aplication.techforest.network.ApiInterface
import com.aplication.techforest.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class DeviceRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {
    suspend fun getDevice(): Resource<List<DeviceResponse>>{
        val response = try {
            apiInterface.getDeviceData()
        } catch (e: Exception){
            return Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }

    suspend fun getUser(usuario:String): Resource<List<UserResponse>> {
        val response = try {
            apiInterface.getuser(usuario)
        } catch (e: Exception){
            return Resource.Error("An unknown error occurred: ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }
}