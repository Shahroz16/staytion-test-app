package com.shahroz.staytiontestapp

data class Request(
    val id: String,
    val type: String,
    val domain: String? = null,
    val service: String? = null,
    val event_type: String? = null,
    val service_data: ServiceData? = null
)

data class ServiceData(
    val entity_id: String,
    val brightness: String? = null
)
