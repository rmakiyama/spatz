package com.rmakiyama.spatz.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmakiyama.spatz.domain.model.Tweet
import com.rmakiyama.spatz.domain.usecase.GetTweetsUseCase
import kotlinx.coroutines.launch

internal class HomeViewModel @ViewModelInject constructor(
    private val getTweets: GetTweetsUseCase
) : ViewModel() {

    private val _tweet = MutableLiveData<List<Tweet>>()
    val tweet: LiveData<List<Tweet>> get() = _tweet

    init {
        viewModelScope.launch {
            _tweet.value = getTweets.run(Unit)
        }
    }
}
