package ru.netology.nmedia.ui.data.repository

import kotlinx.coroutines.flow.Flow
import ru.netology.nmedia.ui.data.dto.Point

interface PointRepository {
    val data: Flow<List<Point>>

    suspend fun getAll(): Long
    suspend fun save(point: Point)
    suspend fun removeById(id: Long)
    suspend fun edit(point: Point)

}