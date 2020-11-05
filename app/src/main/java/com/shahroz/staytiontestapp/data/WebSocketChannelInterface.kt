package com.shahroz.staytiontestapp.data

import com.shahroz.staytiontestapp.Model.ActionResponse
import com.shahroz.staytiontestapp.Request
import com.shahroz.staytiontestapp.Model.Response
import kotlinx.coroutines.flow.StateFlow

interface WebSocketChannelInterface {
    fun getIncomingData(): StateFlow<Response?>
    fun getIncomingResult(): StateFlow<ActionResponse?>
    fun close(code: Int = 1000, reason: String? = null)
    fun send(data: Request)
}
