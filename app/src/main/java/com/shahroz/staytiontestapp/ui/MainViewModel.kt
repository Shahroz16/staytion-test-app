package com.shahroz.staytiontestapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.shahroz.staytiontestapp.data.Constants
import com.shahroz.staytiontestapp.model.*
import com.shahroz.staytiontestapp.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel @ViewModelInject constructor(private val repository: Repository) : ViewModel() {
    var allDevices: List<Device> = listOf()

    init {
        webSocketCreate()
        authenticateSocket()
    }

    private fun authenticateSocket() {
        val request = Request(type = "auth", access_token = Constants.TOKEN)
        webSocketSend(request)
    }

    val webSocketIncomingData: LiveData<Response?> =
        repository.webSocketIncomingData().asLiveData()

    val webSocketIncomingActionResponse: LiveData<ActionResponse?> =
        repository.webSocketIncomingResult().asLiveData()

    val webSocketIncomingStateResponse: LiveData<StateResponse?> =
        repository.webSocketStateResponse().asLiveData()

    fun updateDevice(stateResponse: StateResponse) {
        allDevices
            .filter { it.entity_id == stateResponse.event?.data?.entity_id }
            .first { it.context.id == stateResponse.event?.data?.old_state?.context?.id }
            .apply {
                stateResponse.event?.data?.new_state?.let {
                    this.context = it.context
                    this.state = it.state
                    this.attributes.brightness = it.attributes.brightness
                    this.attributes.temperature = it.attributes.temperature
                }
            }
    }

    @InternalCoroutinesApi
    fun readDevicesData() {
        val request = Request(id = "3", type = "get_states")
        webSocketSend(request)

        viewModelScope.launch(Dispatchers.IO) {
            repository.webSocketIncomingData().collect {
                it?.let { allDevices = it.result.shuffled(Random()) }
            }
        }
    }

    private fun webSocketCreate() {
        repository.webSocketCreate()
    }

    fun setStateChangeListener() {
        val request = Request(
            id = "1",
            type = "subscribe_events",
            event_type = "state_changed"
        )
        webSocketSend(request)
    }

    fun webSocketSend(data: Request) {
        repository.webSocketSend(data)
    }

    override fun onCleared() {
        super.onCleared()
        repository.closeChannel()
    }
}
