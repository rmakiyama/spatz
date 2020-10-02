package com.rmakiyama.spatz.auth

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.rmakiyama.spatz.core.destination.ScreenDestination
import com.rmakiyama.spatz.core.result.Result
import com.rmakiyama.spatz.domain.model.twitter.RequestToken
import com.rmakiyama.spatz.test.MainCoroutineRule
import com.rmakiyama.spatz.test.MockkRule
import com.rmakiyama.spatz.test.runBlockingTest
import com.rmakiyama.spatz.usecase.auth.GetAccessTokenUseCase
import com.rmakiyama.spatz.usecase.auth.GetRequestTokenUseCase
import com.rmakiyama.spatz.usecase.auth.SaveTwitterSessionUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    @get:Rule val rule: TestRule = InstantTaskExecutorRule()
    @get:Rule val coroutineRule: MainCoroutineRule = MainCoroutineRule()
    @get:Rule val mockkRule: MockkRule = MockkRule(this)

    @MockK private lateinit var getAccessToken: GetAccessTokenUseCase
    @MockK private lateinit var getRequestToken: GetRequestTokenUseCase

    @Test
    fun initTest_non_auth_param() = coroutineRule.runBlockingTest {
        coEvery { getAccessToken(any()) } returns Result.Success(Unit)

        val savedStateHandle = SavedStateHandle()

        val viewModel = createTarget(
            getAccessToken = getAccessToken,
            savedStateHandle = savedStateHandle
        )

        Assert.assertEquals(
            savedStateHandle.get<String>(ScreenDestination.PARAM_OAUTH_TOKEN),
            null
        )
        Assert.assertEquals(
            savedStateHandle.get<String>(ScreenDestination.PARAM_OAUTH_VERIFIER),
            null
        )
        coVerify(exactly = 0) { getAccessToken(any()) }
    }

    @Test
    fun initTest_has_auth_param() = coroutineRule.runBlockingTest {
        coEvery { getAccessToken(any()) } returns Result.Success(Unit)

        val oauthToken = "mock_token"
        val oauthVerifier = "mock_verifier"
        val savedStateHandle = SavedStateHandle().apply {
            set(ScreenDestination.PARAM_OAUTH_TOKEN, oauthToken)
            set(ScreenDestination.PARAM_OAUTH_VERIFIER, oauthVerifier)
        }

        val viewModel = createTarget(
            getAccessToken = getAccessToken,
            savedStateHandle = savedStateHandle
        )

        Assert.assertEquals(
            savedStateHandle.get<String>(ScreenDestination.PARAM_OAUTH_TOKEN),
            oauthToken
        )
        Assert.assertEquals(
            savedStateHandle.get<String>(ScreenDestination.PARAM_OAUTH_VERIFIER),
            oauthVerifier
        )
        coVerify(exactly = 1) { getAccessToken(any()) }
    }

    @Test
    fun getRequestToken() = coroutineRule.runBlockingTest {
        val token = "mock_token"
        val secret = "mock_secret"
        val mockRequestToken = RequestToken(token, secret)
        coEvery { getRequestToken(any()) } returns Result.Success(mockRequestToken)
        val observer = mockk<Observer<RequestToken>>(relaxed = true)

        val viewModel = createTarget(
            getRequestToken = getRequestToken,
        )
        viewModel.requestToken.observeForever(observer)

        viewModel.getRequestToken()

        verify { observer.onChanged(mockRequestToken) }
    }

    private fun createTarget(
        getRequestToken: GetRequestTokenUseCase = mockk(),
        getAccessToken: GetAccessTokenUseCase = mockk(),
        saveTwitterSession: SaveTwitterSessionUseCase = mockk(),
        savedStateHandle: SavedStateHandle = SavedStateHandle(),
    ): LoginViewModel {
        return LoginViewModel(
            getRequestToken,
            getAccessToken,
            saveTwitterSession,
            savedStateHandle,
        )
    }
}
