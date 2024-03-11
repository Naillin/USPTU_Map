package com.example.usptu_map.map_operations

import android.graphics.Color
import com.example.usptu_map.project_objects.Сoordinates
import com.yandex.mapkit.geometry.LinearRing
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.geometry.Polygon
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.map.PolygonMapObject
import com.yandex.mapkit.map.PolylineMapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.transport.masstransit.Route
import com.yandex.mapkit.transport.masstransit.Session

class MapOperationsTools(private val mapView: MapView) {
    val placemarkList: MutableList<PlacemarkMapObject> = mutableListOf()
    val polygonList: MutableList<PolygonMapObject> = mutableListOf()
    //private var routeOfMap: PolylineMapObject? = null

    fun addPlacemarkOnMap(title: String = "", point: Point = Сoordinates.CENTER_USPTU_CITY_COORD, icon: Int = 0) {
        val context = mapView.context
        val mapObjects = mapView.map.mapObjects.addCollection()

        val placemark = mapObjects.addPlacemark(point)
        placemark.setIcon(com.yandex.runtime.image.ImageProvider.fromResource(context, icon)) // Установка иконки
        placemark.setText(title)
        //placemark.opacity = 0.5f // Настройка прозрачности

        //placemark.useAnimation(PlacemarkAnimation.scale(1.0f, 2.0f, Animation.Type.SMOOTH, 5.0f))
        //placemark.playAnimation()

        placemarkList.add(placemark)
    }

    fun addPolygonOnMap(points: List<Point> = listOf(Point(50.0, 50.0)), color: Int = Color.RED) {
        val mapObjects: MapObjectCollection = mapView.map.mapObjects.addCollection()

        // Создание полигона
        val polygon = Polygon(LinearRing(points), listOf())

        // Добавление полигона на карту и настройка его внешнего вида
        val polygonMapObject = mapObjects.addPolygon(polygon)
        polygonMapObject.fillColor = color // Пример: полупрозрачный красный цвет
        polygonMapObject.strokeColor = Color.BLACK // Цвет границы
        polygonMapObject.strokeWidth = 2.0f // Ширина границы

        polygonList.add(polygonMapObject)
    }

    inner class RouteFactory(private var route: PolylineMapObject?,
                             private val color: Int = Color.DKGRAY,
                             private val onError: (com.yandex.runtime.Error) -> Unit,
                             private val onUpdateRoute: (PolylineMapObject) -> Unit) : Session.RouteListener {
        override fun onMasstransitRoutes(routes: MutableList<Route>) {
            if (routes.isNotEmpty()) {
                //Удаляем предыдущий маршрут, если он существует
                route?.let {
                    mapView.map.mapObjects.remove(it)
                }

                route = mapView.map.mapObjects.addPolyline(routes.first().geometry)
                route!!.setStrokeColor(color) //Цвет полилинии маршрута
                onUpdateRoute(route!!)
            }
        }

        override fun onMasstransitRoutesError(error: com.yandex.runtime.Error) {
            onError(error)
        }
    }

//    fun requestRoute2Points(startPoint: Point, endPoint: Point): Session {
//        //Запуск
//        val pedestrianRouter = TransportFactory.getInstance().createPedestrianRouter()
//
//        //Создание запроса
//        val requestPoints = arrayListOf(
//            RequestPoint(startPoint, RequestPointType.WAYPOINT, null, null),
//            RequestPoint(endPoint, RequestPointType.WAYPOINT, null, null)
//        )
//
//        val pedestrianSession = pedestrianRouter.requestRoutes(requestPoints, TimeOptions(null, null), object : Session.RouteListener {
//            override fun onMasstransitRoutes(routes: MutableList<Route>) {
//                if (routes.isNotEmpty()) {
//                    // Удаляем предыдущий маршрут, если он существует
//                    routeOfMap?.let {
//                        mapView.map.mapObjects.remove(it)
//                    }
//
//                    routeOfMap = mapView.map.mapObjects.addPolyline(routes.first().geometry)
//                    routeOfMap!!.setStrokeColor(Color.DKGRAY) // Цвет полилинии маршрута
//                }
//            }
//
//            override fun onMasstransitRoutesError(error: com.yandex.runtime.Error) {
//                //Toast.makeText(context, "Ошибка при построении маршрута: ${error.toString()}", Toast.LENGTH_LONG).show()
//            }
//        })
//
//        return pedestrianSession
//    }
//
//    fun removeRouteOfMap() {
//        routeOfMap?.let {
//            mapView.map.mapObjects.remove(it)
//            routeOfMap = null
//        }
//    }
}