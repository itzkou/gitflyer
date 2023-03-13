package com.kou.gitflyer.data.remote

import javax.inject.Inject

class GitFlyerRemote @Inject constructor(private val gitFlyerApi: GitFlyerAbstraction) : RetrofitSource() {
    suspend fun login(authToken: String) =
        getResource { gitFlyerApi.login(authToken) }
}