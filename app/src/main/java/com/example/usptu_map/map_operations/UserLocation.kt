package com.example.usptu_map.map_operations

import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.location.FilteringMode
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationStatus
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.transport.masstransit.Session
import com.yandex.runtime.image.ImageProvider

class UserLocation(private val mapView: MapView, private val routeFactory: RouteFactory) {
    private var userLocation: Location? = null; public fun getUserLocation() = userLocation

    public var routingEnabled: Boolean = false
    public var routingSession: Session? = null
    private var endPoint: Point = Point(0.0, 0.0)

    private var userLocationPlacemark: PlacemarkMapObject? = null
    private var mapObjects: MapObjectCollection? = null //Для хранения ссылки на коллекцию объектов карты
    //private val routeFactory: RouteFactory = RouteFactory(mapView)

    fun initUserLocation(imageProvider: ImageProvider){
        // Инициализируем mapObjects один раз, если это ещё не было сделано
        if (mapObjects == null) {
            mapObjects = mapView.map.mapObjects.addCollection()
        }

        // Создаём метку местоположения пользователя, если она ещё не была создана
        if (userLocationPlacemark == null) {
            userLocationPlacemark = mapObjects?.addPlacemark(Point(0.0, 0.0))
            userLocationPlacemark?.setIcon(imageProvider)
        }

        // Подписываемся на обновления местоположения
        subscribeToLocationUpdates()
    }

    private fun subscribeToLocationUpdates() {
        val locationManager = MapKitFactory.getInstance().createLocationManager()
        locationManager.subscribeForLocationUpdates(1.0, 1, 0.0, true, FilteringMode.OFF, object : LocationListener {
            override fun onLocationUpdated(location: Location) {
                // Обновляем позицию метки местоположения пользователя
                userLocation = location
                userLocationPlacemark?.geometry = location.position

                mapView.map.move(CameraPosition(location.position, 30.0f, 150.0f, 30.0f), Animation(Animation.Type.LINEAR, 0.1F), null)

                if(routingEnabled) {
                    routingSession = routeFactory.requestRoute2Points(location.position, endPoint) {

                    }
                }
                //onLocationUpdateFunc?.invoke()
            }

            override fun onLocationStatusUpdated(locationStatus: LocationStatus) {
                //Обработка изменений статуса местоположения, если необходимо
            }
        })
    }

    public fun setEndPoint(point: Point) {
        endPoint = point
    }
}