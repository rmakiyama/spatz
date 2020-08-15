package com.rmakiyama.spatz.auth

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import timber.log.Timber


internal object TwitterUtil {

    private const val EXTRA_CONSUMER_KEY = "ck"
    private const val EXTRA_CONSUMER_SECRET = "cs"
    const val EXTRA_OAUTH_TOKEN = "tk"
    const val EXTRA_OAUTH_TOKEN_SECRET = "ts"
    const val EXTRA_SCREEN_NAME = "screen_name"
    private const val TWITTER_PACKAGE_NAME = "com.twitter.android"
    private const val SSO_CLASS_NAME = "$TWITTER_PACKAGE_NAME.SingleSignOnActivity"

    fun isSSOAvailable(context: Context): Boolean {
        val pm: PackageManager = context.packageManager
        return try {
            val info = pm.getPackageInfo(TWITTER_PACKAGE_NAME, 0)
            true
        } catch (exception: PackageManager.NameNotFoundException) {
            Timber.w("Twitter app is not found.")
            false
        }
    }

    fun getTwitterSSOIntent(context: Context): Intent {
        if (!isSSOAvailable(context)) throw IllegalStateException("SSO is not available.")
        val component = ComponentName(
            TWITTER_PACKAGE_NAME,
            SSO_CLASS_NAME
        )
        return Intent().setComponent(component)
            .putExtra(EXTRA_CONSUMER_KEY, BuildConfig.CONSUMER_KEY)
            .putExtra(EXTRA_CONSUMER_SECRET, BuildConfig.CONSUMER_SECRET)
    }
}
