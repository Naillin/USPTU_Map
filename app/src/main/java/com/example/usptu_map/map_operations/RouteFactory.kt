package com.example.usptu_map.map_operations

import android.graphics.Color
import com.yandex.mapkit.RequestPoint
import com.yandex.mapkit.RequestPointType
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.PolylineMapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.transport.TransportFactory
import com.yandex.mapkit.transport.masstransit.Route
import com.yandex.mapkit.transport.masstransit.Session
import com.yandex.mapkit.transport.masstransit.TimeOptions

class RouteFactory(private val mapView: MapView) {
    private var routesCollection: MapObjectCollection? = mapView.map.mapObjects.addCollection()
    private var routeOfMap: PolylineMapObject? = null
    private var pedestrianSession: Session? = null

    public fun requestRoute2Points(
        startPoint: Point,
        endPoint: Point,
        onError: (com.yandex.runtime.Error) -> Unit
    ): Session {
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
                    onError(error)
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
            mapView.map.mapObjects.remove(it)
            routeOfMap = null // Обнуляем ссылку, чтобы избежать повторного использования
        }
    }

    public fun cancelRouteSession() {
        pedestrianSession?.cancel() // Отменяем текущую сессию
    }
}