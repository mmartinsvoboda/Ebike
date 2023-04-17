package com.svoboda.ebike.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wheel_sizes")
data class WheelSizeEntity(
    @PrimaryKey val inches: Double,
    val meters: Double
)
