package com.svoboda.ebike.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.svoboda.ebike.domain.model.Bike
import com.svoboda.ebike.domain.model.WheelSize
import com.svoboda.ebike.domain.usecase.CalculateSpeedUseCase
import com.svoboda.ebike.domain.usecase.ObserveAllBikesUseCase
import com.svoboda.ebike.domain.usecase.ObserveAllWheelSizesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

/**
 * A ViewModel class responsible for managing the state and interactions of bikes, wheel sizes, and RPM.
 *
 * @property calculateSpeedUseCase Use case to calculate the speed of an electric bike.
 * @property observeAllBikesUseCase Use case to observe all bikes from the repository.
 * @property observeAllWheelSizesUseCase Use case to observe all wheel sizes from the repository.
 */
class BikeViewModel(
    private val calculateSpeedUseCase: CalculateSpeedUseCase,
    private val observeAllBikesUseCase: ObserveAllBikesUseCase,
    private val observeAllWheelSizesUseCase: ObserveAllWheelSizesUseCase,
) : ViewModel() {

    /** Flow containing the list of bikes. */
    val bikes = observeAllBikesUseCase()

    /** Flow containing the list of wheel sizes. */
    val wheelSizes = observeAllWheelSizesUseCase()

    /** Mutable state flow representing the currently selected bike. */
    val selectedBike = MutableStateFlow<Bike?>(null)

    /** Mutable state flow representing the currently selected wheel size. */
    val selectedWheelSize = MutableStateFlow<WheelSize?>(null)

    /** Mutable state flow representing the current RPM value. */
    val currentRpm = MutableStateFlow<Int?>(null)

    /** Flow representing the calculated speed based on the selected bike, wheel size, and current RPM. */
    val speed = combine(
        selectedBike,
        selectedWheelSize,
        currentRpm
    ) { selectedBike, selectedWheelSize, currentRpm ->
        if (selectedBike != null && selectedWheelSize != null && currentRpm != null) {
            calculateSpeedUseCase(selectedBike.gearRatio, selectedWheelSize.meters, currentRpm)
        } else {
            null
        }
    }

    /**
     * Updates the selected bike.
     *
     * @param bike The selected bike.
     */
    fun selectBike(bike: Bike) {
        selectedBike.value = bike
    }

    /**
     * Updates the selected wheel size.
     *
     * @param wheelSize The selected wheel size.
     */
    fun selectWheelSize(wheelSize: WheelSize) {
        selectedWheelSize.value = wheelSize
    }

    /**
     * Updates the current RPM value.
     *
     * @param rpm The new RPM value.
     */
    fun setCurrentRpm(rpm: Int) {
        val maxRpm = selectedBike.value?.maxRpm
        val validRpm = if (maxRpm != null && rpm <= maxRpm) rpm else maxRpm ?: rpm

        currentRpm.value = validRpm
    }
}
