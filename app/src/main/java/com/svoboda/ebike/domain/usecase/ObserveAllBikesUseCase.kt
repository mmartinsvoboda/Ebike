package com.svoboda.ebike.domain.usecase

import com.svoboda.ebike.domain.repository.BikeRepository

/**
 * Observes all bikes from the bike repository.
 */
class ObserveAllBikesUseCase(
    private val bikeRepository: BikeRepository
) {
    operator fun invoke() = bikeRepository.observeAllBikes()
}
