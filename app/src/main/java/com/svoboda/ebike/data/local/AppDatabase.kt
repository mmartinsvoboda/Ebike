package com.svoboda.ebike.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.svoboda.ebike.data.local.dao.BikeDao
import com.svoboda.ebike.data.local.dao.WheelSizeDao
import com.svoboda.ebike.data.local.entity.BikeEntity
import com.svoboda.ebike.data.local.entity.WheelSizeEntity
import com.svoboda.ebike.domain.usecase.ParseBikesUseCase
import com.svoboda.ebike.domain.usecase.ParseWheelSizesUseCase

@Database(entities = [BikeEntity::class, WheelSizeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bikeDao(): BikeDao
    abstract fun wheelSizeDao(): WheelSizeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context,
            parseBikesUseCase: ParseBikesUseCase,
            parseWheelSizesUseCase: ParseWheelSizesUseCase
        ): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).addCallback(
                    AppDatabaseCallback(
                        context,
                        parseBikesUseCase,
                        parseWheelSizesUseCase
                    )
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
