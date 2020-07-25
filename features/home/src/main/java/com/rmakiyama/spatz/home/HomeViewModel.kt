package com.rmakiyama.spatz.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmakiyama.spatz.domain.model.tweet.Tweet
import com.rmakiyama.spatz.domain.usecase.auth.LoadAuthUserUseCase
import com.rmakiyama.spatz.domain.usecase.tweet.GetTweetsUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

internal class HomeViewModel @ViewModelInject constructor(
    private val loadAuthUser: LoadAuthUserUseCase,
    private val getTweets: GetTweetsUseCase
) : ViewModel() {

    val authUser get() = loadAuthUser.load(Unit)
    private val _tweet = MutableLiveData<List<Tweet>>()
    val tweet: LiveData<List<Tweet>> get() = _tweet
    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> get() = _loading

    init {
        viewModelScope.launch {
            if (authUser.first() != null) getTweets()
        }
    }

    fun getTweets() {
        viewModelScope.launch {
            // TODO: failed pattern
            _loading.value = true
            _tweet.value = getTweets.run(Unit)
            _loading.value = false
        }
    }
}
