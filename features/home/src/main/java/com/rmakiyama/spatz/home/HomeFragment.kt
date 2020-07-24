package com.rmakiyama.spatz.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.Hold
import com.rmakiyama.spatz.core.destination.ScreenDestination
import com.rmakiyama.spatz.domain.model.tweet.Tweet
import com.rmakiyama.spatz.home.databinding.FragmentHomeBinding
import com.rmakiyama.spatz.home.item.TweetItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private val timelineAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        setupTimelineView(binding.timeline)
        binding.tweetFab.setOnClickListener(::onClickTweetFab)
        viewModel.tweet.observe(viewLifecycleOwner, ::updateTweetList)
    }

    private fun setupTimelineView(
        timeline: RecyclerView
    ) {
        timeline.adapter = timelineAdapter
    }

    private fun onClickTweetFab(view: View) {
        exitTransition = Hold()
        val fabToTweetTransitionName = getString(R.string.transition_name_fab_to_tweet)
        val extra = FragmentNavigatorExtras(view to fabToTweetTransitionName)
        findNavController().navigate(ScreenDestination.Tweet.deeplink, null, extra)
    }

    private fun updateTweetList(tweets: List<Tweet>) {
        timelineAdapter.update(tweets.map { tweet -> TweetItem(tweet, tweetClickListener) })
    }

    private val tweetClickListener = object : TweetItem.TweetOnClickListener {
        override fun onClickUser(tweet: Tweet) {
            findNavController().navigate(ScreenDestination.UserDetail.deeplink)
        }
    }
}
