package com.shahroz.staytiontestapp.model

data class StateResponse(
    val id: Long,
    val type: String,
    val event: Event?
)

data class Event(
    val event_type: String,
    val data: Data,
    val context: Context
)

data class Data(
    val entity_id: String,
    val old_state: State,
    val new_state: State
)

data class State(
    val entity_id: String,
    val state: String,
    val attributes: Attributes,
    val context: Context
)
