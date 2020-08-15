package com.rmakiyama.spatz.auth

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmakiyama.spatz.core.result.Result
import com.rmakiyama.spatz.core.result.updateOnSuccess
import com.rmakiyama.spatz.domain.model.twitter.RequestToken
import com.rmakiyama.spatz.usecase.auth.SaveTwitterSessionCommand
import com.rmakiyama.spatz.usecase.auth.SaveTwitterSessionUseCase
import com.rmakiyama.spatz.usecase.auth.TwitterLoginUseCase
import kotlinx.coroutines.launch
import timber.log.Timber

class LoginViewModel @ViewModelInject constructor(
    private val login: TwitterLoginUseCase,
    private val saveTwitterSession: SaveTwitterSessionUseCase
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> get() = _loading

    private val _requestToken = MutableLiveData<RequestToken>()
    val requestToken: LiveData<RequestToken> get() = _requestToken

    // TODO
    private val _succeeded = MutableLiveData<Unit>()
    val succeeded: LiveData<Unit> get() = _succeeded

    fun login() {
        viewModelScope.launch {
            when (val result = login(Unit)) {
                is Result.Success -> result.updateOnSuccess(_requestToken)
                is Result.Error -> Timber.e(result.exception)
            }
        }
    }

    fun saveAuthUser(
        token: String?,
        secret: String?,
        screenName: String?
    ) {
        viewModelScope.launch {
            _loading.value = true
            saveTwitterSession(
                SaveTwitterSessionCommand(
                    token = token,
                    secret = secret,
                    screenName = screenName
                )
            )
            _loading.value = false
            _succeeded.value = Unit
        }
    }
}
