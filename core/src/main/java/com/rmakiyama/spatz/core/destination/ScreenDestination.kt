package com.rmakiyama.spatz.core.destination

import android.net.Uri
import androidx.core.net.toUri

sealed class ScreenDestination(path: String) {
    val deeplink: Uri = "$SCHEME://$path".toUri()

    object Tweet : ScreenDestination("tweet")

    companion object {
        private const val SCHEME: String = "spatz"
    }
}
