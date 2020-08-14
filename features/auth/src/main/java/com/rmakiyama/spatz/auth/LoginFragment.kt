package com.rmakiyama.spatz.auth

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialFadeThrough
import com.rmakiyama.spatz.auth.databinding.FragmentLoginBinding
import com.rmakiyama.spatz.core.extension.KEY_LOGIN_SUCCESSFUL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()

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
        binding.loginButton.setOnClickListener { viewModel.login() }

        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) binding.progressBar.show() else binding.progressBar.hide()
        }
        viewModel.succeeded.observe(viewLifecycleOwner) {
            savedStateHandle.set(KEY_LOGIN_SUCCESSFUL, true)
            navController.popBackStack()
        }
    }

    private val closeLoginOnBackPressed = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            activity?.finish()
        }
    }
}
