package com.rmakiyama.spatz.tweet

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import com.rmakiyama.spatz.core.extension.motionDurationLargeCollapsing
import com.rmakiyama.spatz.core.extension.motionDurationLargeExpanding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TweetFragment : Fragment(R.layout.fragment_tweet) {

    private val viewModel: TweetViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = motionDurationLargeExpanding
            setPathMotion(MaterialArcMotion())
        }
        sharedElementReturnTransition = MaterialContainerTransform().apply {
            duration = motionDurationLargeCollapsing
            setPathMotion(MaterialArcMotion())
        }
    }
}
