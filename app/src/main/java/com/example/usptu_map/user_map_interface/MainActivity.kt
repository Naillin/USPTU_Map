package com.example.usptu_map.user_map_interface

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.usptu_map.R
import com.example.usptu_map.SomeTools
import com.example.usptu_map.databinding.ActivityMainBinding
import com.example.usptu_map.map_operations.MapBordersHolder
import com.example.usptu_map.map_operations.MapOprations
import com.example.usptu_map.map_operations.RouteFactory
import com.example.usptu_map.map_operations.UserLocation
import com.example.usptu_map.project_objects.coordinates.MapPoints.academicBuildings
import com.example.usptu_map.system.ConstantsProject
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
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

    /**
     * Установка API ключа YandexMapKit
     *
     * Запуск систем карты
     *
     * Запуск систем построений маршрутов
     */
    private fun initializationMaps() {
        MapKitFactory.setApiKey(ConstantsProject.API_KEY_YANDEX_MAPS)
        MapKitFactory.initialize(this@MainActivity)
        TransportFactory.initialize(this@MainActivity)
    }

    /**
     * Проверка прав программы
     */
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

    /**
     * Проверка прав программы
     */
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

    /**
     * Инициализация NaviagationView
     */
    private fun initializationNavMenu() = with(bindingMainActivity) {
        navViewMain.setNavigationItemSelectedListener {
            when(it.itemId) {
                //ГРУППА - МАРШРУТЫ
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

                //ГРУППА - ЗДАНИЯ
                R.id.itemFirstCorpus -> {
                    functionForBuildings(academicBuildings[0].coordinates.toMapKitPoint())
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

    private fun functionForBuildings(building: Point) {
        if(userLocation.getUserLocation() != null) {
            if(!userLocation.checkDistance(building, 1000F)) {
                routeFactory.removeAllRoutes()
                userLocation.routingEnabled = true
                userLocation.setEndPoint(building)
            }
            else {
                SomeTools(this@MainActivity).createAlertDialogMultiActions(
                    "Далековато...",
                    "Кажется вы очень далеко от студенческого городка.\nМы можем предложить вам заказать такси или проложить маршрут с помощью 2ГИС до точки?",
                    SomeTools.AlertDialogAction("2GIS") { _, _ ->
                        val from = userLocation.getUserLocation()
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse("dgis://2gis.ru/routeSearch/rsType/ctx/from/${from!!.position.longitude},${from.position.latitude}/to/${building.longitude},${building.latitude}")
                            setPackage("ru.dublgis.dgismobile")
                        }

                        if (intent.resolveActivity(packageManager) != null) {
                            startActivity(intent)
                        } else {
                            // Приложение 2GIS не установлено, можно перенаправить пользователя в Google Play
                            //startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=ru.dublgis.dgismobile")))

                            // Перенаправление пользователя на страницу загрузки приложения 2GIS
                            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://apk.2gis.ru/")))
                        }
                    },
                    SomeTools.AlertDialogAction("YandexTaxi") { _, _ ->
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse("yandextaxi://route?end-lat=${building.latitude}&end-lon=${building.longitude}")
                            setPackage("ru.yandex.taxi")
                        }

                        if (intent.resolveActivity(packageManager) != null) {
                            startActivity(intent)
                        } else {
                            //Приложение Yandex Taxi не установлено, можно перенаправить пользователя в Google Play
                            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=ru.yandex.taxi")))
                        }
                    },
                    SomeTools.AlertDialogAction("Nothing") { _, _ ->

                    }
                )
            }
        }
        else {
            SomeTools(this@MainActivity).showToast("Местоположение не определено...")
        }
    }

    /**
     * Описание методов жизненого цикла Activity
     */
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