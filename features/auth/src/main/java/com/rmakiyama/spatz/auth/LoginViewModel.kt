package com.rmakiyama.spatz.auth

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmakiyama.spatz.domain.model.auth.AuthUser
import com.rmakiyama.spatz.usecase.auth.SaveAuthUserUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel @ViewModelInject constructor(
    private val saveAuthUser: SaveAuthUserUseCase
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> get() = _loading

    // TODO
    private val _succeeded = MutableLiveData<Unit>()
    val succeeded: LiveData<Unit> get() = _succeeded

    // FIXME
    fun saveAuthUser() {
        viewModelScope.launch {
            _loading.value = true
            delay(1_500L)
            val user = AuthUser()
            saveAuthUser(user)
            _loading.value = false
            _succeeded.value = Unit
        }
    }
}
