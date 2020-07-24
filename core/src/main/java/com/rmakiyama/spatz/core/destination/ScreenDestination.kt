package com.rmakiyama.spatz.core.destination

import android.net.Uri
import androidx.core.net.toUri
import com.rmakiyama.spatz.domain.model.user.UserId

sealed class ScreenDestination(path: String) {
    val deeplink: Uri = "$SCHEME://$path".toUri()

    object Tweet : ScreenDestination("tweet")
    data class UserDetail(
        val userId: UserId
    ) : ScreenDestination("userdetail/${userId.value}")

    companion object {
        private const val SCHEME: String = "spatz"

        const val PARAM_USER_ID = "userId"
    }
}
