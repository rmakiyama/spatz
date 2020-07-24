package com.rmakiyama.spatz.user

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import coil.api.load
import com.google.android.material.transition.MaterialSharedAxis
import com.google.android.material.transition.MaterialSharedAxis.Z
import com.rmakiyama.spatz.domain.model.user.User
import com.rmakiyama.spatz.user.databinding.FragmentUserDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : Fragment(R.layout.fragment_user_detail) {

    private val viewModel: UserDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(Z, true)
        returnTransition = MaterialSharedAxis(Z, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentUserDetailBinding.bind(view)
        binding.setupInsets()
        binding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        viewModel.user.observe(viewLifecycleOwner) { user -> binding.applyUserData(user) }
    }

    private fun FragmentUserDetailBinding.setupInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(appbar) { view, insets ->
            toolbar.updatePadding(top = insets.systemWindowInsetTop)
            ViewCompat.onApplyWindowInsets(view, insets)
        }
    }

    private fun FragmentUserDetailBinding.applyUserData(user: User) {
        userBannerImage.load(user.profileBannerUrl.value)
        userImage.load(user.profilePhotoUrl.value)
        userName.text = user.name.value
        userDescription.text = user.description?.value
    }
}
