package ru.netology.nmedia.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.data.dto.Point as AppPoint
import ru.netology.nmedia.databinding.ItemPointBinding

class PointsAdapter(
    private val listener: PointListener
) : ListAdapter<AppPoint, PointsAdapter.PointViewHolder>(PointDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointViewHolder {
        val binding = ItemPointBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PointViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: PointViewHolder, position: Int) {
        val point = getItem(position)
        holder.bind(point)
    }

    class PointViewHolder(
        private val binding: ItemPointBinding,
        private val listener: PointListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(point: AppPoint) {
            // Заполняем название и описание
            binding.pointName.text = point.name
            binding.pointDescription.text = point.description


            // Клик по всему элементу — переход к точке на карте
            binding.root.setOnClickListener {
                listener.onPointClick(point)
            }


            // Клик по кнопке удаления
            binding.deleteButton.setOnClickListener {
                listener.onRemove(point)
            }


            // Меняем иконку кнопки в зависимости от статуса
            binding.toggleVisitedButton.setImageResource(
                if (point.isVisited) {
                    ru.netology.nmedia.R.drawable.ic_check_circle_24dp // Заполненная галочка
                } else {
                    ru.netology.nmedia.R.drawable.ic_check_circle_24dp// Пустая галочка
                }
            )

            // Меняем цвет кнопки в зависимости от статуса
            val color = if (point.isVisited) {
                android.graphics.Color.parseColor("#4CAF50") // Зеленый
            } else {
                android.graphics.Color.GRAY
            }
            binding.toggleVisitedButton.setColorFilter(color)

            // Клик по всему элементу — переход к точке на карте
            binding.root.setOnClickListener {
                listener.onPointClick(point)
            }

            // Клик по кнопке переключения посещенности
            binding.toggleVisitedButton.setOnClickListener {
                listener.onToggleVisited(point)
            }

            // Клик по кнопке редактирования
            binding.editButton.setOnClickListener {
                listener.onEdit(point)
            }

            // Клик по кнопке удаления
            binding.deleteButton.setOnClickListener {
                listener.onRemove(point)
            }

        }
    }

    class PointDiffCallback : DiffUtil.ItemCallback<AppPoint>() {
        override fun areItemsTheSame(oldItem: AppPoint, newItem: AppPoint): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AppPoint, newItem: AppPoint): Boolean {
            return oldItem == newItem
        }
    }
}

interface PointListener {
    fun onRemove(point: AppPoint)
    fun onEdit(point: AppPoint)
    fun onPointClick(point: AppPoint)
    fun onToggleVisited(point: AppPoint)
}
