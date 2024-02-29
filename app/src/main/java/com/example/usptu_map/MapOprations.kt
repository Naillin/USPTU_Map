package com.example.usptu_map

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.usptu_map.databinding.ActivityMainBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.geometry.BoundingBox
import com.yandex.mapkit.geometry.LinearRing
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.geometry.Polygon
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationManager
import com.yandex.mapkit.location.LocationStatus
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.CameraUpdateReason
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.runtime.image.ImageProvider
import java.io.IOException

class MapOprations(private val binding: ActivityMainBinding) {
    val mapOperationsTools: MapOperationsTools = MapOperationsTools(binding.mapViewMain)

    private var cameraPositionMain = CameraPosition(
        Сoordinates.CENTER_USPTU_CITY_COORD,
        /* zoom = */ 17.0f,
        /* azimuth = */ 150.0f,
        /* tilt = */ 30.0f
    )

    fun getCameraPosition(): CameraPosition = with(binding) {
        return mapViewMain.map.cameraPosition
    }

    fun cameraReady(): Boolean = with(binding) {
        // Проверка, достигла ли камера начальной позиции
        Math.abs(mapViewMain.map.cameraPosition.target.latitude - Сoordinates.CENTER_USPTU_CITY_COORD.latitude) < 0.0001 &&
                Math.abs(mapViewMain.map.cameraPosition.target.longitude - Сoordinates.CENTER_USPTU_CITY_COORD.longitude) < 0.0001
    }

    fun startPointMaps() = with(binding) {
        viewBlockMap.visibility = android.view.View.VISIBLE
        mapViewMain.map.isZoomGesturesEnabled = false

        mapViewMain.map.move(
            cameraPositionMain,
            Animation(com.yandex.mapkit.Animation.Type.SMOOTH, 1F),
            null
        )
    }

    fun customPlacemarksOfMap() {
        mapOperationsTools.addPlacemarkOnMap("", Сoordinates.CORPUSES[0], R.drawable.heart) //заменять текст иконками в которые интегрирован текст
        mapOperationsTools.addPlacemarkOnMap("", Сoordinates.CORPUSES[1], R.drawable.heart)
        mapOperationsTools.addPlacemarkOnMap("", Сoordinates.CORPUSES[2], R.drawable.heart)
        mapOperationsTools.addPlacemarkOnMap("", Сoordinates.CORPUSES[3], R.drawable.heart)
        mapOperationsTools.addPlacemarkOnMap("", Сoordinates.CORPUSES[4], R.drawable.heart)
        mapOperationsTools.addPlacemarkOnMap("", Сoordinates.CORPUSES[5], R.drawable.heart)
        mapOperationsTools.addPlacemarkOnMap("", Сoordinates.CORPUSES[6], R.drawable.heart)
        mapOperationsTools.addPlacemarkOnMap("", Сoordinates.CORPUSES[7], R.drawable.heart)
        mapOperationsTools.addPlacemarkOnMap("", Сoordinates.CORPUSES[8], R.drawable.heart)

    }

    fun polygonsOfMap() = with(binding) {
        // Создание точек вокруг центральной точки для полигона
        val firstCorpusArea = listOf(
            Point(com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.latitude - 0.0002, com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.longitude + 0.0013),//ВЛ
            Point(com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.latitude - 0.0004, com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.longitude - 0.00087),//ВП
            Point(com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.latitude + 0.0015, com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.longitude - 0.0014),//НП
            Point(com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.latitude + 0.0023, com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.longitude - 0.00082),//КНП
            Point(com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.latitude + 0.0018, com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.longitude + 0.001),//КНЛ
            Point(com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.latitude + 0.0015, com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.longitude + 0.00085),//НЛ

        )
        mapOperationsTools.addPolygonOnMap(firstCorpusArea, R.color.light_blue)

        val secondCorpusArea = listOf(
            Point(com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.latitude - 0.0006, com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.longitude + 0.0013),//НЛ
            Point(com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.latitude - 0.00077, com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.longitude - 0.00078),//НП
            Point(com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.latitude - 0.0042, com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.longitude + 0.00029),//ВП
            Point(com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.latitude - 0.004, com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.longitude + 0.0024),//ВЛ
        )
        mapOperationsTools.addPolygonOnMap(secondCorpusArea, R.color.light_blue)

        val thirdCorpusArea = listOf(
            Point(com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.latitude - 0.00036, com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.longitude + 0.0038),//НЛ
            Point(com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.latitude - 0.00056, com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.longitude + 0.0015),//НП
            Point(com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.latitude - 0.004, com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.longitude + 0.00254),//ВП
            Point(com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.latitude - 0.0043, com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.longitude + 0.0032),//ВВ
            Point(com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.latitude - 0.002, com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.longitude + 0.0044),//ВЛ
        )
        mapOperationsTools.addPolygonOnMap(thirdCorpusArea, R.color.light_blue)

        val fourthCorpusArea = listOf(
            Point(54.818900,56.068718),
            Point(54.819151,56.068765),
            Point(54.819476,56.069035),
            Point(54.819636,56.069379),
            Point(54.820350,56.068614),
            Point(54.820072,56.067826),
            Point(54.819384,56.067176),
            Point(54.818771,56.067238)
        )
        mapOperationsTools.addPolygonOnMap(fourthCorpusArea, R.color.light_blue)

        val firstCorpus=listOf(
            Point(54.818224,56.059652),
            Point(54.819021,56.059402),
            Point(54.818986,56.059074),
            Point(54.818744,56.059124),
            Point(54.818682,56.058606),
            Point(54.818926,56.058535),
            Point(54.818898,56.058273),
            Point(54.818663,56.058338),
            Point(54.818611,56.057800),
            Point(54.818830,56.057758),
            Point(54.818815,56.057387),
            Point(54.818022,56.057648),
            Point(54.818057,56.057965),
            Point(54.818403,56.057898),
            Point(54.818464,56.058242),
            Point(54.818397,56.058285),
            Point(54.818450,56.058825),
            Point(54.818503,56.058851),
            Point(54.818544,56.059201),
            Point(54.818188,56.059308),
        )
        mapOperationsTools.addPolygonOnMap(firstCorpus, R.color.light_red)

        val elevenCorpus=listOf(
            Point(54.817590,56.058458),
            Point(54.817231,56.058566),
            Point(54.817303,56.059201),
            Point(54.817644,56.059119),
        )
        mapOperationsTools.addPolygonOnMap(elevenCorpus, R.color.light_red)

        val ufkOneCorpus=listOf(
            Point(54.816173,56.059500),
            Point(54.816259,56.060321),
            Point(54.815719,56.060495),
            Point(54.815679,56.060119),
            Point(54.815984,56.060004),
            Point(54.815942,56.059563),
        )
        mapOperationsTools.addPolygonOnMap(ufkOneCorpus, R.color.light_red)

        val ufkTwoCorpus=listOf(
            Point(54.815591,56.060042),
            Point(54.815640,56.060481),
            Point(54.814709,56.060749),
            Point(54.814642,56.060276),
        )
        mapOperationsTools.addPolygonOnMap(ufkTwoCorpus, R.color.light_red)

        val thirdCorpus=listOf(
            Point(54.817786,56.060239),
            Point(54.817561,56.060290),
            Point(54.817738,56.062259),
            Point(54.817981,56.062191),
        )
        mapOperationsTools.addPolygonOnMap(thirdCorpus, R.color.light_red)

        val secondCorpus=listOf(
            Point(54.817566,56.061141),
            Point(54.816721,56.061421),
            Point(54.816766,56.061843),
            Point(54.817645,56.061587),
        )
        mapOperationsTools.addPolygonOnMap(secondCorpus, R.color.light_red)

        val fourthCorpus=listOf(
            Point(54.816978,56.062645),
            Point(54.816940,56.062178),
            Point(54.816221,56.062385),
            Point(54.816273,56.062903),
        )
        mapOperationsTools.addPolygonOnMap(fourthCorpus, R.color.light_red)

        val sevenCorpus=listOf(
            Point(54.820059,56.059564),
            Point(54.820367,56.058515),
            Point(54.819966,56.058142),
            Point(54.819875,56.058474),
            Point(54.820086,56.058669),
            Point(54.819892,56.059403),
        )
        mapOperationsTools.addPolygonOnMap(sevenCorpus, R.color.light_red)

        val eighthCorpus=listOf(
            Point(54.818980,56.068649),
            Point(54.819630,56.069197),
            Point(54.819836,56.069030),
            Point(54.819697,56.068265),
            Point(54.819801,56.067865),
            Point(54.819506,56.067586),
            Point(54.819371,56.068070),
            Point(54.818943,56.068289),
        )
        mapOperationsTools.addPolygonOnMap(eighthCorpus, R.color.light_red)

        val firstDormitory=listOf(
            Point(54.817528,56.057828),
            Point(54.817590,56.058395),
            Point(54.817407,56.058453),
            Point(54.817383,56.058303),
            Point(54.816959,56.058431),
            Point(54.816924,56.057979),
        )
        mapOperationsTools.addPolygonOnMap(firstDormitory, R.color.light_red)

        val secondDormitory=listOf(
            Point(54.817656,56.059169),
            Point(54.817489,56.059232),
            Point(54.817498,56.059400),
            Point(54.817069,56.059513),
            Point(54.817122,56.059988),
            Point(54.817720,56.059780),
        )
        mapOperationsTools.addPolygonOnMap(secondDormitory, R.color.light_red)

        val thirdDormitory=listOf(
            Point(54.817420,56.060894),
            Point(54.817392,56.060592),
            Point(54.816694,56.060786),
            Point(54.816722,56.061088),
        )
        mapOperationsTools.addPolygonOnMap(thirdDormitory, R.color.light_red)

        val fourthDormitory=listOf(
            Point(54.816672,56.060587),
            Point(54.815964,56.060787),
            Point(54.816011,56.061080),
            Point(54.816698,56.060891),
        )
        mapOperationsTools.addPolygonOnMap(fourthDormitory, R.color.light_red)

        val fiveDormitory=listOf(
            Point(54.815537,56.061495),
            Point(54.814867,56.061708),
            Point(54.814884,56.061953),
            Point(54.815567,56.061757),
        )
        mapOperationsTools.addPolygonOnMap(fiveDormitory, R.color.light_red)

        val sixDormitory=listOf(
            Point(54.816220,56.061898),
            Point(54.816245,56.062201),
            Point(54.815489,56.062407),
            Point(54.815443,56.061936),
            Point(54.815597,56.061903),
            Point(54.815616,56.062068),
        )
        mapOperationsTools.addPolygonOnMap(sixDormitory, R.color.light_red)

        val tenDormitory=listOf(
            Point(54.814870,56.061366),
            Point(54.814916,56.061798),
            Point(54.814698,56.061884),
            Point(54.814655,56.061421),
        )
        mapOperationsTools.addPolygonOnMap(tenDormitory, R.color.light_red)

        val dinningRoom=listOf(
            Point(54.817152,56.058523),
            Point(54.816740,56.058643),
            Point(54.816845,56.059514),
            Point(54.817239,56.059391),
        )
        mapOperationsTools.addPolygonOnMap(dinningRoom, R.color.light_red)
    }


//    fun mapBorders() = with(binding) {
//        val allowedAreaRadius = 0.0135
//        var isRecovering = false // Флаг для отслеживания процесса восстановления
//
//        val cameraListener = CameraListener { map, cameraPosition, _, finished ->
//            val currentPoint = cameraPosition.target
//            val distance = Math.sqrt(
//                Math.pow(currentPoint.latitude - Сoordinates.CENTER_USPTU_CITY_COORD.latitude, 2.0) +
//                        Math.pow(currentPoint.longitude - Сoordinates.CENTER_USPTU_CITY_COORD.longitude, 2.0)
//            )
//            Log.d("MyTag", "distance = $distance")
//
//            // Включаем блокировку взаимодействия с картой, если вышли за пределы и не в процессе восстановления
//            if (distance > allowedAreaRadius) {
//                viewBlockMap.visibility = View.VISIBLE
//                if(!isRecovering)
//                {
//                    isRecovering = true // Помечаем начало процесса восстановления
//                    map.move(
//                        cameraPositionMain,
//                        Animation(Animation.Type.LINEAR, 0.5F),
//                        null
//                    )
//                    Log.d("MyTag", "MapView is being recovered")
//                }
//                Log.d("MyTag", "MapView is off")
//            }
//
//            // Как только анимация завершена, проверяем, нужно ли скрыть блокировку
//            if (finished) { // попробовать сделать проверку по координатам, если координаты начальной точки, то отдавать контроль
//                viewBlockMap.visibility = View.GONE
//                isRecovering = false // Помечаем завершение процесса восстановления
//            }
//        }
//
//        mapViewMain.mapWindow.map.addCameraListener(cameraListener)
//    }

//    fun getMapBorders(): CameraListener = with(binding) {
//        val allowedAreaRadius = 0.0135
//
//        val cameraListener = CameraListener { map, cameraPosition, _, finished ->
//            val currentPoint = cameraPosition.target
//            val distance = Math.sqrt(
//                Math.pow(currentPoint.latitude - Сoordinates.CENTER_USPTU_CITY_COORD.latitude, 2.0) +
//                        Math.pow(currentPoint.longitude - Сoordinates.CENTER_USPTU_CITY_COORD.longitude, 2.0)
//            )
//            Log.d("MyTag", "distance = $distance")
//
//            // Проверка, достигла ли камера начальной позиции
//            val isAtStartPoint = Math.abs(currentPoint.latitude - Сoordinates.CENTER_USPTU_CITY_COORD.latitude) < 0.0001 &&
//                    Math.abs(currentPoint.longitude - Сoordinates.CENTER_USPTU_CITY_COORD.longitude) < 0.0001
//            if (distance > allowedAreaRadius) {
//                mapViewMain.clearAnimation()
//                viewBlockMap.visibility = View.VISIBLE
//                map.move(
//                    cameraPositionMain,
//                    Animation(Animation.Type.LINEAR, 0.1F),
//                    null
//                )
//                Log.d("MyTag", "MapView is being recovered")
//            } else if (isAtStartPoint) {
//                viewBlockMap.visibility = View.GONE
//                Log.d("MyTag", "MapView recovery finished, user can move the map again")
//            }
//        }
//
//        //mapViewMain.mapWindow.map.addCameraListener(cameraListener)
//        return cameraListener
//    }

    fun isPointInsideBoundingBox(point: Point, southWest: Point, northEast: Point): Boolean {
        return point.latitude in southWest.latitude..northEast.latitude &&
                point.longitude in southWest.longitude..northEast.longitude
    }
    fun getMapBorders(): CameraListener = with(binding) {
        val cameraListener = CameraListener { map, cameraPosition, cameraUpdateReason, finished ->
            // Проверяем, что обновление камеры произошло по причине действий пользователя и анимация завершена
            if (cameraUpdateReason == CameraUpdateReason.GESTURES && finished) {
                val isInsideBoundingBox = isPointInsideBoundingBox(
                    cameraPosition.target,
                    Сoordinates.SOUTHWEST_USPTU_CITY_COORD,
                    Сoordinates.NORTHEAST_USPTU_CITY_COORD
                )

                if (!isInsideBoundingBox) {
                    setDefaultLocation()
                    Log.d("MyTag", "Camera is moved back inside the allowed area")
                }
            }
        }

        cameraListener
    }

    fun setDefaultLocation(): Boolean = with(binding) {
        var result = try {
            mapViewMain.clearAnimation()
            mapViewMain.map.move(
                cameraPositionMain,
                Animation(Animation.Type.LINEAR, 0.5F),
                null
            )
            true
        } catch (e: IOException) {
            false
        }

        return result
    }

    private var userLocationPlacemark: PlacemarkMapObject? = null
    fun userPlacemark(): LocationListener = with(binding) {
        val mapObjects = mapViewMain.map.mapObjects
        val locationListener = object : LocationListener {

            override fun onLocationUpdated(p0: Location) {
                removeUserLocation()
                val placemark = mapObjects.addPlacemark(Point(p0.position.latitude, p0.position.longitude))
                placemark.setIcon(ImageProvider.fromResource(root.context, R.drawable.heart))
                userLocationPlacemark = placemark

                // чтобы камера следовала за пользователем
                // mapView.map.move(CameraPosition(point, 14.0f, 0.0f, 0.0f))
                Log.d("MyTag", "Location has been updated.")
            }

            override fun onLocationStatusUpdated(p0: LocationStatus) {
                Log.d("MyTag", "Location status has been changed.")
            }
        }

        locationListener
    }

    fun removeUserLocation() = with(binding) {
        userLocationPlacemark?.let {
            mapViewMain.map.mapObjects.remove(it)
            userLocationPlacemark = null
        }
    }
}