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
    //Генерируемый класс биндинга для вашего макета элемента списка
    private lateinit var binding: ListItemRadioButtonBinding

    //Позиция выбранной радио кнопки
    var selectedItemPosition: Int = -1

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //Использование View Binding для инфлейта вашего макета
        if (convertView == null) {
            //Создаем новый инстанс биндинга для нового элемента списка
            binding = ListItemRadioButtonBinding.inflate(LayoutInflater.from(context), parent, false)
        } else {
            //Повторное использование существующего биндинга
            binding = ListItemRadioButtonBinding.bind(convertView)
        }

        binding.radioButtonOption.text = items[position]
        binding.radioButtonOption.isChecked = position == selectedItemPosition

        // Обработчик выбора радио кнопки
        binding.radioButtonOption.setOnClickListener {
            selectedItemPosition = position
            notifyDataSetChanged() //Обновите адаптер для перерисовки элементов списка
        }

        return binding.root
    }
}