package com.kou.gitflyer.data.remote

import javax.inject.Inject

class GitFlyerRemote @Inject constructor(private val gitFlyerApi: GitFlyerAbstraction) : RetrofitSource() {
    suspend fun login(authToken: String) =
        getResource { gitFlyerApi.login(authToken) }
    suspend fun getUsers(authToken: String,page:Int,size:Int) =
        getResource { gitFlyerApi.getUsers(authToken,page,size) }
}