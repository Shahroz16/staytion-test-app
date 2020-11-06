package com.shahroz.staytiontestapp.data

import com.shahroz.staytiontestapp.model.ActionResponse
import com.shahroz.staytiontestapp.model.Request
import com.shahroz.staytiontestapp.model.Response
import com.shahroz.staytiontestapp.model.StateResponse
import kotlinx.coroutines.flow.StateFlow

interface WebSocketChannelInterface {
    fun getIncomingData(): StateFlow<Response?>
    fun getIncomingResult(): StateFlow<ActionResponse?>
    fun getStateResponse(): StateFlow<StateResponse?>
    fun close(code: Int = 1000, reason: String? = null)
    fun send(data: Request)
}
