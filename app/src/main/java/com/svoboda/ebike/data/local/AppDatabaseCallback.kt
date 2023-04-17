package com.svoboda.ebike.data.local

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.svoboda.ebike.data.local.entity.BikeEntity
import com.svoboda.ebike.data.local.entity.WheelSizeEntity
import com.svoboda.ebike.domain.usecase.ParseBikesUseCase
import com.svoboda.ebike.domain.usecase.ParseWheelSizesUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * A callback for initializing the AppDatabase with data parsed from XML files.
 * This class extends RoomDatabase.Callback() and overrides its onCreate method.
 *
 * @property context The application context required for accessing assets.
 * @property parseBikesUseCase The use case for parsing bikes from the bikes XML file.
 * @property parseWheelSizesUseCase The use case for parsing wheel sizes from the wheel sizes XML file.
 */
class AppDatabaseCallback(
    private val context: Context,
    private val parseBikesUseCase: ParseBikesUseCase,
    private val parseWheelSizesUseCase: ParseWheelSizesUseCase
) : RoomDatabase.Callback() {

    /**
     * Called when the database is created for the first time.
     * This method parses data from the XML files and inserts it into the database.
     *
     * @param db The database that was just created.
     */
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        val bikes = parseBikesUseCase().map { bike ->
            BikeEntity(name = bike.name, maxRpm = bike.maxRpm, gearRatio = bike.gearRatio)
        }

        val wheelSizes = parseWheelSizesUseCase().map { wheelSize ->
            WheelSizeEntity(wheelSize.inches, wheelSize.meters)
        }

        GlobalScope.launch {
            val appDatabase =
                AppDatabase.getDatabase(context, parseBikesUseCase, parseWheelSizesUseCase)
            appDatabase.bikeDao().insertAll(bikes)
            appDatabase.wheelSizeDao().insertAll(wheelSizes)
        }
    }
}
