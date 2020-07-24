package com.rmakiyama.spatz.tweet

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TweetFragment : Fragment(R.layout.fragment_tweet) {

    private val viewModel: TweetViewModel by viewModels()
}
