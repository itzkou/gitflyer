package com.kou.gitflyer.presentation.profile

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
class ProfileViewModel@Inject constructor(private val repository: GitFlyerRepoAbstraction,
                                          private val prefsStore: PrefsStore
):ViewModel() {
    private val _user = MutableLiveData<Resource<User>>()
    val user = _user
    private val myToken = prefsStore.getToken()
    private val login = prefsStore.getId()



    fun getUser() {
        viewModelScope.launch {
            myToken.collect { token ->
                token?.let {
                    login.collect{ login ->
                        login?.let {
                            repository.getUser(it, it).collect { response ->
                                response?.let {
                                    _user.value = response
                                }
                            }
                        }
                    }

                }
            }

        }

    }
}