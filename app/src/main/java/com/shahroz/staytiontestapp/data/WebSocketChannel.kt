package com.shahroz.staytiontestapp.data

import com.shahroz.staytiontestapp.model.ActionResponse
import com.shahroz.staytiontestapp.model.Response
import com.shahroz.staytiontestapp.model.StateResponse
import com.shahroz.staytiontestapp.utils.DataUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class WebSocketChannel : WebSocketChannelInterface {

    private val socket: WebSocket

    private val _allData = MutableStateFlow<Response?>(null)
    private val allData: StateFlow<Response?> = _allData

    private val _result = MutableStateFlow<ActionResponse?>(null)
    private val result: StateFlow<ActionResponse?> = _result

    private val _stateResponse = MutableStateFlow<StateResponse?>(null)
    private val stateResponse: StateFlow<StateResponse?> = _stateResponse

    init {
        val okHttpClient = OkHttpClient()
        val request = Request
            .Builder()
            .url(Constants.BASE_URL)
            .build()

        socket = okHttpClient.newWebSocket(
            request,
            ChatIOWebSocketListener()
        )
        okHttpClient.dispatcher.executorService.shutdown()
    }

    override fun getIncomingData(): StateFlow<Response?> {
        return allData
    }

    override fun getIncomingResult(): StateFlow<ActionResponse?> {
        return result
    }

    override fun getStateResponse(): StateFlow<StateResponse?> {
        return stateResponse
    }

    override fun close(code: Int, reason: String?) {
        socket.close(code, reason)
    }

    override fun send(data: com.shahroz.staytiontestapp.model.Request) {
        socket.send(DataUtils.requestToJSON(data))
    }

    private inner class ChatIOWebSocketListener : WebSocketListener() {
        override fun onMessage(webSocket: WebSocket, text: String) {
            val response = DataUtils.jsonToStateResponse(text)
            when (response?.id) {
                1L -> if (response.type == "event")
                    _stateResponse.value = DataUtils.jsonToStateResponse(text)
                3L -> if (response.type == "result")
                    _allData.value = DataUtils.jsonToResponse(text)
                19L -> if (response.type == "result")
                    _result.value = DataUtils.jsonToActionResponse(text)
            }
        }
    }
}

