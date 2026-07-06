package ru.netology.nmedia.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.netology.nmedia.data.dao.PointDao
import ru.netology.nmedia.data.entity.PointEntity

@Database(entities = [PointEntity::class], version = 1, exportSchema = false)

abstract class AppDb : RoomDatabase() {
    abstract val pointDao: PointDao

}