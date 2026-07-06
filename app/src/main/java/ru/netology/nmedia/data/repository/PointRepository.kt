package ru.netology.nmedia.data.repository

import kotlinx.coroutines.flow.Flow
import ru.netology.nmedia.data.dto.Point

interface PointRepository {
    val data: Flow<List<Point>>
    suspend fun save(point: Point)
    suspend fun removeById(id: Long)
}