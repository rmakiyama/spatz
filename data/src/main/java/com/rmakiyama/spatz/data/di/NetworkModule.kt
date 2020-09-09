package com.rmakiyama.spatz.data.di

import com.rmakiyama.spatz.core.BuildConfig
import com.rmakiyama.spatz.data.TwitterSessionSource
import com.rmakiyama.spatz.data.retrofit.TwitterApiClient
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer
import se.akerfeldt.okhttp.signpost.SigningInterceptor
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @Named("oauth")
    fun provideOAuthHeaderInterceptor(
        twitterSessionSource: TwitterSessionSource
    ): Interceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val consumer = OkHttpOAuthConsumer(
                BuildConfig.CONSUMER_KEY,
                BuildConfig.CONSUMER_SECRET
            )
            twitterSessionSource.get()?.also { session ->
                consumer.setTokenWithSecret(
                    session.authToken.token,
                    session.authToken.secret
                )
            }
            return SigningInterceptor(consumer).intercept(chain)
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @Named("oauth") oauthInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(oauthInterceptor)
            // fixme
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.twitter.com/")
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
            .build()
    }

    @Provides
    @Singleton
    fun provideTwitterApiClient(retrofit: Retrofit): TwitterApiClient {
        return retrofit.create(TwitterApiClient::class.java)
    }
}
