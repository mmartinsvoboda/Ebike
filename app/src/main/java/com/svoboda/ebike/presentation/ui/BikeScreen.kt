@file:OptIn(ExperimentalMaterial3Api::class)

package com.svoboda.ebike.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.svoboda.ebike.R
import com.svoboda.ebike.domain.model.Bike
import com.svoboda.ebike.domain.model.WheelSize
import com.svoboda.ebike.presentation.viewmodel.BikeViewModel

@Composable
fun BikeScreen(viewModel: BikeViewModel) {
    val bikes = viewModel.bikes.collectAsState(emptyList())
    val wheelSizes = viewModel.wheelSizes.collectAsState(emptyList())
    val speed = viewModel.speed.collectAsState(null)

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        // Bike selection
        item {
            val selectedBike = viewModel.selectedBike.collectAsState()

            BikeDropdown(
                bikes = bikes.value,
                selectedBike = selectedBike.value,
                onBikeSelected = { viewModel.selectBike(it) }
            )
        }

        // Wheel size selection
        item {
            val selectedWheelSize = viewModel.selectedWheelSize.collectAsState()

            WheelSizeDropdown(
                wheelSizes = wheelSizes.value,
                selectedWheelSize = selectedWheelSize.value,
                onWheelSizeSelected = { viewModel.selectWheelSize(it) }
            )
        }

        // RPM input
        item {
            val currentRpm = viewModel.currentRpm.collectAsState()
            val selectedBike = viewModel.selectedBike.collectAsState()

            RpmInput(
                currentRpm = currentRpm.value,
                maxRpm = selectedBike.value?.maxRpm,
                onRpmChanged = { viewModel.setCurrentRpm(it) }
            )
        }

        // Calculated speed
        item {
            CalculatedSpeed(speed.value)
        }
    }
}

@Composable
private fun BikeDropdown(bikes: List<Bike>, selectedBike: Bike?, onBikeSelected: (Bike) -> Unit) {
    val bikeExpanded = remember { mutableStateOf(false) }

    Text(text = stringResource(R.string.select_a_bike), modifier = Modifier.fillMaxWidth())

    DropdownMenu(
        expanded = bikeExpanded.value,
        onDismissRequest = { bikeExpanded.value = false },
        modifier = Modifier.fillMaxWidth()
    ) {
        bikes.forEach { bike ->
            DropdownMenuItem(
                text = { Text(text = bike.name) },
                onClick = {
                    onBikeSelected(bike)
                    bikeExpanded.value = false
                }
            )
        }
    }

    Button(
        onClick = { bikeExpanded.value = !bikeExpanded.value },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = selectedBike?.name ?: stringResource(R.string.select_a_bike_variant))
    }
}

@Composable
private fun WheelSizeDropdown(
    wheelSizes: List<WheelSize>,
    selectedWheelSize: WheelSize?,
    onWheelSizeSelected: (WheelSize) -> Unit
) {
    val wheelSizeExpanded = remember { mutableStateOf(false) }

    Text(text = stringResource(R.string.select_a_wheel_size), modifier = Modifier.fillMaxWidth())

    DropdownMenu(
        expanded = wheelSizeExpanded.value,
        onDismissRequest = { wheelSizeExpanded.value = false },
        modifier = Modifier.fillMaxWidth()
    ) {
        wheelSizes.forEach { wheelSize ->
            DropdownMenuItem(
                text = { Text(text = "${wheelSize.inches} (${wheelSize.meters} m)") },
                onClick = {
                    onWheelSizeSelected(wheelSize)
                    wheelSizeExpanded.value = false
                }
            )
        }
    }

    Button(
        onClick = { wheelSizeExpanded.value = !wheelSizeExpanded.value },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "${selectedWheelSize?.inches} (${selectedWheelSize?.meters} m)".takeIf { selectedWheelSize != null }
                ?: stringResource(R.string.select_a_wheel_size_variant)
        )
    }
}

@Composable
private fun RpmInput(currentRpm: Int?, maxRpm: Int?, onRpmChanged: (Int) -> Unit) {
    Text(text = stringResource(R.string.enter_current_rpm), modifier = Modifier.fillMaxWidth())

    Slider(
        value = currentRpm?.toFloat() ?: 0f,
        onValueChange = { value -> onRpmChanged(value.toInt()) },
        valueRange = 0f..(maxRpm?.toFloat() ?: 10000f)
    )

    TextField(
        value = currentRpm?.toString() ?: "",
        onValueChange = { input ->
            onRpmChanged(input.toIntOrNull() ?: 0)
        },
        placeholder = {
            Text(text = stringResource(R.string.rpm))
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            autoCorrect = false
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun CalculatedSpeed(speed: Double?) {
    if (speed != null) {
        Text(
            text = "Calculated speed: ${"%.2f".format(speed)} km/h",
            modifier = Modifier.fillMaxWidth()
        )
    } else {
        Text(
            text = stringResource(R.string.please_select_a_bike_wheel_size_and_enter_rpm_to_calculate_speed),
            modifier = Modifier.fillMaxWidth()
        )
    }
}
