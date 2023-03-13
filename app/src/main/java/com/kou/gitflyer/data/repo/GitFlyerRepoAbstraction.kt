package com.kou.gitflyer.data.repo

import com.kou.gitflyer.data.entity.LoginResponse
import com.kou.gitflyer.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GitFlyerRepoAbstraction {

    suspend fun login(authToken: String): Flow<Resource<LoginResponse>?>

}