package com.kou.gitflyer.data.remote

import javax.inject.Inject

class GitFlyerRemote @Inject constructor(private val gitFlyerApi: GitFlyerAbstraction) : RetrofitSource() {
    suspend fun getUser(authToken: String) =
        getResource { gitFlyerApi.getUser(authToken) }
}