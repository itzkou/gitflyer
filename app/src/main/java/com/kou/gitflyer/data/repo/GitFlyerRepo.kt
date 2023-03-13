package com.kou.gitflyer.data.repo

import com.kou.gitflyer.data.entity.User
import com.kou.gitflyer.data.remote.GitFlyerRemote
import com.kou.gitflyer.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GitFlyerRepo @Inject constructor(
    private val remote: GitFlyerRemote,
    private val ioDispatcher: CoroutineDispatcher
) : GitFlyerRepoAbstraction {
    override suspend fun login(authToken: String): Flow<Resource<User>?> {
        return flow {
            val response = remote.login(authToken)
            emit(response)

        }.flowOn(ioDispatcher)
    }

    override suspend fun getUsers(authToken: String, page: Int, since: Int): Flow<Resource<List<User>>?> {
        return flow {
            val response = remote.getUsers(authToken,page, since)
            emit(response)

        }.flowOn(ioDispatcher)
    }

    override suspend fun getUser(authToken: String, id: String): Flow<Resource<User>?> {
        return flow{
            val response = remote.login(authToken)
            emit(response)
        }.flowOn(ioDispatcher)
    }

}