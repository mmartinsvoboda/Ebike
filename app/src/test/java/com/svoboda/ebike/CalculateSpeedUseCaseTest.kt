package com.svoboda.ebike

import com.svoboda.ebike.domain.usecase.CalculateSpeedUseCase
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CalculateSpeedUseCaseTest {
    private val calculateSpeedUseCase = CalculateSpeedUseCase()

    @Test
    fun `test speed calculation with sample values`() {
        val rpm = 3500
        val gearRatio = 0.0637
        val wheelSize = 0.5838

        val expectedSpeed = 24.5342 // in km/h

        val actualSpeed = calculateSpeedUseCase(gearRatio, wheelSize, rpm)
        assertEquals(expectedSpeed, actualSpeed, 0.01)
    }

    @Test
    fun `test speed calculation with zero rpm`() {
        val rpm = 0
        val gearRatio = 0.0637
        val wheelSize = 0.5838

        val expectedSpeed = 0.0 // in km/h

        val actualSpeed = calculateSpeedUseCase(gearRatio, wheelSize, rpm)
        assertEquals(expectedSpeed, actualSpeed, 0.01)
    }

    @Test
    fun `test speed calculation with different gear ratio`() {
        val rpm = 3500
        val gearRatio = 0.085
        val wheelSize = 0.5838

        val expectedSpeed = 32.7380 // in km/h

        val actualSpeed = calculateSpeedUseCase(gearRatio, wheelSize, rpm)
        assertEquals(expectedSpeed, actualSpeed, 0.01)
    }

    @Test
    fun `test speed calculation with another gear ratio`() {
        val rpm = 3500
        val gearRatio = 0.0637
        val wheelSize = 0.5831

        val expectedSpeed = 24.50666 // in km/h

        val actualSpeed = calculateSpeedUseCase(gearRatio, wheelSize, rpm)
        assertEquals(expectedSpeed, actualSpeed, 0.01)
    }
}
