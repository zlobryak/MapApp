package ru.netology.nmedia.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import ru.netology.nmedia.data.dto.Point
import ru.netology.nmedia.data.repository.PointRepository
import javax.inject.Inject

@HiltViewModel
class PointViewModel @Inject constructor(
    private val repository: PointRepository
) : ViewModel() {

    val data: LiveData<List<Point>> = repository.data
        .catch { it.printStackTrace() }
        .asLiveData(Dispatchers.Default)

    fun removeById(id: Long) {
        viewModelScope.launch {
            repository.removeById(id)
        }
    }

    fun save(point: Point) {
        viewModelScope.launch {
            repository.save(point)
        }
    }
}