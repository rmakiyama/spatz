package com.rmakiyama.spatz.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmakiyama.spatz.domain.model.tweet.Tweet
import com.rmakiyama.spatz.domain.usecase.GetTweetsUseCase
import com.rmakiyama.spatz.domain.usecase.LoadAuthUserUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

internal class HomeViewModel @ViewModelInject constructor(
    private val loadAuthUser: LoadAuthUserUseCase,
    private val getTweets: GetTweetsUseCase
) : ViewModel() {

    val authUser get() = loadAuthUser.load(Unit)
    private val _tweet = MutableLiveData<List<Tweet>>()
    val tweet: LiveData<List<Tweet>> get() = _tweet

    init {
        viewModelScope.launch {
            if (authUser.first() != null) getTweets()
        }
    }

    private fun getTweets() {
        viewModelScope.launch {
            _tweet.value = getTweets.run(Unit)
        }
    }
}
