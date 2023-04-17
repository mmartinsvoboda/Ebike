package com.svoboda.ebike.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bikes")
data class BikeEntity(
    @PrimaryKey val name: String,
    val maxRpm: Int,
    val gearRatio: Double
)
