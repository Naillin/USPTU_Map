package com.example.usptu_map

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.usptu_map.databinding.ActivityMainBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.LinearRing
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.geometry.Polygon
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.runtime.image.ImageProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var bindingMainActivity: ActivityMainBinding
    private lateinit var mapOprations: MapOprations

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializationMaps()

        bindingMainActivity = ActivityMainBinding.inflate(layoutInflater)
        mapOprations = MapOprations(bindingMainActivity)
        setContentView(bindingMainActivity.root)

        mapOprations.startPointMaps()
        mapOprations.polygonsOfMap()
        mapOprations.customPlacemarksOfMap()
        //Вызов метода с задержкой
        lifecycleScope.launch {
            delay(1000) // Задержка в миллисекундах (1000 мс = 1 секунда)
            mapOprations.mapBorders()
        }
        bindingMainActivity.viewBlockMap.visibility = View.GONE
        initializationBottomMenu()

        WebParsing().getParseData() // не робит
    }

    private fun initializationMaps() {
        MapKitFactory.setApiKey(ConstantsProject.API_KEY_YANDEX_MAPS)
        MapKitFactory.initialize(this@MainActivity)
    }



    private fun initializationBottomMenu() = with(bindingMainActivity) {
        bottomNavMain.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.itemMakeRoute -> {
                    // Обработка выбора "Создать маршрут"
                }
                R.id.itemDeleteRoute -> {
                    // Обработка выбора "Удалить маршрут"
                }
            }
            true // Возвращаем true, чтобы отобразить выбранный элемент как выбранный
        }
    }


    override fun onStart() = with(bindingMainActivity) {
        super.onStart()

        mapViewMain.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onStop() = with(bindingMainActivity) {
        MapKitFactory.getInstance().onStop()
        mapViewMain.onStop()

        super.onStop()
    }
}