package com.example.usptu_map.map_operations

import android.widget.Toast
import com.example.usptu_map.map_operations.strategies.placemark.PlacemarkManager
import com.example.usptu_map.project_objects.coordinates.MapPoints
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.mapview.MapView

class PlacemarkFactory(private val mapView: MapView) {
    private val mapObjects = mapView.map.mapObjects.addCollection()

    fun addPlacemarkOnMap(title: String = "", point: Point = MapPoints.CENTER_USPTU_CITY_COORD, icon: Int = 0) : PlacemarkManager {
        val context = mapView.context
        val placemark = mapObjects.addPlacemark(point)
        placemark.setIcon(com.yandex.runtime.image.ImageProvider.fromResource(context, icon)) // Установка иконки
        placemark.setText(title)
        //placemark.opacity = 0.5f // Настройка прозрачности


        return PlacemarkManager(placemark)
    }
}