package com.rmakiyama.spatz.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rmakiyama.spatz.domain.model.auth.AuthUser
import com.rmakiyama.spatz.domain.result.Result
import com.rmakiyama.spatz.domain.usecase.auth.LoadAuthUserUseCase
import com.rmakiyama.spatz.domain.usecase.tweet.GetTweetsUseCase
import com.rmakiyama.spatz.test.MainCoroutineRule
import com.rmakiyama.spatz.test.MockkRule
import com.rmakiyama.spatz.test.data.Fakes
import io.mockk.Called
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule val rule: TestRule = InstantTaskExecutorRule()
    @get:Rule val coroutineRule: MainCoroutineRule = MainCoroutineRule()
    @get:Rule val mockkRule: MockkRule = MockkRule(this)

    @MockK private lateinit var loadAuthUser: LoadAuthUserUseCase
    @MockK private lateinit var getTweets: GetTweetsUseCase

    @Test
    fun initTest_AuthUser() = runBlockingTest {
        val fakeUser = AuthUser()
        every { loadAuthUser(Unit) } returns flowOf(Result.Success(fakeUser))
        coEvery { getTweets(Unit) } returns Result.Success(emptyList())

        val viewModel = HomeViewModel(loadAuthUser, getTweets)
        every { viewModel.getTweets() } returns Unit

        coVerify(exactly = 1) { getTweets(Unit) }

        Assert.assertEquals(fakeUser, (viewModel.authUser.first() as Result.Success).data)
    }

    @Test
    fun initTest_NonAuth() = runBlockingTest {
        every { loadAuthUser(Unit) } returns flowOf(Result.Success(null))
        coEvery { getTweets(Unit) } returns Result.Success(emptyList())

        val viewModel = HomeViewModel(loadAuthUser, getTweets)
        every { viewModel.getTweets() } returns Unit

        coVerify {
            getTweets(Unit) wasNot Called
        }

        Assert.assertEquals(null, viewModel.tweet.value)
    }

    @Test
    fun loadTweetsTest_LiveDataUpdate() {
        every { loadAuthUser(Unit) } returns flowOf(Result.Success(AuthUser()))
        coEvery { getTweets(Unit) } returns Result.Success(Fakes.tweets)

        val viewModel = HomeViewModel(loadAuthUser, getTweets)

        viewModel.getTweets()

        coVerify { getTweets(Unit) }

        Assert.assertEquals(Fakes.tweets, viewModel.tweet.value)
    }
}
