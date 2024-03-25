package com.example.usptu_map.user_map_interface

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.usptu_map.R
import com.example.usptu_map.databinding.ActivityMainBinding
import com.example.usptu_map.map_operations.MapBordersHolder
import com.example.usptu_map.map_operations.MapOprations
import com.example.usptu_map.map_operations.RouteFactory
import com.example.usptu_map.map_operations.UserLocation
import com.example.usptu_map.project_objects.base_entities.coordinates.MapPoints.academicBuildings
import com.example.usptu_map.system.ConstantsProject
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.transport.TransportFactory
import com.yandex.mapkit.transport.masstransit.Session
import com.yandex.runtime.image.ImageProvider

class MainActivity : AppCompatActivity() {
    private lateinit var bindingMainActivity: ActivityMainBinding
    private lateinit var mapOprations: MapOprations
    private lateinit var mapBordersHolder: MapBordersHolder
    private lateinit var mapBorders: CameraListener
    private lateinit var routeFactory: RouteFactory
    private lateinit var userLocation: UserLocation

    //Сессия маршрутов
    private var pedestrianSession: Session? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializationMaps()

        bindingMainActivity = ActivityMainBinding.inflate(layoutInflater)
        mapOprations = MapOprations(bindingMainActivity)
        mapBordersHolder = MapBordersHolder(bindingMainActivity.mapViewMain)
        mapBorders = mapBordersHolder.getMapBorders()
        routeFactory = RouteFactory(bindingMainActivity.mapViewMain)
        userLocation = UserLocation(bindingMainActivity.mapViewMain, routeFactory)

        setContentView(bindingMainActivity.root)

        mapOprations.startPointMaps()
        mapOprations.polygonsOfMap()
        mapOprations.customPlacemarksOfMap()

        initializationNavMenu()
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
            userLocation.initUserLocation(ImageProvider.fromResource(
                bindingMainActivity.root.context,
                R.drawable.heart
            ))
        }
    }

    //Проверка прав
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                //Разрешения предоставлены, начинаем отслеживание местоположения
                userLocation.initUserLocation(ImageProvider.fromResource(
                    bindingMainActivity.root.context,
                    R.drawable.heart
                ))
            } else {
                //Разрешения отклонены, показываем пользователю объяснение необходимости разрешений
            }
        }
    }

    private fun initializationNavMenu() = with(bindingMainActivity) {
        navViewMain.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.itemMakeRoute -> {
                    routeFactory.removeAllRoutes()
                    userLocation.routingEnabled = false

                }
                R.id.itemMakeLessonRoute -> {

                    //ПОЛУЧИТЬ ИНФОРМАЦИЮ О ПАРАХ
                    routeFactory.removeAllRoutes()
                    userLocation.routingEnabled = true
                    userLocation.setEndPoint(academicBuildings[0].coordinates.toMapKitPoint())


                }
                R.id.itemFirstCorpus -> {
                    routeFactory.removeAllRoutes()
                    userLocation.routingEnabled = true
                    userLocation.setEndPoint(academicBuildings[0].coordinates.toMapKitPoint())
                }
                R.id.itemSecondCorpus -> {
                    routeFactory.removeAllRoutes()
                    userLocation.routingEnabled = true
                    userLocation.setEndPoint(academicBuildings[1].coordinates.toMapKitPoint())
                }
                R.id.itemThirdCorpus -> {
                    routeFactory.removeAllRoutes()
                    userLocation.routingEnabled = true
                    userLocation.setEndPoint(academicBuildings[2].coordinates.toMapKitPoint())
                }
                R.id.itemFourthCorpus -> {
                    routeFactory.removeAllRoutes()
                    userLocation.routingEnabled = true
                    userLocation.setEndPoint(academicBuildings[3].coordinates.toMapKitPoint())
                }
            }
            true
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
        mapBordersHolder.setDefaultLocation()

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