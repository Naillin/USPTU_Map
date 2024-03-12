package com.example.usptu_map.map_operations

import android.graphics.Color
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.usptu_map.R
import com.example.usptu_map.databinding.ActivityMainBinding
import com.example.usptu_map.project_objects.PolygonsMarks
import com.example.usptu_map.project_objects.Сoordinates
import com.yandex.mapkit.Animation
import com.yandex.mapkit.RequestPoint
import com.yandex.mapkit.RequestPointType
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.location.FilteringMode
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationStatus
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.CameraUpdateReason
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.map.PolylineMapObject
import com.yandex.mapkit.transport.TransportFactory
import com.yandex.mapkit.transport.masstransit.Route
import com.yandex.mapkit.transport.masstransit.Session
import com.yandex.mapkit.transport.masstransit.TimeOptions
import java.io.IOException

class MapOprations(private val binding: ActivityMainBinding) {
    private val mapOperationsTools: MapOperationsTools = MapOperationsTools(binding.mapViewMain)

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
        PolygonsMarks.apply {
            mapOperationsTools.addPolygonOnMap(firstCorpus, ContextCompat.getColor(root.context,
                R.color.light_red
            ))
            mapOperationsTools.addPolygonOnMap(elevenCorpus, ContextCompat.getColor(root.context,
                R.color.light_red
            ))
            mapOperationsTools.addPolygonOnMap(ufkOneCorpus, ContextCompat.getColor(root.context,
                R.color.light_red
            ))
            mapOperationsTools.addPolygonOnMap(ufkTwoCorpus, ContextCompat.getColor(root.context,
                R.color.light_red
            ))
            mapOperationsTools.addPolygonOnMap(thirdCorpus, ContextCompat.getColor(root.context,
                R.color.light_red
            ))
            mapOperationsTools.addPolygonOnMap(secondCorpus, ContextCompat.getColor(root.context,
                R.color.light_red
            ))
            mapOperationsTools.addPolygonOnMap(fourthCorpus, ContextCompat.getColor(root.context,
                R.color.light_red
            ))
            mapOperationsTools.addPolygonOnMap(sevenCorpus, ContextCompat.getColor(root.context,
                R.color.light_red
            ))
            mapOperationsTools.addPolygonOnMap(eighthCorpus, ContextCompat.getColor(root.context,
                R.color.light_red
            ))
            mapOperationsTools.addPolygonOnMap(firstDormitory, ContextCompat.getColor(root.context,
                R.color.light_red
            ))
            mapOperationsTools.addPolygonOnMap(secondDormitory, ContextCompat.getColor(root.context,
                R.color.light_red
            ))
            mapOperationsTools.addPolygonOnMap(thirdDormitory, ContextCompat.getColor(root.context,
                R.color.light_red
            ))
            mapOperationsTools.addPolygonOnMap(fourthDormitory, ContextCompat.getColor(root.context,
                R.color.light_red
            ))
            mapOperationsTools.addPolygonOnMap(fiveDormitory, ContextCompat.getColor(root.context,
                R.color.light_red
            ))
            mapOperationsTools.addPolygonOnMap(sixDormitory, ContextCompat.getColor(root.context,
                R.color.light_red
            ))
            mapOperationsTools.addPolygonOnMap(tenDormitory, ContextCompat.getColor(root.context,
                R.color.light_red
            ))
            mapOperationsTools.addPolygonOnMap(dinningRoom, ContextCompat.getColor(root.context,
                R.color.light_red
            ))

            mapOperationsTools.addPolygonOnMap(firstCorpusArea, ContextCompat.getColor(root.context,
                R.color.light_blue
            ))
            mapOperationsTools.addPolygonOnMap(secondCorpusArea, ContextCompat.getColor(root.context,
                R.color.light_blue
            ))
            mapOperationsTools.addPolygonOnMap(thirdCorpusArea, ContextCompat.getColor(root.context,
                R.color.light_blue
            ))
            mapOperationsTools.addPolygonOnMap(fourthCorpusArea, ContextCompat.getColor(root.context,
                R.color.light_blue
            ))
        }

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

    private var routesCollection: MapObjectCollection? = binding.mapViewMain.map.mapObjects.addCollection()
    private var routeOfMap: PolylineMapObject? = null
    private var pedestrianSession: Session? = null
    fun requestRoute2Points(startPoint: Point, endPoint: Point): Session {
        val pedestrianRouter = TransportFactory.getInstance().createPedestrianRouter()

        //Создание запроса
        val requestPoints = arrayListOf(
            RequestPoint(startPoint, RequestPointType.WAYPOINT, null, null),
            RequestPoint(endPoint, RequestPointType.WAYPOINT, null, null)
        )

        //Удаление предыдущего маршрута, если он существует
        //removeCurrentRoute()
        removeAllRoutes()

        pedestrianSession = pedestrianRouter.requestRoutes(
            requestPoints,
            TimeOptions(), // TimeOptions без nullable параметров
            object : Session.RouteListener {
                override fun onMasstransitRoutes(routes: MutableList<Route>) {
                    if (routes.isNotEmpty()) {
                        // Создаем новый маршрут на карте
                        //routeOfMap = binding.mapViewMain.map.mapObjects.addPolyline(routes.first().geometry)
                        routeOfMap = routesCollection?.addPolyline(routes.first().geometry)
                        routeOfMap!!.setStrokeColor(Color.RED)
                    }
                }

                override fun onMasstransitRoutesError(error: com.yandex.runtime.Error) {
                    Toast.makeText(binding.root.context, "Ошибка при построении маршрута: ${error.toString()}", Toast.LENGTH_LONG).show()
                }
            }
        )

        return pedestrianSession as Session
    }

    private fun removeAllRoutes() {
        routesCollection?.clear() // Удаляем все маршруты из коллекции
    }

    private fun removeCurrentRoute() {
        routeOfMap?.let {
            binding.mapViewMain.map.mapObjects.remove(it)
            routeOfMap = null // Обнуляем ссылку, чтобы избежать повторного использования
        }
    }

    fun cancelRouteSession() {
        pedestrianSession?.cancel() // Отменяем текущую сессию
    }
}