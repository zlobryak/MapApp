package ru.netology.nmedia.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia.data.dto.Point

@Entity
data class PointEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val description: String?,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val isVisited: Boolean = false,
) {
    fun toDto() = Point(
        id,
        description,
        name,
        latitude,
        longitude,
        isVisited,
    )

    companion object{
        fun fromDto(point: Point) = PointEntity(
            id = point.id,
            description = point.description,
            name = point.name,
            latitude = point.latitude,
            longitude = point.longitude,
            isVisited = point.isVisited
        )
    }
}
