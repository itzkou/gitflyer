package com.kou.gitflyer.presentation.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kou.gitflyer.data.entity.User
import com.kou.gitflyer.data.prefs.PrefsStore
import com.kou.gitflyer.data.repo.GitFlyerRepoAbstraction
import com.kou.gitflyer.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: GitFlyerRepoAbstraction,
    private val prefsStore: PrefsStore
) : ViewModel() {
    private val _users = MutableLiveData<Resource<List<User>>>()
    val users = _users
    private val _user = MutableLiveData<Resource<User>>()

    fun getUsers(since: Int, page: Int) {

        viewModelScope.launch {
            repository.getUsers(page, since).collect { response ->
                response?.let {
                    _users.value = response
                }
            }
        }

    }

    fun setId(id: String) {
        viewModelScope.launch {
            prefsStore.setId(id)
        }
    }


}