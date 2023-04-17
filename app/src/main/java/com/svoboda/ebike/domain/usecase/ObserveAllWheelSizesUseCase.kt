package com.svoboda.ebike.domain.usecase

import com.svoboda.ebike.domain.repository.WheelSizeRepository

/**
 * Observes all wheel sizes from the wheel size repository.
 */
class ObserveAllWheelSizesUseCase(
    private val wheelSizeRepository: WheelSizeRepository
) {
    operator fun invoke() = wheelSizeRepository.observeAllWheelSizes()
}
