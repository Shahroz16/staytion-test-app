package com.shahroz.staytiontestapp.ui

enum class DeviceTypes(value: Int) {
    LOCK(0),
    LIGHT(1),
    CLIMATE(2),
    OTHER(3)
}

object DeviceDomains {
    const val LOCK = "lock"
    const val LIGHT = "light"
    const val CLIMATE = "climate"
}

object DeviceStates {
    const val CLIMATE_STATE_ON = "heat"
    const val CLIMATE_STATE_OFF = "off"

    const val CLIMATE_SERVICE_STATE_ON = "turn_on"
    const val CLIMATE_SERVICE_STATE_OFF = "turn_off"

    const val LOCK_STATE_LOCK = "locked"
    const val LOCK_STATE_UNLOCK = "unlocked"

    const val LOCK_SERVICE_STATE_LOCK = "lock"
    const val LOCK_SERVICE_STATE_UNLOCK = "unlock"

    const val LIGHT_STATE_ON = "on"
    const val LIGHT_STATE_OFF = "off"

    const val LIGHT_SERVICE_STATE_ON = "turn_on"
    const val LIGHT_SERVICE_STATE_OFF = "turn_off"
}
