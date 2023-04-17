package com.svoboda.ebike.domain.model

/**
 * Bike
 *
 * @property name
 * @property maxRpm
 * @property gearRatio
 * @constructor Create empty Bike
 */
data class Bike(
    val name: String,
    val maxRpm: Int,
    val gearRatio: Double
)

