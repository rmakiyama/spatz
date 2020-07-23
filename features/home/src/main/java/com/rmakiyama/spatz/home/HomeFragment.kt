package com.rmakiyama.spatz.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.rmakiyama.spatz.domain.model.tweet.Tweet
import com.rmakiyama.spatz.home.databinding.FragmentHomeBinding
import com.rmakiyama.spatz.home.item.TweetItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private val timelineAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        setupTimelineView(binding.timeline)
        viewModel.tweet.observe(viewLifecycleOwner, ::updateTweetList)
    }

    private fun setupTimelineView(
        timeline: RecyclerView
    ) {
        timeline.adapter = timelineAdapter
    }

    private fun updateTweetList(tweets: List<Tweet>) {
        timelineAdapter.update(tweets.map(::TweetItem))
    }
}
