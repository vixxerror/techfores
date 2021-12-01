package com.aplication.techforest.network

import com.aplication.techforest.model.Device
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface ApiInterface {

    @GET("devices")
    suspend fun getDeviceData() : List<Device>
}