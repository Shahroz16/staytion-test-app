package com.shahroz.staytiontestapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.shahroz.staytiontestapp.model.ActionResponse
import com.shahroz.staytiontestapp.model.Response
import com.shahroz.staytiontestapp.Request
import com.shahroz.staytiontestapp.repository.Repository

class MainViewModel @ViewModelInject constructor(private val repository: Repository) : ViewModel() {
    init {
        webSocketCreate()
    }

    val webSocketIncomingData: LiveData<Response?> = repository.webSocketIncomingData().asLiveData()
    val webSocketIncomingActionResponse: LiveData<ActionResponse?> =
        repository.webSocketIncomingResult().asLiveData()

    private fun webSocketCreate() {
        repository.webSocketCreate()
    }

    fun webSocketSend(data: Request) {
        repository.webSocketSend(data)
    }
}
