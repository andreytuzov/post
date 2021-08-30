package com.itexus.post.data.remote.api

import com.itexus.post.data.remote.model.UserResponse
import retrofit2.http.GET

interface UserApi {

    @GET("users")
    suspend fun getUsers(): List<UserResponse>
}