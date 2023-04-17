package com.svoboda.ebike.domain.usecase

import java.lang.Math.PI

/**
 * Calculates the speed of an electric bike based on the gear ratio, wheel size in meters, and RPM.
 */
class CalculateSpeedUseCase {
    operator fun invoke(gearRatio: Double, wheelSize: Double, rpm: Int): Double {
        val circumference = wheelSize * PI
        val speedInMetersPerMinute = rpm * gearRatio * circumference
        return speedInMetersPerMinute * 0.06 // converting to km/h
    }
}
