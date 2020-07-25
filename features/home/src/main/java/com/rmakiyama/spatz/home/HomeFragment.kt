package com.rmakiyama.spatz.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialSharedAxis
import com.rmakiyama.spatz.core.destination.ScreenDestination
import com.rmakiyama.spatz.domain.model.tweet.Tweet
import com.rmakiyama.spatz.home.databinding.FragmentHomeBinding
import com.rmakiyama.spatz.home.item.TweetItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.applySystemWindowInsetsToMargin
import dev.chrisbanes.insetter.applySystemWindowInsetsToPadding
import kotlinx.coroutines.flow.collect
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private val timelineAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        binding.setupInsets()
        setupTimelineView(binding.timeline)
        binding.tweetFab.setOnClickListener(::onClickTweetFab)
        viewModel.tweet.observe(viewLifecycleOwner, ::updateTweetList)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.authUser.collect { user -> if (user == null) navigateLogin() }
        }
    }

    private fun FragmentHomeBinding.setupInsets() {
        toolbar.applySystemWindowInsetsToMargin(top = true)
        timeline.applySystemWindowInsetsToPadding(bottom = true)
        tweetFab.applySystemWindowInsetsToMargin(bottom = true)
    }

    private fun setupTimelineView(
        timeline: RecyclerView
    ) {
        timeline.adapter = timelineAdapter
    }

    private fun onClickTweetFab(view: View) {
        exitTransition = Hold()
        reenterTransition = Hold()
        val fabToTweetTransitionName = getString(R.string.transition_name_fab_to_tweet)
        val extra = FragmentNavigatorExtras(view to fabToTweetTransitionName)
        findNavController().navigate(ScreenDestination.Tweet.deeplink, null, extra)
    }

    private fun navigateLogin() {
        Timber.i("info: navigate to login")
        findNavController().navigate(ScreenDestination.AuthLogin.deeplink)
    }

    private fun updateTweetList(tweets: List<Tweet>) {
        timelineAdapter.update(tweets.map { tweet -> TweetItem(tweet, tweetClickListener) })
    }

    private val tweetClickListener = object : TweetItem.TweetOnClickListener {
        override fun onClickUser(tweet: Tweet) {
            exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
            reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
            findNavController().navigate(ScreenDestination.UserDetail(tweet.user.id).deeplink)
        }
    }
}
