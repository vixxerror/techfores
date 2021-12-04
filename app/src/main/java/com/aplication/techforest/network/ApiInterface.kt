package com.aplication.techforest.network

import com.aplication.techforest.model.Device
import com.aplication.techforest.model.LoginState
import com.aplication.techforest.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import java.lang.annotation.Documented
import javax.inject.Singleton

@Singleton
interface ApiInterface {

    @GET("devices")
    suspend fun getDeviceData() : List<Device>

    @GET("users/usuario={usuario}")
    suspend fun getuser(@Path("usuario") usuario: String) : UserResponse
}

