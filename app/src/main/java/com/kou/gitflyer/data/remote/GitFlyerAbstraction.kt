package com.kou.gitflyer.data.remote

import com.kou.gitflyer.data.entity.LoginResponse
import retrofit2.Response
import retrofit2.http.*

@JvmSuppressWildcards
interface GitFlyerAbstraction {

    /** Get User **/
    @GET("user")
    suspend fun login(@Header("Authorization") authToken: String="Bearer"): Response<LoginResponse>


}