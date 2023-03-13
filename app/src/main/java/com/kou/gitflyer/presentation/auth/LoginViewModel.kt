package com.kou.gitflyer.presentation.auth

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
class LoginViewModel@Inject constructor(private val repository: GitFlyerRepoAbstraction,
                                        private val prefsStore: PrefsStore
):ViewModel() {
    private val _user = MutableLiveData<Resource<User>>()
    val loginResponse = _user


    fun login(authToken:String) {
        viewModelScope.launch {
            repository.login(authToken).collect { response ->
                response?.let {
                    _user.value = response
                }
            }
            prefsStore.setToken(authToken)
        }

    }
}