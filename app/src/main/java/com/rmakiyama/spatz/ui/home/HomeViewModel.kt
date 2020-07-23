package com.rmakiyama.spatz.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmakiyama.spatz.domain.model.Tweet
import com.rmakiyama.spatz.domain.usecase.GetTweetsUseCase
import com.rmakiyama.spatz.domain.usecase.GetTweetsUseCaseImpl
import kotlinx.coroutines.launch

internal class HomeViewModel : ViewModel() {

    private val getTweets: GetTweetsUseCase = GetTweetsUseCaseImpl()

    private val _tweet = MutableLiveData<List<Tweet>>()
    val tweet: LiveData<List<Tweet>> get() = _tweet

    init {
        viewModelScope.launch {
            _tweet.value = getTweets.run(Unit)
        }
    }
}
