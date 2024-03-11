package com.example.usptu_map.map_operations

import android.widget.Toast
import com.example.usptu_map.R
import com.example.usptu_map.databinding.ActivityMainBinding
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
import com.yandex.runtime.image.ImageProvider

class UserLocation(
    private val binding: ActivityMainBinding,
    //private val onLocationUpdateFunc: (() -> Unit)?
) {
    private var userLocation: Location? = null; public fun getUserLocation() = userLocation
    private var userLocationPlacemark: PlacemarkMapObject? = null

    private var mapObjects: MapObjectCollection? = null // Для хранения ссылки на коллекцию объектов карты

    fun initUserLocation() = with(binding) {
        // Инициализируем mapObjects один раз, если это ещё не было сделано
        if (mapObjects == null) {
            mapObjects = mapViewMain.map.mapObjects.addCollection()
        }

        // Создаём метку местоположения пользователя, если она ещё не была создана
        if (userLocationPlacemark == null) {
            userLocationPlacemark = mapObjects?.addPlacemark(Point(0.0, 0.0))
            userLocationPlacemark?.setIcon(ImageProvider.fromResource(root.context, R.drawable.heart))
        }

        // Подписываемся на обновления местоположения
        subscribeToLocationUpdates()
    }

    private fun subscribeToLocationUpdates() = with(binding) {
        val locationManager = MapKitFactory.getInstance().createLocationManager()
        locationManager.subscribeForLocationUpdates(1.0, 1, 0.0, true, FilteringMode.OFF, object : LocationListener {
            override fun onLocationUpdated(location: Location) {
                // Обновляем позицию метки местоположения пользователя
                userLocation = location
                userLocationPlacemark?.geometry = location.position

                mapViewMain.map.move(CameraPosition(location.position, 30.0f, 150.0f, 30.0f), Animation(Animation.Type.LINEAR, 0.1F), null)

                //onLocationUpdateFunc?.invoke()
            }

            override fun onLocationStatusUpdated(locationStatus: LocationStatus) {
                // Обработка изменений статуса местоположения, если необходимо
            }
        })
    }
}