package com.kou.gitflyer.data.remote

import javax.inject.Inject

class GitFlyerApiAbstraction @Inject constructor(private val gitFlyerApi: GitFlyerApi) : RetrofitSource() {
    suspend fun getUser(authToken: String) =
        getResource { gitFlyerApi.getUser(authToken) }
}