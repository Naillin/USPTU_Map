package com.example.usptu_map.user_map_interface

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Base64
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import com.example.usptu_map.R
import com.example.usptu_map.SomeTools
import com.example.usptu_map.databinding.ActivityMainBinding
import com.example.usptu_map.map_operations.MapBordersHolder
import com.example.usptu_map.map_operations.MapOprations
import com.example.usptu_map.map_operations.RouteFactory
import com.example.usptu_map.map_operations.UserLocation
import com.example.usptu_map.map_operations.UserLocationUpdateListener
import com.example.usptu_map.project_objects.ParcelingTools.toParcelable
import com.example.usptu_map.project_objects.base_entities.Building
import com.example.usptu_map.project_objects.coordinates.MapPoints
import com.example.usptu_map.system.ConstantsProject
import com.example.usptu_map.system.ConstantsProject.INTENT_KEY1
import com.example.usptu_map.system.ConstantsProject.INTENT_KEY2
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.transport.TransportFactory
import com.yandex.mapkit.transport.masstransit.Session
import com.yandex.runtime.image.ImageProvider

class MainActivity : AppCompatActivity(), UserLocationUpdateListener {
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

        bindingMainActivity = ActivityMainBinding.inflate(layoutInflater).apply {
            mapOprations = MapOprations(this)
            mapBordersHolder = MapBordersHolder(this.mapViewMain)
            mapBorders = mapBordersHolder.getMapBorders()
            routeFactory = RouteFactory(this.mapViewMain)
            userLocation = UserLocation(this.mapViewMain, routeFactory)
        }

        setContentView(bindingMainActivity.root)

        mapOprations.apply {
            startPointMaps()
            polygonsOfMap()
            customPlacemarksOfMap()
        }

        initializationNavMenu()
        launchersPack()
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

        MapPoints.initialize(this@MainActivity)
    }

    /**
     * Проверка прав программы, запуск отслеживания местоположения пользователя
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
            userLocation.setLocationListener(this@MainActivity)
            userLocation.initUserLocation(ImageProvider.fromResource(
                bindingMainActivity.root.context,
                R.drawable.walkperson_placemark
            ))
        }
    }

    /**
     * Проверка прав программы, запуск отслеживания местоположения пользователя
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                //Разрешения предоставлены, начинаем отслеживание местоположения
                userLocation.setLocationListener(this@MainActivity)
                userLocation.initUserLocation(ImageProvider.fromResource(
                    bindingMainActivity.root.context,
                    R.drawable.walkperson_placemark
                ))
            } else {
                //Разрешения отклонены, показываем пользователю объяснение необходимости разрешений
            }
        }
    }

    /**
     * Уведомеление о статусе местоположения
     */
    override fun onLocationUpdated(location: Location) = with(bindingMainActivity) {
        runOnUiThread {
            cardViewStatus.setCardBackgroundColor(Color.GREEN)
            textViewStatus.text = getString(R.string.location_is_determined)
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

                    val intent = Intent(this@MainActivity, TwoPointsRouteActivity::class.java)
                    twoPointsActivityLauncher?.launch(intent)
                }
                R.id.itemMakeLessonRoute -> {
                    //ПОЛУЧИТЬ ИНФОРМАЦИЮ О ПАРАХ
                    routeFactory.removeAllRoutes()
                    //userLocation.routingEnabled = true
//                    userLocation.setEndPoint(MapPoints.academicBuildings[0].coordinates.toMapKitPoint())
//
//                    val wp = WebParsing("БПО-21-01", this@MainActivity);
//                    lifecycleScope.launch { wp.parseSchedule() }
//                    var lessons = wp.getSchedule()
//                    lessons = wp.getSchedule()
                }
                R.id.itemMakeRouteFood -> {
                    val building = userLocation.getNearestBuilding(MapPoints.universityCafe)!!
                    functionForBuildings(building.coordinates.toMapKitPoint())
                }
                R.id.itemMakeRouteProducts -> {
                    val building = userLocation.getNearestBuilding(MapPoints.universityProducts)!!
                    functionForBuildings(building.coordinates.toMapKitPoint())
                }
                R.id.itemMakeRouteRelax -> {
                    val building = userLocation.getNearestBuilding(MapPoints.universityChill)!!
                    functionForBuildings(building.coordinates.toMapKitPoint())
                }

                //ГРУППА - ЗДАНИЯ
                R.id.itemFirstCorpus -> {
                    functionForBuildings(MapPoints.academicBuildings[0].coordinates.toMapKitPoint())
                }
                R.id.itemSecondCorpus -> {
                    functionForBuildings(MapPoints.academicBuildings[1].coordinates.toMapKitPoint())
                }
                R.id.itemThirdCorpus -> {
                    functionForBuildings(MapPoints.academicBuildings[2].coordinates.toMapKitPoint())
                }
                R.id.itemFourthCorpus -> {
                    functionForBuildings(MapPoints.academicBuildings[3].coordinates.toMapKitPoint())
                }
                R.id.itemSevenCorpus -> {
                    functionForBuildings(MapPoints.academicBuildings[4].coordinates.toMapKitPoint())
                }
                R.id.ItemEightCorpus -> {
                    functionForBuildings(MapPoints.academicBuildings[5].coordinates.toMapKitPoint())
                }
                R.id.ItemElevenCorpus -> {
                    functionForBuildings(MapPoints.academicBuildings[6].coordinates.toMapKitPoint())
                }
                R.id.ItemUfkOneCorpus -> {
                    functionForBuildings(MapPoints.academicBuildings[7].coordinates.toMapKitPoint())
                }
                R.id.ItemUfkTwoCorpus -> {
                    functionForBuildings(MapPoints.academicBuildings[8].coordinates.toMapKitPoint())
                }

                //ГРУППА - ОБЩЕЖИТИЯ
                R.id.itemFirstDormitory -> {
                    functionForBuildings(MapPoints.universityDormitories[0].coordinates.toMapKitPoint())
                }
                R.id.itemSecondDormitory -> {
                    functionForBuildings(MapPoints.universityDormitories[1].coordinates.toMapKitPoint())
                }
                R.id.itemThirdDormitory -> {
                    functionForBuildings(MapPoints.universityDormitories[2].coordinates.toMapKitPoint())
                }
                R.id.itemFourthDormitory -> {
                    functionForBuildings(MapPoints.universityDormitories[3].coordinates.toMapKitPoint())
                }
                R.id.itemFiveDormitory -> {
                    functionForBuildings(MapPoints.universityDormitories[4].coordinates.toMapKitPoint())
                }
                R.id.itemSixDormitory -> {
                    functionForBuildings(MapPoints.universityDormitories[5].coordinates.toMapKitPoint())
                }
                R.id.ItemTenDormitory -> {
                    functionForBuildings(MapPoints.universityDormitories[6].coordinates.toMapKitPoint())
                }
            }

            drawerLayoutMain.closeDrawers()
            true
        }

        // Создаем Handler для главного потока
        Handler(Looper.getMainLooper()).postDelayed({
            // Проверяем, не уничтожена ли уже активность
            if (!isFinishing) {
                drawerLayoutMain.openDrawer(GravityCompat.START)
            }
        }, 1500) // Задержка в 2 секунды
    }

    private fun functionForBuildings(building: Point) {
        val from = userLocation.getUserLocation()
        if(from != null) {

            if(!userLocation.checkDistance(building, 1000F)) {
                routeFactory.removeAllRoutes()
                userLocation.routingEnabled = true
                userLocation.setEndPoint(building)
                userLocation.useExistingDataForRouting()

                bindingMainActivity.mapViewMain.map.move(
                    CameraPosition(
                        building,
                        /* zoom = */ 17.0f,
                        /* azimuth = */ 150.0f,
                        /* tilt = */ 30.0f
                    ),
                    Animation(Animation.Type.SMOOTH, 0.5F),
                    null
                )
            }
            else {
                SomeTools(this@MainActivity).createAlertDialogMultiActions(
                    getString(R.string.far_from_the_university_title),
                    getString(R.string.far_from_the_university_message),
                    SomeTools.AlertDialogAction("2GIS") { _, _ ->
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse("dgis://2gis.ru/routeSearch/rsType/ctx/from/${from.position.longitude},${from.position.latitude}/to/${building.longitude},${building.latitude}")
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
            SomeTools(this@MainActivity).showToast(getString(R.string.location_is_not_determined))
        }
    }

    /**
     * Комплект лаунчеров текущего Activity
     */
    private var twoPointsActivityLauncher: ActivityResultLauncher<Intent>? = null
    private fun launchersPack() {
        //возвращение ответа из 2PointsActivity activity
        twoPointsActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == RESULT_OK) {
                // Получение данных из интента
                val data: Intent? = it.data
                val value1String  = data?.getStringExtra(INTENT_KEY1)
                val value2String  = data?.getStringExtra(INTENT_KEY2)

                if (value1String != null && value2String != null) {
                    val value1ByteArray = Base64.decode(value1String, Base64.DEFAULT)
                    val value2ByteArray = Base64.decode(value2String, Base64.DEFAULT)

                    val value1 = value1ByteArray.toParcelable(Building.CREATOR)
                    val value2 = value2ByteArray.toParcelable(Building.CREATOR)

                    if (value1 != null && value2 != null) {
                        routeFactory.requestRoute2Points(value1.coordinates.toMapKitPoint(), value2.coordinates.toMapKitPoint()) {

                        }
                    }
                }
            }
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

        mapViewMain.mapWindow.map.addCameraListener(mapBorders)
    }

    override fun onPause() = with(bindingMainActivity) {
        mapViewMain.mapWindow.map.removeCameraListener(mapBorders)
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