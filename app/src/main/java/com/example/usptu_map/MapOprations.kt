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
        mapOperationsTools.addPolygonOnMap(firstCorpusArea, R.color.white)

        val secondCorpusArea = listOf(
            Point(com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.latitude - 0.0006, com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.longitude + 0.0013),//НЛ
            Point(com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.latitude - 0.00077, com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.longitude - 0.00078),//НП
            Point(com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.latitude - 0.0042, com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.longitude + 0.00029),//ВП
            Point(com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.latitude - 0.004, com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.longitude + 0.0024),//ВЛ
        )
        mapOperationsTools.addPolygonOnMap(secondCorpusArea, R.color.white)

        val thirdCorpusArea = listOf(
            Point(com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.latitude - 0.00036, com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.longitude + 0.0038),//НЛ
            Point(com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.latitude - 0.00056, com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.longitude + 0.0015),//НП
            Point(com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.latitude - 0.004, com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.longitude + 0.00254),//ВП
            Point(com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.latitude - 0.0043, com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.longitude + 0.0032),//ВВ
            Point(com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.latitude - 0.002, com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.longitude + 0.0044),//ВЛ
        )
        mapOperationsTools.addPolygonOnMap(thirdCorpusArea, R.color.white)
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