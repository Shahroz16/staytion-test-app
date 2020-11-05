package com.shahroz.staytiontestapp.data

import android.util.Log
import com.google.gson.Gson
import com.shahroz.staytiontestapp.Utils.DataUtils
import com.shahroz.staytiontestapp.model.ActionResponse
import com.shahroz.staytiontestapp.model.Response
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

    override fun close(code: Int, reason: String?) {
        socket.close(code, reason)
    }

    override fun send(data: com.shahroz.staytiontestapp.Request) {
        socket.send(DataUtils.requestToJSON(data))
    }

    private inner class ChatIOWebSocketListener : WebSocketListener() {
        override fun onMessage(webSocket: WebSocket, text: String) {
            Log.d("TAG", "onMessage: $text")

            val response = DataUtils.jsonToActionResponse(text)
            when (response.id) {
                0 -> {
                    if (response.type == "auth_required") {
                        val map = mapOf("type" to "auth", "access_token" to Constants.TOKEN)
                        val json = Gson().toJson(map)
                        webSocket.send(json)
                    }
                }
                1 -> {
                    _result.value = response
                }
                3 -> {
                    _allData.value = DataUtils.jsonToResponse(text)
                }
                19 -> {
                    _result.value = response
                }
                else -> {
                    _result.value = response
                }
            }
        }
    }
}
