package com.svoboda.ebike.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.svoboda.ebike.data.local.entity.WheelSizeEntity
import kotlinx.coroutines.flow.Flow

/**
 * A Data Access Object (DAO) for the WheelSizeEntity table in the AppDatabase.
 * This interface defines methods for inserting and retrieving wheel size entities.
 */
@Dao
interface WheelSizeDao {
    /**
     * Inserts a list of WheelSizeEntity objects into the database.
     * In case of conflict, the existing data is replaced.
     *
     * @param wheelSizes The list of WheelSizeEntity objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(wheelSizes: List<WheelSizeEntity>)

    /**
     * Retrieves all WheelSizeEntity objects from the database as a Flow.
     *
     * @return A Flow containing a list of all WheelSizeEntity objects in the database.
     */
    @Query("SELECT * FROM wheel_sizes")
    fun getAllWheelSizes(): Flow<List<WheelSizeEntity>>
}
