package com.example.usptu_map.map_operations

import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.location.FilteringMode
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationStatus
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.transport.masstransit.Session
import com.yandex.runtime.image.ImageProvider

/**
 * Класс описывающий отслеживание местоположение пользователя
 */
class UserLocation(private val mapView: MapView, private val routeFactory: RouteFactory) {
    private var userLocationUpdateListener: UserLocationUpdateListener? = null;
    public fun setLocationListener(listener: UserLocationUpdateListener) { userLocationUpdateListener = listener }

    public var routingEnabled: Boolean = false
    private var userLocation: Location? = null; public fun getUserLocation() = userLocation
    private var routingSession: Session? = null; public fun getRoutingSession() = routingSession
    private var endPoint: Point = Point(0.0, 0.0)

    private var userLocationPlacemark: PlacemarkMapObject? = null
    private var mapObjects: MapObjectCollection? = null //Для хранения ссылки на коллекцию объектов карты
    //private val routeFactory: RouteFactory = RouteFactory(mapView)

    /**
     * Запуск отслеживания местоположения
     */
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

    /**
     * Установка подписки на обновление данных о локации пользователя
     */
    private fun subscribeToLocationUpdates() {
        val locationManager = MapKitFactory.getInstance().createLocationManager()
        locationManager.subscribeForLocationUpdates(1.0, 1, 0.0, true, FilteringMode.OFF, object : LocationListener {
            override fun onLocationUpdated(location: Location) {
                // Обновляем позицию метки местоположения пользователя
                userLocation = location
                userLocationPlacemark?.geometry = location.position

                //mapView.map.move(CameraPosition(location.position, 30.0f, 150.0f, 30.0f), Animation(Animation.Type.LINEAR, 0.1F), null)

                if(routingEnabled) {
                    routingSession = routeFactory.requestRoute2Points(location.position, endPoint) {

                    }
                }
                userLocationUpdateListener?.onLocationUpdated(location)
            }

            override fun onLocationStatusUpdated(locationStatus: LocationStatus) {
                //Обработка изменений статуса местоположения, если необходимо
            }
        })
    }

    /**
     * Установка точки назначения для построения маршрута
     */
    public fun setEndPoint(point: Point) {
        endPoint = point
    }

    /**
     * Проверка больше ли расстояние, чем distanceTo от пользователя до точки
     */
    public fun checkDistance(target: Point, distanceTo: Float = 1000F): Boolean {
        // Создаём объект Location для пользоваетля и выбранной точки
        val userAndroidLocation = android.location.Location("").apply {
            latitude = userLocation?.position?.latitude ?: 0.0
            longitude = userLocation?.position?.longitude ?: 0.0
        }
        val targetAndroidLocation = android.location.Location("").apply {
            latitude = target.latitude
            longitude = target.longitude
        }

        // Расстояние между местоположением пользователя и выбранной точкой в метрах
        val distance = userAndroidLocation.distanceTo(targetAndroidLocation)

        // Проверяем, больше ли расстояние чем distanceTo
        return if (distance > distanceTo) {
            true
        } else {
            false
        }
    }

    /**
     * Удаление метки отображающей локацию польователя
     */
    public fun removeUserLocationPlacemark() {
        mapObjects?.clear()
    }
}