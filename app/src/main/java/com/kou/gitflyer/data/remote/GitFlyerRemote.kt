package com.kou.gitflyer.data.remote

import javax.inject.Inject

class GitFlyerRemote @Inject constructor(private val gitFlyerApi: GitFlyerAbstraction) : RetrofitSource() {
    suspend fun login(authToken: String) =
        getResource { gitFlyerApi.login(authToken) }
    suspend fun getUsers( page:Int, since:Int) =
        getResource { gitFlyerApi.getUsers(page,since) }
    suspend fun getUser(id:String) =
        getResource {
            gitFlyerApi.getUser(id)
        }
}