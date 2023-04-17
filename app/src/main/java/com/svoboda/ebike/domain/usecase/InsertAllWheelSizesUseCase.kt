package com.svoboda.ebike.domain.usecase

import com.svoboda.ebike.domain.model.WheelSize
import com.svoboda.ebike.domain.repository.WheelSizeRepository

/**
 * Handles the insertion of all wheel sizes into the wheel size repository.
 */
class InsertAllWheelSizesUseCase(
    private val wheelSizeRepository: WheelSizeRepository
) {
    suspend operator fun invoke(wheelSizes: List<WheelSize>) =
        wheelSizeRepository.insertAllWheelSizes(wheelSizes)
}
