package com.example.usptu_map

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.usptu_map.databinding.ActivityMainBinding
import com.example.usptu_map.map_operations.MapOperationsTools
import com.example.usptu_map.map_operations.MapOprations
import com.example.usptu_map.map_operations.UserLocation
import com.example.usptu_map.project_objects.ConstantsProject
import com.example.usptu_map.project_objects.Сoordinates
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.transport.TransportFactory
import com.yandex.mapkit.transport.masstransit.Session

class MainActivity : AppCompatActivity() {
    private lateinit var bindingMainActivity: ActivityMainBinding
    private lateinit var mapOprations: MapOprations
    private lateinit var mapOperationsTools: MapOperationsTools
    private lateinit var mapBorders: CameraListener
    private lateinit var userLocation: UserLocation

    //Сессия маршрутов
    private var pedestrianSession: Session? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializationMaps()

        bindingMainActivity = ActivityMainBinding.inflate(layoutInflater)
        mapOprations = MapOprations(bindingMainActivity)
        mapOperationsTools = MapOperationsTools(bindingMainActivity.mapViewMain)
        mapBorders = mapOprations.getMapBorders()
        userLocation = UserLocation(bindingMainActivity, mapOprations)

        setContentView(bindingMainActivity.root)

        mapOprations.startPointMaps()
        mapOprations.polygonsOfMap()
        mapOprations.customPlacemarksOfMap()
        bindingMainActivity.viewBlockMap.visibility = View.GONE
        initializationBottomMenu()

        //WebParsing().getParseData() // не робит
    }

    //Запуск карты
    private fun initializationMaps() {
        MapKitFactory.setApiKey(ConstantsProject.API_KEY_YANDEX_MAPS)
        MapKitFactory.initialize(this@MainActivity)
        TransportFactory.initialize(this@MainActivity)
    }

    //Проверка прав
    private fun checkAndRequestLocationPermissions() {
        if (ContextCompat.checkSelfPermission(this@MainActivity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(this@MainActivity, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION),
                1
            )
        } else {
            //Разрешения уже предоставлены, можно начинать отслеживание местоположения
            userLocation.initUserLocation()
        }
    }

    //Проверка прав
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                //Разрешения предоставлены, начинаем отслеживание местоположения
                userLocation.initUserLocation()
            } else {
                //Разрешения отклонены, показываем пользователю объяснение необходимости разрешений
            }
        }
    }

    //Запуск нижнего меню
    private fun initializationBottomMenu() = with(bindingMainActivity) {
        bottomNavMain.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.itemMakeRoute -> {
                    // Обработка выбора "Создать маршрут"
                    userLocation.endPoint = Сoordinates.CORPUSES[2]
                    userLocation.routingEnabled = true

                    pedestrianSession = userLocation.routingSession
                }
                R.id.itemDeleteRoute -> {
                    userLocation.routingEnabled = false

                    pedestrianSession = mapOprations.requestRoute2Points(Сoordinates.CORPUSES[1], Сoordinates.CORPUSES[8])
                }
            }
            true // Возвращаем true, чтобы отобразить выбранный элемент как выбранный
        }
    }

    override fun onStart() = with(bindingMainActivity) {
        super.onStart()

        mapViewMain.onStart()
        MapKitFactory.getInstance().onStart()
        checkAndRequestLocationPermissions()
    }

    override fun onResume() = with(bindingMainActivity) {
        super.onResume()

        //mapViewMain.mapWindow.map.addCameraListener(mapBorders)
    }

    override fun onPause() = with(bindingMainActivity) {
        //mapViewMain.mapWindow.map.removeCameraListener(mapBorders)
        mapOprations.setDefaultLocation()

        super.onPause()
    }

    override fun onStop() = with(bindingMainActivity) {
        MapKitFactory.getInstance().onStop()
        mapViewMain.onStop()

        super.onStop()
    }

    override fun onDestroy() {
        pedestrianSession?.cancel()

        super.onDestroy()
    }
}