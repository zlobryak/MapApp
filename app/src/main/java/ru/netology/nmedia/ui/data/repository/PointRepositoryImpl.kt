package ru.netology.nmedia.ui.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.netology.nmedia.ui.data.dao.PointDao
import ru.netology.nmedia.ui.data.dto.Point
import ru.netology.nmedia.ui.data.entity.PointEntity
import javax.inject.Inject

class PointRepositoryImpl @Inject constructor(
    private val dao: PointDao,
) : PointRepository {
    override val data: Flow<List<Point>> = dao.getAll().map { entities ->
        entities.map { it.toDto() }
    }

    override suspend fun save(point: Point) {
        if (point.id == 0L) {
            dao.insert(
                PointEntity.fromDto(point)
            )
        } else {
            dao.updateContentById(
                id = point.id,
                name = point.name,
                description = point.description,
                latitude = point.latitude,
                longitude = point.longitude,
                isVisited = point.isVisited
            )
        }
    }

    override suspend fun removeById(id: Long) {
        dao.removeById(id)
    }
}
