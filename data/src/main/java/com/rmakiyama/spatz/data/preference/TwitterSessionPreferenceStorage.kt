package com.rmakiyama.spatz.data.preference

import android.content.Context
import android.os.Build
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.security.crypto.MasterKeys
import com.rmakiyama.spatz.data.TwitterSessionSource
import com.rmakiyama.spatz.domain.model.twitter.TwitterAuthToken
import com.rmakiyama.spatz.domain.model.twitter.TwitterSession
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class TwitterSessionPreferencesStorage @Inject constructor(
    @ApplicationContext context: Context
) : TwitterSessionSource {

    private val prefs by lazy {
        val masterKey =
            MasterKey.Builder(context).apply {
                // fixme
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setKeyGenParameterSpec(MasterKeys.AES256_GCM_SPEC)
                }
            }.build()
        EncryptedSharedPreferences.create(
            context,
            PREFS_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override fun save(session: TwitterSession) {
        prefs.edit {
            putString(PREF_OAUTH_TOKEN, session.authToken.token)
            putString(PREF_OAUTH_TOKEN_SECRET, session.authToken.secret)
            putString(PREF_SCREEN_NAME, session.screenName)
        }
    }

    override fun get(): TwitterSession? {
        val token = prefs.getString(PREF_OAUTH_TOKEN, null)
        val secret = prefs.getString(PREF_OAUTH_TOKEN_SECRET, null)
        val screenName = prefs.getString(PREF_SCREEN_NAME, null)
        if (token == null || secret == null || screenName == null) return null
        return TwitterSession(
            authToken = TwitterAuthToken(
                token = token,
                secret = secret
            ),
            screenName = screenName
        )
    }

    override fun clear() {
        prefs.edit {
            putString(PREF_OAUTH_TOKEN, null)
            putString(PREF_OAUTH_TOKEN_SECRET, null)
            putString(PREF_SCREEN_NAME, null)
        }
    }

    companion object {
        const val PREFS_NAME = "twitter_session"
        const val PREF_OAUTH_TOKEN = "pref_oauth_token"
        const val PREF_OAUTH_TOKEN_SECRET = "pref_oauth_token_secret"
        const val PREF_SCREEN_NAME = "pref_screen_name"
    }
}
