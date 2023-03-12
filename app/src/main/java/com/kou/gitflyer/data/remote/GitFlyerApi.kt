package com.kou.gitflyer.data.remote

import com.kou.gitflyer.data.entity.LoginResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

@JvmSuppressWildcards
interface GitFlyerApi {

    /** Get User **/
    @GET("user")
    suspend fun getUser(@Header("Authorization") authToken: String): Response<LoginResponse>


}