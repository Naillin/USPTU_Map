package com.example.usptu_map.map_operations

import android.widget.Toast
import com.example.usptu_map.project_objects.coordinates.MapPoints
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.mapview.MapView

class PlacemarkFactory(private val mapView: MapView) {
    private val mapObjects = mapView.map.mapObjects.addCollection()
    private val placemarks: MutableList<PlacemarkMapObject> = mutableListOf()

    fun addPlacemarkOnMap(title: String = "", point: Point = MapPoints.CENTER_USPTU_CITY_COORD, icon: Int = 0) : PlacemarkMapObject {
        val context = mapView.context
        val placemark = mapObjects.addPlacemark(point)
        placemark.setIcon(com.yandex.runtime.image.ImageProvider.fromResource(context, icon)) // Установка иконки
        placemark.setText(title)
        //placemark.opacity = 0.5f // Настройка прозрачности

        //placemark.useAnimation(PlacemarkAnimation.scale(1.0f, 2.0f, Animation.Type.SMOOTH, 5.0f))
        //placemark.playAnimation()
        // Добавляем обработчик нажатия на метку
        placemark.addTapListener(object : MapObjectTapListener {
            override fun onMapObjectTap(p0: com.yandex.mapkit.map.MapObject, p1: com.yandex.mapkit.geometry.Point): Boolean {
                if (p0 is PlacemarkMapObject) {
                    // Действия при нажатии на метку
                    Toast.makeText(context, "Вы нажали на метку: $title", Toast.LENGTH_SHORT).show()
                    return true
                }
                return false
            }
        })
        return placemark
    }
}