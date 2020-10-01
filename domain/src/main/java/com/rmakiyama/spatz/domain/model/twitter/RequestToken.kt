package com.rmakiyama.spatz.domain.model.twitter

data class RequestToken(
    val token: String,
    val tokenSecret: String
) {

    fun getAuthorizeURL(): String {
        return "$OAUTH_URL?oauth_token=$token"
    }

    companion object {
        private const val OAUTH_URL = "https://api.twitter.com/oauth/authenticate"
    }
}
