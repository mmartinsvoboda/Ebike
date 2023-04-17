package com.svoboda.ebike.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.svoboda.ebike.data.local.entity.BikeEntity
import kotlinx.coroutines.flow.Flow

/**
 * A Data Access Object (DAO) for the BikeEntity table in the AppDatabase.
 * This interface defines methods for inserting and retrieving bike entities.
 */
@Dao
interface BikeDao {
    /**
     * Inserts a list of BikeEntity objects into the database.
     * In case of conflict, the existing data is replaced.
     *
     * @param bikes The list of BikeEntity objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(bikes: List<BikeEntity>)

    /**
     * Retrieves all BikeEntity objects from the database as a Flow.
     *
     * @return A Flow containing a list of all BikeEntity objects in the database.
     */
    @Query("SELECT * FROM bikes")
    fun getAllBikes(): Flow<List<BikeEntity>>
}
