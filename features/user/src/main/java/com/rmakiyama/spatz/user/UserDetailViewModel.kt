package com.rmakiyama.spatz.user

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmakiyama.spatz.core.destination.ScreenDestination.Companion.PARAM_USER_ID
import com.rmakiyama.spatz.domain.model.user.UserId
import com.rmakiyama.spatz.domain.repository.UserRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class UserDetailViewModel @ViewModelInject constructor(
    private val userRepository: UserRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {
        viewModelScope.launch {
            val id = savedStateHandle.get<String>(PARAM_USER_ID)?.let { UserId(it) }
                ?: throw IllegalArgumentException("id is not found.")
            val user = userRepository.find(id)
            Timber.i("info: ${user?.name?.value}")
        }
    }
}
