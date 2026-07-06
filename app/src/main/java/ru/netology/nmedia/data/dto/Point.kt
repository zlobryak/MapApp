package ru.netology.nmedia.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Point  (
    val id: Long = 0,
    val description: String?,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val isVisited: Boolean = false,
) : Parcelable {
}