package com.rmakiyama.spatz.tweet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.transition.Transition
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import com.rmakiyama.spatz.core.extension.motionDurationLargeCollapsing
import com.rmakiyama.spatz.core.extension.motionDurationLargeExpanding
import com.rmakiyama.spatz.core.extension.showKeyboard
import com.rmakiyama.spatz.core.extension.themeColor
import com.rmakiyama.spatz.core.util.TransitionEndListener
import com.rmakiyama.spatz.tweet.databinding.FragmentTweetBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TweetFragment : Fragment(R.layout.fragment_tweet) {

    private val viewModel: TweetViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupTransition()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentTweetBinding.bind(view)

        (sharedElementEnterTransition as Transition).addListener(
            // delay show keyboard until transition end
            TransitionEndListener {
                binding.inputTweet.apply {
                    requestFocus()
                    requestFocusFromTouch()
                    findFocus().showKeyboard()
                }
            })
    }

    private fun setupTransition() {
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = motionDurationLargeExpanding
            setPathMotion(MaterialArcMotion())
            containerColor = requireContext().themeColor(R.attr.colorSurface)
            startContainerColor = requireContext().themeColor(R.attr.colorSecondary)
            endContainerColor = requireContext().themeColor(R.attr.colorSurface)
        }
        sharedElementReturnTransition = MaterialContainerTransform().apply {
            duration = motionDurationLargeCollapsing
            setPathMotion(MaterialArcMotion())
            containerColor = requireContext().themeColor(R.attr.colorSurface)
            startContainerColor = requireContext().themeColor(R.attr.colorSurface)
            endContainerColor = requireContext().themeColor(R.attr.colorSecondary)
        }
    }
}
