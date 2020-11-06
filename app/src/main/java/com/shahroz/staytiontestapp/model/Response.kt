package com.shahroz.staytiontestapp.model

data class Response(
    val id: Long,
    val type: String,
    val success: Boolean,
    val result: List<Device>
)

data class Device(
    val entity_id: String,
    var state: String,
    var attributes: Attributes,
    var context: Context
)

data class Attributes(
    val id: String? = null,
    val user_id: String? = null,
    val friendly_name: String,
    val min_temp: Float? = null,
    val max_temp: Float? = null,
    val currentTemperature: Float? = null,
    var temperature: Float? = null,
    var brightness: Long? = null,
)

data class Context(
    val id: String,
    val parent_id: Any? = null,
    val user_id: String? = null
)
