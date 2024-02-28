package com.example.usptu_map

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.usptu_map.databinding.ActivityMainBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.LinearRing
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.geometry.Polygon
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.runtime.image.ImageProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var bindingMainActivity: ActivityMainBinding
    private lateinit var mapOprations: MapOprations
    private lateinit var locationListenerUser: LocationListener
    private lateinit var mapBorders: CameraListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializationMaps()

        bindingMainActivity = ActivityMainBinding.inflate(layoutInflater)
        mapOprations = MapOprations(bindingMainActivity)
        locationListenerUser = mapOprations.userPlacemark()
        mapBorders = mapOprations.getMapBorders()

        setContentView(bindingMainActivity.root)

        mapOprations.startPointMaps()
        mapOprations.polygonsOfMap()
        mapOprations.customPlacemarksOfMap()
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
        //MapKitFactory.getInstance().createLocationManager().requestSingleUpdate(locationListenerUser)
    }

    override fun onResume() = with(bindingMainActivity) {
        super.onResume()

        mapViewMain.mapWindow.map.addCameraListener(mapBorders)
    }

    override fun onPause() = with(bindingMainActivity) {
        mapViewMain.mapWindow.map.removeCameraListener(mapBorders)
        mapOprations.setDefaultLocation()

        super.onPause()
    }

    override fun onStop() = with(bindingMainActivity) {
        //MapKitFactory.getInstance().createLocationManager().unsubscribe(locationListenerUser)
        MapKitFactory.getInstance().onStop()
        mapViewMain.onStop()

        super.onStop()
    }
}