package com.example.usptu_map.user_map_interface

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.usptu_map.databinding.ListItemRadioButtonBinding

class RadioAdapter(
    private val context: Context,
    private val items: List<String>
) : ArrayAdapter<String>(context, 0, items) {
    private lateinit var binding: ListItemRadioButtonBinding

    // Внешне управляемая переменная для отслеживания выбранной позиции
    var selectedPosition: Int = -1

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        if (convertView == null) {
            binding = ListItemRadioButtonBinding.inflate(LayoutInflater.from(context), parent, false)
        } else {
            binding = ListItemRadioButtonBinding.bind(convertView)
        }

        // Устанавливаем текст радиокнопки
        binding.radioButtonOption.text = items[position]

        // Отмечаем радиокнопку, если текущая позиция соответствует выбранной
        binding.radioButtonOption.isChecked = position == selectedPosition

        return binding.root
    }
}