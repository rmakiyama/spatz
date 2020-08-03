package com.rmakiyama.spatz.core.util

import androidx.transition.Transition

class TransitionEndListener(private val doOnEnd: () -> Unit) : Transition.TransitionListener {

    override fun onTransitionEnd(transition: Transition) {
        doOnEnd()
    }

    override fun onTransitionResume(transition: Transition) {}

    override fun onTransitionPause(transition: Transition) {}

    override fun onTransitionCancel(transition: Transition) {}

    override fun onTransitionStart(transition: Transition) {}
}
