package ru.netology.nmedia.ui.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.netology.nmedia.ui.data.dao.PointDao
import ru.netology.nmedia.ui.data.entity.PointEntity

@Database(entities = [PointEntity::class], version = 1, exportSchema = false)

abstract class AppDb : RoomDatabase() {
    abstract val pointDao: PointDao

}