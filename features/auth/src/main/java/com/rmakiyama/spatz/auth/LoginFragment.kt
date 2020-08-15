package com.rmakiyama.spatz.auth

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialFadeThrough
import com.rmakiyama.spatz.auth.TwitterUtil.EXTRA_OAUTH_TOKEN
import com.rmakiyama.spatz.auth.TwitterUtil.EXTRA_OAUTH_TOKEN_SECRET
import com.rmakiyama.spatz.auth.TwitterUtil.EXTRA_SCREEN_NAME
import com.rmakiyama.spatz.auth.databinding.FragmentLoginBinding
import com.rmakiyama.spatz.core.extension.KEY_LOGIN_SUCCESSFUL
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()

    private val startForResultSSO =
        registerForActivityResult(StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data ?: return@registerForActivityResult
                val token = intent.getStringExtra(EXTRA_OAUTH_TOKEN)
                val tokenSecret = intent.getStringExtra(EXTRA_OAUTH_TOKEN_SECRET)
                val screenName = intent.getStringExtra(EXTRA_SCREEN_NAME)
                viewModel.saveAuthUser(token, tokenSecret, screenName)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough()
        returnTransition = MaterialFadeThrough()
        requireActivity().onBackPressedDispatcher.addCallback(this, closeLoginOnBackPressed)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        val savedStateHandle = requireNotNull(navController.previousBackStackEntry).savedStateHandle
        val binding = FragmentLoginBinding.bind(view)
        binding.loginButton.setOnClickListener { loginTwitter() }

        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) binding.progressBar.show() else binding.progressBar.hide()
        }
        viewModel.requestToken.observe(viewLifecycleOwner) { token ->
            Timber.i("info: request token. $token")
        }
        viewModel.succeeded.observe(viewLifecycleOwner) {
            savedStateHandle.set(KEY_LOGIN_SUCCESSFUL, true)
            navController.popBackStack()
        }
    }

    private fun loginTwitter() {
        if (TwitterUtil.isSSOAvailable(requireContext())) {
            startForResultSSO.launch(TwitterUtil.getTwitterSSOIntent(requireContext()))
        } else {
            // TODO
            // viewModel.login()
        }
    }

    private val closeLoginOnBackPressed = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            activity?.finish()
        }
    }
}
