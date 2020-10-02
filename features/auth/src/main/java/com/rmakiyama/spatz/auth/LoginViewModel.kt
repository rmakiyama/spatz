package com.rmakiyama.spatz.auth

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmakiyama.spatz.core.destination.ScreenDestination.Companion.PARAM_OAUTH_TOKEN
import com.rmakiyama.spatz.core.destination.ScreenDestination.Companion.PARAM_OAUTH_VERIFIER
import com.rmakiyama.spatz.core.result.Result
import com.rmakiyama.spatz.core.result.updateOnSuccess
import com.rmakiyama.spatz.domain.model.twitter.RequestToken
import com.rmakiyama.spatz.usecase.auth.GetAccessTokenCommand
import com.rmakiyama.spatz.usecase.auth.GetAccessTokenUseCase
import com.rmakiyama.spatz.usecase.auth.GetRequestTokenUseCase
import com.rmakiyama.spatz.usecase.auth.SaveTwitterSessionCommand
import com.rmakiyama.spatz.usecase.auth.SaveTwitterSessionUseCase
import kotlinx.coroutines.launch
import timber.log.Timber

class LoginViewModel @ViewModelInject constructor(
    private val getRequestToken: GetRequestTokenUseCase,
    private val getAccessToken: GetAccessTokenUseCase,
    private val saveTwitterSession: SaveTwitterSessionUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> get() = _loading

    private val _requestToken = MutableLiveData<RequestToken>()
    val requestToken: LiveData<RequestToken> get() = _requestToken

    private val _succeeded = MutableLiveData<Unit>()
    val succeeded: LiveData<Unit> get() = _succeeded

    init {
        getAccessToken()
    }

    fun getRequestToken() {
        viewModelScope.launch {
            when (val result = getRequestToken(Unit)) {
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

    private fun getAccessToken() {
        val oauthToken = savedStateHandle.get<String>(PARAM_OAUTH_TOKEN) ?: return
        val oauthVerifier = savedStateHandle.get<String>(PARAM_OAUTH_VERIFIER) ?: return
        viewModelScope.launch {
            _loading.value = true
            when (val result = getAccessToken(GetAccessTokenCommand(oauthToken, oauthVerifier))) {
                is Result.Success -> result.updateOnSuccess(_succeeded)
                is Result.Error -> Timber.e(result.exception)
            }
            _loading.value = false
        }
    }
}
