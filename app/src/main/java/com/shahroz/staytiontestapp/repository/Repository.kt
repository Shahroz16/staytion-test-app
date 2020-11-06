package com.shahroz.staytiontestapp.repository

import com.shahroz.staytiontestapp.data.WebSocketChannel
import com.shahroz.staytiontestapp.data.WebSocketChannelInterface
import com.shahroz.staytiontestapp.model.ActionResponse
import com.shahroz.staytiontestapp.model.Request
import com.shahroz.staytiontestapp.model.Response
import com.shahroz.staytiontestapp.model.StateResponse
import kotlinx.coroutines.flow.StateFlow

class Repository {
    private lateinit var channel: WebSocketChannelInterface

    fun webSocketCreate() {
        channel = WebSocketChannel()
    }

    fun webSocketSend(data: Request) {
        channel.send(data)
    }

    fun webSocketIncomingResult(): StateFlow<ActionResponse?> {
        return channel.getIncomingResult()
    }

    fun webSocketIncomingData(): StateFlow<Response?> {
        return channel.getIncomingData()
    }

    fun webSocketStateResponse(): StateFlow<StateResponse?> {
        return channel.getStateResponse()
    }

    fun closeChannel() {
        channel.close()
    }
}
