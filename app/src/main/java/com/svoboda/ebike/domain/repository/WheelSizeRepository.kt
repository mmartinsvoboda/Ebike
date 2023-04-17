package com.svoboda.ebike.domain.repository

import com.svoboda.ebike.domain.model.WheelSize
import kotlinx.coroutines.flow.Flow

/**
 * Wheel size repository
 *
 * @constructor Create empty Wheel size repository
 */
interface WheelSizeRepository {
    /**
     * Insert all wheel sizes
     *
     * @param wheelSizes
     */
    suspend fun insertAllWheelSizes(wheelSizes: List<WheelSize>)

    /**
     * Observe all wheel sizes
     *
     * @return
     */
    fun observeAllWheelSizes(): Flow<List<WheelSize>>
}
