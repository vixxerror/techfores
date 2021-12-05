package com.aplication.techforest.network

import com.aplication.techforest.model.DeviceResponse
import com.aplication.techforest.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface ApiInterface {

    @GET("devices")
    suspend fun getDeviceData() : List<DeviceResponse>

    @GET("users/")
    suspend fun getuser(@Query("usuario") usuario : String) : List<UserResponse>
}

