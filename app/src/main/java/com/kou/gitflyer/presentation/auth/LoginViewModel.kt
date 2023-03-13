package com.kou.gitflyer.presentation.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kou.gitflyer.data.entity.LoginResponse
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
    private val _loginResponse = MutableLiveData<Resource<LoginResponse>>()
    val loginResponse = _loginResponse


    fun login(authToken:String) {
        viewModelScope.launch {
            repository.login(authToken).collect { response ->
                response?.let {
                    _loginResponse.value = response
                }
            }
            prefsStore.setToken(authToken)
        }

    }
}