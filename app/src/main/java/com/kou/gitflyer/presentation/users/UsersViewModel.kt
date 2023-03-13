package com.kou.gitflyer.presentation.users

import androidx.lifecycle.MutableLiveData
import com.kou.gitflyer.data.entity.User
import com.kou.gitflyer.data.prefs.PrefsStore
import com.kou.gitflyer.data.repo.GitFlyerRepoAbstraction
import com.kou.gitflyer.utils.Resource
import javax.inject.Inject

class UsersViewModel@Inject constructor(private val repository: GitFlyerRepoAbstraction,
                                        private val prefsStore: PrefsStore
) {
    private val _users = MutableLiveData<Resource<List<User>>>()
    val users = _users
    val authToken = prefsStore.getToken()


}