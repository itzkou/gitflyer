package com.kou.gitflyer.data.repo

import com.kou.gitflyer.data.entity.User
import com.kou.gitflyer.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GitFlyerRepoAbstraction {

    suspend fun login(authToken: String): Flow<Resource<User>?>
    suspend fun getUsers( page:Int, since:Int): Flow<Resource<List<User>>?>
    suspend fun getUser(id:String): Flow<Resource<User>?>


}