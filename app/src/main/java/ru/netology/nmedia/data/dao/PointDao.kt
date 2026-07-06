package ru.netology.nmedia.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.netology.nmedia.data.entity.PointEntity

@Dao
interface PointDao {
    @Query("SELECT * FROM PointEntity ORDER BY id DESC")
    fun getAll(): Flow<List<PointEntity>>

    @Insert(onConflict = REPLACE)
    suspend fun insert(point: PointEntity)

    @Query(
        """UPDATE PointEntity         
            SET name = :name, 
            description = :description, 
            latitude = :latitude, 
            longitude = :longitude,
            isVisited = :isVisited WHERE id = :id"""
    )
    suspend fun updateContentById(
        id: Long,
        name: String,
        description: String?,
        latitude: Double,
        longitude: Double,
        isVisited: Boolean,
    )

    @Query("DELETE FROM PointEntity WHERE id = :id")
    suspend fun removeById(id: Long)
}