package com.example.usptu_map

import android.graphics.Color
import android.util.Log
import android.view.View
import com.example.usptu_map.databinding.ActivityMainBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.geometry.LinearRing
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.geometry.Polygon
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.runtime.image.ImageProvider

class MapOprations(private val binding: ActivityMainBinding) {
    val mapOperationsTools: MapOperationsTools = MapOperationsTools(binding.mapViewMain)

    private var cameraPositionMain = CameraPosition(
        Сoordinates.CENTER_USPTU_CITY_COORD,
        /* zoom = */ 17.0f,
        /* azimuth = */ 150.0f,
        /* tilt = */ 30.0f
    )

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
        mapOperationsTools.addPlacemarkOnMap("", Сoordinates.CORPUS1, R.drawable.heart) //заменять текст иконками в которые интегрирован текст
    }

    fun polygonsOfMap() = with(binding) {
        // Создание точек вокруг центральной точки для полигона
        val points = listOf(
            Point(com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.latitude + 0.001, com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.longitude + 0.001),
            Point(com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.latitude + 0.001, com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.longitude - 0.001),
            Point(com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.latitude - 0.001, com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.longitude - 0.001),
            Point(com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.latitude - 0.001, com.example.usptu_map.Сoordinates.CENTER_USPTU_CITY_COORD.longitude + 0.001)
        )
        mapOperationsTools.addPolygonOnMap(points, R.color.white)
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

    fun mapBorders() = with(binding) {
        val allowedAreaRadius = 0.0135

        val cameraListener = CameraListener { map, cameraPosition, _, finished ->
            val currentPoint = cameraPosition.target
            val distance = Math.sqrt(
                Math.pow(currentPoint.latitude - Сoordinates.CENTER_USPTU_CITY_COORD.latitude, 2.0) +
                        Math.pow(currentPoint.longitude - Сoordinates.CENTER_USPTU_CITY_COORD.longitude, 2.0)
            )
            Log.d("MyTag", "distance = $distance")

            // Проверка, достигла ли камера начальной позиции
            val isAtStartPoint = Math.abs(currentPoint.latitude - Сoordinates.CENTER_USPTU_CITY_COORD.latitude) < 0.0001 &&
                    Math.abs(currentPoint.longitude - Сoordinates.CENTER_USPTU_CITY_COORD.longitude) < 0.0001
            if (distance > allowedAreaRadius) {
                viewBlockMap.visibility = View.VISIBLE
                map.move(
                    cameraPositionMain, // Убедитесь, что это координаты для возвращения
                    Animation(Animation.Type.LINEAR, 0.5F),
                    null
                )
                Log.d("MyTag", "MapView is being recovered")
            } else if (isAtStartPoint) {
                viewBlockMap.visibility = View.GONE
                Log.d("MyTag", "MapView recovery finished, user can move the map again")
            }
        }

        mapViewMain.mapWindow.map.addCameraListener(cameraListener)
    }
}