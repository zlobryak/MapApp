package ru.netology.nmedia.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.netology.nmedia.databinding.FragmentPointsListBinding
import ru.netology.nmedia.ui.adapter.PointListener
import ru.netology.nmedia.ui.adapter.PointsAdapter
import ru.netology.nmedia.ui.viewmodel.PointViewModel
import ru.netology.nmedia.data.dto.Point as AppPoint

@AndroidEntryPoint
class PointsListFragment : Fragment(), PointListener {

    private val viewModel: PointViewModel by viewModels()
    private var _binding: FragmentPointsListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: PointsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPointsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализируем адаптер
        adapter = PointsAdapter(this)
        binding.pointsList.layoutManager = LinearLayoutManager(requireContext())
        binding.pointsList.adapter = adapter

        // Подписка на данные из ViewModel
        viewModel.data.observe(viewLifecycleOwner) { points ->
            adapter.submitList(points)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // === Реализация PointListener ===

    override fun onRemove(point: AppPoint) {
        viewModel.removeById(point.id)
    }

    override fun onEdit(point: AppPoint) {
        showEditPointDialog(point)
    }

    override fun onPointClick(point: AppPoint) {
        // Передаем координаты обратно в MapsFragment через Fragment Result API
        setFragmentResult(
            "pointSelected",
            bundleOf(
                "latitude" to point.latitude,
                "longitude" to point.longitude,
                "pointId" to point.id
            )
        )
        // Закрываем фрагмент списка
        parentFragmentManager.popBackStack()
    }

    // === Диалог редактирования ===

    private fun showEditPointDialog(point: AppPoint) {
        val nameInput = EditText(requireContext()).apply {
            hint = "Название точки"
            setText(point.name)
        }

        val descriptionInput = EditText(requireContext()).apply {
            hint = "Описание"
            setText(point.description)
        }

        val layout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(50, 30, 50, 30)
            addView(nameInput)
            addView(descriptionInput)
        }

        android.app.AlertDialog.Builder(requireContext())
            .setTitle("Редактировать точку")
            .setView(layout)
            .setPositiveButton("Сохранить") { dialog, _ ->
                val name = nameInput.text.toString()
                val description = descriptionInput.text.toString()

                if (name.isNotBlank()) {
                    val updatedPoint = AppPoint(
                        id = point.id,
                        name = name,
                        description = description,
                        latitude = point.latitude,
                        longitude = point.longitude,
                        isVisited = point.isVisited
                    )
                    viewModel.save(updatedPoint)
                }
                dialog.dismiss()
            }
            .setNegativeButton("Отмена") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}