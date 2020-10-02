package com.rmakiyama.spatz.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rmakiyama.spatz.core.result.Result
import com.rmakiyama.spatz.domain.model.twitter.TwitterAuthToken
import com.rmakiyama.spatz.domain.model.twitter.TwitterSession
import com.rmakiyama.spatz.test.MainCoroutineRule
import com.rmakiyama.spatz.test.MockkRule
import com.rmakiyama.spatz.test.data.Fakes
import com.rmakiyama.spatz.test.runBlockingTest
import com.rmakiyama.spatz.usecase.auth.LoadTwitterSessionUseCase
import com.rmakiyama.spatz.usecase.tweet.GetTweetsUseCase
import io.mockk.Called
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule val rule: TestRule = InstantTaskExecutorRule()
    @get:Rule val coroutineRule: MainCoroutineRule = MainCoroutineRule()
    @get:Rule val mockkRule: MockkRule = MockkRule(this)

    @MockK private lateinit var loadTwitterSession: LoadTwitterSessionUseCase
    @MockK private lateinit var getTweets: GetTweetsUseCase

    @Test
    fun initTest_AuthUser() = coroutineRule.runBlockingTest {
        val fake = TwitterSession(TwitterAuthToken("fake", "fake"), "fake")
        every { loadTwitterSession(Unit) } returns flowOf(Result.Success(fake))
        coEvery { getTweets(Unit) } returns Result.Success(emptyList())

        val viewModel = HomeViewModel(
            loadTwitterSession = loadTwitterSession,
            getTweets = getTweets
        )

        coVerify(exactly = 1) { getTweets(Unit) }

        Assert.assertEquals(fake, (viewModel.twitterSession.first() as Result.Success).data)
    }

    @Test
    fun initTest_NonAuth() = coroutineRule.runBlockingTest {
        every { loadTwitterSession(Unit) } returns flowOf(Result.Success(null))
        coEvery { getTweets(Unit) } returns Result.Success(emptyList())

        val viewModel = HomeViewModel(
            loadTwitterSession = loadTwitterSession,
            getTweets = getTweets
        )

        coVerify {
            getTweets(Unit) wasNot Called
        }

        Assert.assertEquals(null, viewModel.tweet.value)
    }

    @Test
    fun loadTweetsTest_LiveDataUpdate() = coroutineRule.runBlockingTest {
        val fake = TwitterSession(TwitterAuthToken("fake", "fake"), "fake")
        every { loadTwitterSession(Unit) } returns flowOf(Result.Success(fake))
        coEvery { getTweets(Unit) } returns Result.Success(Fakes.tweets)

        val viewModel = HomeViewModel(
            loadTwitterSession = loadTwitterSession,
            getTweets = getTweets
        )

        viewModel.getTweets()

        coVerify { getTweets(Unit) }

        Assert.assertEquals(Fakes.tweets, viewModel.tweet.value)
    }
}
