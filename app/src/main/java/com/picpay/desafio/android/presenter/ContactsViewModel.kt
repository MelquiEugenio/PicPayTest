package com.picpay.desafio.android.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.data.network.dto.UserDto
import com.picpay.desafio.android.domain.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val usersRepository: UsersRepository
): ViewModel() {

    val users: MutableLiveData<List<UserDto>?> by lazy {
        MutableLiveData<List<UserDto>?>(emptyList())
    }

    /**
     * Method to get a list of users from the PicPay API.
     * returns: List<User> or null if an error occurred.
     */
    private fun getUsersFromApi() {
        viewModelScope.launch {
            users.postValue(usersRepository.getUsersFromApi())
        }
    }

    fun saveUsers(users: List<UserDto>) {
        usersRepository.saveUsers(users)
    }

    fun getSavedUsers(): List<UserDto>? {
        return usersRepository.getSavedUsers()
    }

    init {
        getUsersFromApi()
    }
}