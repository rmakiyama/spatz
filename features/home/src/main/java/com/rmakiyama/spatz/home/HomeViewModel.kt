package com.rmakiyama.spatz.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmakiyama.spatz.core.result.Result
import com.rmakiyama.spatz.core.result.updateOnSuccess
import com.rmakiyama.spatz.domain.model.tweet.Tweet
import com.rmakiyama.spatz.usecase.auth.LoadTwitterSessionUseCase
import com.rmakiyama.spatz.usecase.tweet.GetTweetsUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber

internal class HomeViewModel @ViewModelInject constructor(
    loadTwitterSession: LoadTwitterSessionUseCase,
    private val getTweets: GetTweetsUseCase
) : ViewModel() {

    val twitterSession = loadTwitterSession(Unit)
    private val _tweet = MutableLiveData<List<Tweet>>()
    val tweet: LiveData<List<Tweet>> get() = _tweet
    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> get() = _loading

    init {
        firstLoad()
    }

    fun getTweets() {
        viewModelScope.launch {
            _loading.value = true
            when (val result = getTweets(Unit)) {
                is Result.Success -> result.updateOnSuccess(_tweet)
                is Result.Error -> Timber.e(result.exception)
            }
            _loading.value = false
        }
    }

    private fun firstLoad() {
        viewModelScope.launch {
            when (val result = twitterSession.first()) {
                is Result.Success -> if (result.data != null) getTweets()
                is Result.Error -> Timber.e(result.exception)
            }
        }
    }
}
