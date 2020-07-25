package com.rmakiyama.spatz.core.extension

import androidx.fragment.app.Fragment
import com.rmakiyama.spatz.core.R

val Fragment.KEY_LOGIN_SUCCESSFUL: String get() = "LOGIN_SUCCESSFUL"

val Fragment.motionDurationLargeExpanding: Long
    get() = resources.getInteger(R.integer.spatz_motion_duration_large_expanding).toLong()
val Fragment.motionDurationLargeCollapsing: Long
    get() = resources.getInteger(R.integer.spatz_motion_duration_large_collapsing).toLong()
