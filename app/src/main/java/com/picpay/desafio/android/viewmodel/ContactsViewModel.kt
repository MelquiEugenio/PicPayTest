package com.picpay.desafio.android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.model.network.api.PicPayService
import com.picpay.desafio.android.model.network.dto.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val service: PicPayService
): ViewModel() {

    val users: MutableLiveData<List<User>?> by lazy {
        MutableLiveData<List<User>?>(emptyList())
    }

    private fun getUsers() {
        viewModelScope.launch {
            try {
                val temp = service.getUsers()
                users.postValue(temp)

            } catch (e: Exception) {
                users.postValue(null)
            }
        }
    }

    init {
        getUsers()
    }
}