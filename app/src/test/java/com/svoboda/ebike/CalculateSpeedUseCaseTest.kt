package com.svoboda.ebike

import com.svoboda.ebike.domain.usecase.CalculateSpeedUseCase
import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * Test class for the [CalculateSpeedUseCase].
 * It covers various test scenarios to ensure the correct calculation of speed.
 */
class CalculateSpeedUseCaseTest {
    private val calculateSpeedUseCase = CalculateSpeedUseCase()

    /**
     * Tests the speed calculation with sample values for rpm, gearRatio, and wheelSize.
     */
    @Test
    fun `test speed calculation with sample values`() {
        val rpm = 3500
        val gearRatio = 0.0637
        val wheelSize = 0.5838

        val expectedSpeed = 24.5342 // in km/h

        val actualSpeed = calculateSpeedUseCase(gearRatio, wheelSize, rpm)
        assertEquals(expectedSpeed, actualSpeed, 0.01)
    }

    /**
     * Tests the speed calculation when the rpm value is zero.
     */
    @Test
    fun `test speed calculation with zero rpm`() {
        val rpm = 0
        val gearRatio = 0.0637
        val wheelSize = 0.5838

        val expectedSpeed = 0.0 // in km/h

        val actualSpeed = calculateSpeedUseCase(gearRatio, wheelSize, rpm)
        assertEquals(expectedSpeed, actualSpeed, 0.01)
    }

    /**
     * Tests the speed calculation with a different gear ratio value.
     */
    @Test
    fun `test speed calculation with different gear ratio`() {
        val rpm = 3500
        val gearRatio = 0.085
        val wheelSize = 0.5838

        val expectedSpeed = 32.7380 // in km/h

        val actualSpeed = calculateSpeedUseCase(gearRatio, wheelSize, rpm)
        assertEquals(expectedSpeed, actualSpeed, 0.01)
    }

    /**
     * Tests the speed calculation with another gear ratio value.
     */
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
