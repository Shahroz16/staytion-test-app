package com.shahroz.staytiontestapp.model

data class Response(
    val id: Long,
    val type: String,
    val success: Boolean,
    val result: List<Result>
)

data class Result(
    val entityID: String,
    val state: String,
    val attributes: Attributes,
    val lastChanged: String,
    val lastUpdated: String,
    val context: Context
)

data class Attributes(
    val editable: Boolean? = null,
    val id: String? = null,
    val userID: String? = null,
    val friendlyName: String,
    val nextDawn: String? = null,
    val nextDusk: String? = null,
    val nextMidnight: String? = null,
    val nextNoon: String? = null,
    val nextRising: String? = null,
    val nextSetting: String? = null,
    val elevation: Double? = null,
    val azimuth: Double? = null,
    val rising: Boolean? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val radius: Long? = null,
    val passive: Boolean? = null,
    val icon: String? = null,
    val hvacModes: List<String>? = null,
    val minTemp: Long? = null,
    val maxTemp: Long? = null,
    val currentTemperature: Long? = null,
    val temperature: Double? = null,
    val hvacAction: String? = null,
    val supportedFeatures: Long? = null,
    val minMireds: Long? = null,
    val maxMireds: Long? = null,
    val effectList: List<String>? = null,
    val brightness: Long? = null,
    val colorTemp: Long? = null,
    val whiteValue: Long? = null,
    val hsColor: List<Long>? = null,
    val rgbColor: List<Long>? = null,
    val xyColor: List<Double>? = null,
    val humidity: Long? = null,
    val pressure: Double? = null,
    val windBearing: Double? = null,
    val windSpeed: Double? = null,
    val attribution: String? = null,
    val forecast: List<Forecast>? = null
)

data class Forecast(
    val condition: String,
    val temperature: Double,
    val templow: Double,
    val datetime: String,
    val windBearing: Double,
    val windSpeed: Double,
    val precipitation: Double? = null
)

data class Context(
    val id: String,
    val parentID: Any? = null,
    val userID: String? = null
)
