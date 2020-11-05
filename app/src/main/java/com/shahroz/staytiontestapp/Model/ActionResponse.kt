package com.shahroz.staytiontestapp.Model

data class ActionResponse(
    val id: Int,
    val type: String,
    val success: Boolean,
    val result: Result1
)

data class Result1(
    val context: Context1
)

data class Context1(
    val id: String,
    val parentID: Any? = null,
    val userID: String
)
