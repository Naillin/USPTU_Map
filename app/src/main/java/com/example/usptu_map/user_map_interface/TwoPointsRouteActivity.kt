package com.example.usptu_map.user_map_interface

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.usptu_map.databinding.ActivityTwoPointsRouteBinding
import com.example.usptu_map.project_objects.coordinates.MapPoints.combinedBuildingList
import com.example.usptu_map.system.ConstantsProject.INTENT_KEY1
import com.example.usptu_map.system.ConstantsProject.INTENT_KEY2

class TwoPointsRouteActivity : AppCompatActivity() {
    private lateinit var bindingActivityTwoPointsRoute: ActivityTwoPointsRouteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingActivityTwoPointsRoute = ActivityTwoPointsRouteBinding.inflate(layoutInflater)
        setContentView(bindingActivityTwoPointsRoute.root)

        init()
    }

    private lateinit var adapterLeft: RadioAdapter
    private lateinit var adapterRight: RadioAdapter
    private var selectedItemLeft = -1
    private var selectedItemRight = -1
    private fun init() = with(bindingActivityTwoPointsRoute) {
        // Получаем массив строк из ресурсов
        //val options = resources.getStringArray(R.array.buildings)
        val options: ArrayList<String> = arrayListOf()
        combinedBuildingList.forEach { options.add(it.name.toString()) }

        //Инициализируем адаптер
        adapterLeft = RadioAdapter(this@TwoPointsRouteActivity, options.toList())
        adapterRight = RadioAdapter(this@TwoPointsRouteActivity, options.toList())

        //Находим ListView и устанавливаем для него адаптер
        listViewLeft.adapter = adapterLeft
        listViewRight.adapter = adapterRight

        // Устанавливаем слушателя для обработки выбора элементов - нихуя не работает (посмотри с помощью точки останова)
        listViewLeft.setOnItemClickListener { parent, view, position, id ->
            adapterLeft.selectedPosition = position
            adapterLeft.notifyDataSetChanged()

            // Получаем выбранный элемент
            selectedItemLeft = position
            if(selectedItemLeft != -1 && selectedItemRight != -1) buttonAccept.isEnabled = true
        }
        listViewRight.setOnItemClickListener { parent, view, position, id ->
            adapterRight.selectedPosition = position
            adapterRight.notifyDataSetChanged()

            // Получаем выбранный элемент
            selectedItemRight = position
            if(selectedItemLeft != -1 && selectedItemRight != -1) buttonAccept.isEnabled = true
        }
    }

    fun buttonAcceptOnClick(view: View) {
        val returnIntent = Intent().apply {
            putExtra(INTENT_KEY1, combinedBuildingList[selectedItemLeft])
            putExtra(INTENT_KEY2, combinedBuildingList[selectedItemRight])
        }

        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }
}