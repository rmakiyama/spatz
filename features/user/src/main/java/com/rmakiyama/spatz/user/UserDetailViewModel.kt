package com.rmakiyama.spatz.user

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmakiyama.spatz.core.destination.ScreenDestination.Companion.PARAM_USER_ID
import com.rmakiyama.spatz.domain.model.user.User
import com.rmakiyama.spatz.domain.model.user.UserId
import com.rmakiyama.spatz.domain.repository.UserRepository
import kotlinx.coroutines.launch

class UserDetailViewModel @ViewModelInject constructor(
    private val userRepository: UserRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    init {
        loadUser()
    }

    private fun loadUser() {
        viewModelScope.launch {
            val id = savedStateHandle.get<String>(PARAM_USER_ID)?.let { UserId(it) }
                ?: throw IllegalArgumentException("id is not found.")
            val user = userRepository.find(id) ?: return@launch
            _user.value = user
        }
    }
}
