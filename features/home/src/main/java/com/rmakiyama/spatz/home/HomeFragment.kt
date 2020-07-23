package com.rmakiyama.spatz.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.rmakiyama.spatz.domain.model.tweet.Tweet
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.tweet.observe(viewLifecycleOwner, ::updateTweetList)
    }

    private fun updateTweetList(tweets: List<Tweet>) {
        Timber.d("updateTweetList: ${tweets.size}")
    }
}
