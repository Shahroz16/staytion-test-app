package com.shahroz.staytiontestapp.model

data class ActionResponse(
    val id: Long,
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
