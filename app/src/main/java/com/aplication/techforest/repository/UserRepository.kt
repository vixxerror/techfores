package com.aplication.techforest.repository

import com.aplication.techforest.model.Device
import com.aplication.techforest.model.UserResponse
import com.aplication.techforest.network.ApiInterface
import com.aplication.techforest.utils.Resource
import retrofit2.http.Path
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {
    suspend fun getUser(usuario:String): Resource<UserResponse> {
        val response = try {
            apiInterface.getuser(usuario)
        } catch (e: Exception){
            return Resource.Error("An unknown error occurred:")
        }
        return Resource.Success(response)
    }
}