package com.svoboda.ebike.domain.usecase

import com.svoboda.ebike.domain.model.Bike
import com.svoboda.ebike.domain.repository.BikeRepository

/**
 * Handles the insertion of all bikes into the bike repository.
 */

class InsertAllBikesUseCase(
    private val bikeRepository: BikeRepository
) {
    suspend operator fun invoke(bikes: List<Bike>) = bikeRepository.insertAllBikes(bikes)
}
