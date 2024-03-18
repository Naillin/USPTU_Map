package com.example.usptu_map.map_operations

import com.example.usptu_map.project_objects.base_entities.coordinates.MapPoints
import com.yandex.mapkit.Animation
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import java.io.IOException

class MapBordersHolder(private val mapView: MapView) {
    private var cameraPositionMain = CameraPosition(
        MapPoints.CENTER_USPTU_CITY_COORD,
        /* zoom = */ 17.0f,
        /* azimuth = */ 150.0f,
        /* tilt = */ 30.0f
    )

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

    private fun isPointInsideBoundingBox(point: Point, southWest: Point, northEast: Point): Boolean {
        return point.latitude in southWest.latitude..northEast.latitude &&
                point.longitude in southWest.longitude..northEast.longitude
    }
    fun getMapBorders(): CameraListener {
        val cameraListener = CameraListener { _, cameraPosition, cameraUpdateReason, finished ->
            // Проверяем, что обновление камеры произошло по причине действий пользователя и анимация завершена
            if (cameraUpdateReason == com.yandex.mapkit.map.CameraUpdateReason.GESTURES && finished) {
                val isInsideBoundingBox = isPointInsideBoundingBox(
                    cameraPosition.target,
                    MapPoints.SOUTHWEST_USPTU_CITY_COORD,
                    MapPoints.NORTHEAST_USPTU_CITY_COORD
                )

                if (!isInsideBoundingBox) {
                    setDefaultLocation()
                    android.util.Log.d("MyTag", "Camera is moved back inside the allowed area")
                }
            }
        }

        return cameraListener
    }

    fun setDefaultLocation(): Boolean {
        var result = try {
            mapView.clearAnimation()
            mapView.map.move(
                cameraPositionMain,
                Animation(com.yandex.mapkit.Animation.Type.LINEAR, 0.5F),
                null
            )
            true
        } catch (e: IOException) {
            false
        }

        return result
    }
}