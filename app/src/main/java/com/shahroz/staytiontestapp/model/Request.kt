package com.shahroz.staytiontestapp.model

data class Request(
    val id: String? = null,
    val type: String? = null,
    val domain: String? = null,
    val service: String? = null,
    val event_type: String? = null,
    val access_token: String? = null,
    val service_data: ServiceData? = null
)

data class ServiceData(
    val entity_id: String,
    val brightness: String? = null,
    val temperature: String? = null
)
