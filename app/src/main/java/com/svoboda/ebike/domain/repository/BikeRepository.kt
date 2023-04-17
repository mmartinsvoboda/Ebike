package com.svoboda.ebike.domain.repository

import com.svoboda.ebike.domain.model.Bike
import kotlinx.coroutines.flow.Flow

/**
 * Bike repository
 *
 * @constructor Create empty Bike repository
 */
interface BikeRepository {
    /**
     * Insert all bikes
     *
     * @param bikes
     */
    suspend fun insertAllBikes(bikes: List<Bike>)

    /**
     * Observe all bikes
     *
     * @return
     */
    fun observeAllBikes(): Flow<List<Bike>>
}
