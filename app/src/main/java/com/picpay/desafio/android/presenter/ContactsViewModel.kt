package com.picpay.desafio.android.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.data.network.api.PicPayService
import com.picpay.desafio.android.data.network.dto.User
import com.picpay.desafio.android.domain.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val usersRepository: UsersRepository
): ViewModel() {

    val users: MutableLiveData<List<User>?> by lazy {
        MutableLiveData<List<User>?>(emptyList())
    }

    private fun getUsers() {
        viewModelScope.launch {
            users.postValue(usersRepository.getUsers())
        }
    }

    init {
        getUsers()
    }
}