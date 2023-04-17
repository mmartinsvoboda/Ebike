package com.svoboda.ebike.data.repository

import com.svoboda.ebike.data.local.dao.WheelSizeDao
import com.svoboda.ebike.data.local.entity.WheelSizeEntity
import com.svoboda.ebike.domain.model.WheelSize
import com.svoboda.ebike.domain.repository.WheelSizeRepository
import kotlinx.coroutines.flow.map

class WheelSizeRepositoryImpl(private val wheelSizeDao: WheelSizeDao) : WheelSizeRepository {
    override suspend fun insertAllWheelSizes(wheelSizes: List<WheelSize>) {
        wheelSizes.map {
            WheelSizeEntity(it.inches, it.meters)
        }.also {
            wheelSizeDao.insertAll(it)
        }
    }

    override fun observeAllWheelSizes() = wheelSizeDao.getAllWheelSizes().map { wheelSizeEntities ->
        wheelSizeEntities.map { wheelSizeEntity ->
            WheelSize(
                wheelSizeEntity.inches,
                wheelSizeEntity.meters
            )
        }
    }
}
