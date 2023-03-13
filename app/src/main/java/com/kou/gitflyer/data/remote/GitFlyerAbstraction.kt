package com.kou.gitflyer.data.remote

import com.kou.gitflyer.data.entity.User
import retrofit2.Response
import retrofit2.http.*

@JvmSuppressWildcards
interface GitFlyerAbstraction {

    /** Get User **/
    @GET("user")
    suspend fun login(@Header("Authorization") authToken: String): Response<User>

    @GET("users")
    suspend fun getUsers(@Query("per_page")page:Int,@Query("since")since:Int):Response<List<User>>

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: String): Response<User>
}