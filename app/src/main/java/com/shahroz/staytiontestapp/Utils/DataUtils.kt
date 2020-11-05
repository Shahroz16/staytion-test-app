package com.shahroz.staytiontestapp.Utils

import com.shahroz.staytiontestapp.Model.ActionResponse
import com.google.gson.Gson
import com.shahroz.staytiontestapp.Request
import com.shahroz.staytiontestapp.Model.Response

object DataUtils {
    fun requestToJSON(request: Request): String = Gson().toJson(request)

    fun jsonToActionResponse(json: String): ActionResponse = Gson().fromJson(json, ActionResponse::class.java)

    fun jsonToResponse(json: String): Response = Gson().fromJson(json, Response::class.java)
}
