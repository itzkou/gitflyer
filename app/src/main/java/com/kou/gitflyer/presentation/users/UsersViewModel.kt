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
class UsersViewModel@Inject constructor(private val repository: GitFlyerRepoAbstraction,
                                        private val prefsStore: PrefsStore
) :ViewModel(){
    private val _users = MutableLiveData<Resource<List<User>>>()
    val users = _users
    private val myToken = prefsStore.getToken()

    fun getUsers(since:Int, page:Int) {

        viewModelScope.launch {
            myToken.collect{
                it?.let {
                    repository.getUsers(it,page,since).collect { response ->
                        response?.let {
                            _users.value = response
                        }
                    }
                }

            }



        }

    }
}