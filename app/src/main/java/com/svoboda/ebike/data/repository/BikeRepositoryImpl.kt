package com.svoboda.ebike.data.repository

import com.svoboda.ebike.data.local.dao.BikeDao
import com.svoboda.ebike.data.local.entity.BikeEntity
import com.svoboda.ebike.domain.model.Bike
import com.svoboda.ebike.domain.repository.BikeRepository
import kotlinx.coroutines.flow.map

class BikeRepositoryImpl(private val bikeDao: BikeDao) : BikeRepository {
    override suspend fun insertAllBikes(bikes: List<Bike>) {
        bikes.map { bike ->
            BikeEntity(
                bike.name,
                bike.maxRpm,
                bike.gearRatio
            )
        }.also {
            bikeDao.insertAll(it)
        }
    }

    override fun observeAllBikes() = bikeDao.getAllBikes().map { bikeEntities ->
        bikeEntities.map { bikeEntity ->
            Bike(
                bikeEntity.name,
                bikeEntity.maxRpm,
                bikeEntity.gearRatio
            )
        }
    }
}
