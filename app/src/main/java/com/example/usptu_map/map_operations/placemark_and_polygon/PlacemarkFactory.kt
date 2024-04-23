package com.example.usptu_map.map_operations.placemark_and_polygon

import com.example.usptu_map.map_operations.placemark_and_polygon.strategies.placemark.PlacemarkManager
import com.example.usptu_map.project_objects.coordinates.MapPoints
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider

class PlacemarkFactory(private val mapView: MapView): SettingsPlacemarkPolygon  {
    private val mapObjects = mapView.map.mapObjects.addCollection()

    fun addPlacemarkOnMap(title: String = "", point: Point = MapPoints.CENTER_USPTU_CITY_COORD, icon: Int = 0) : PlacemarkManager {
        val context = mapView.context
        val placemark = mapObjects.addPlacemark(point)
        placemark.setIcon(com.yandex.runtime.image.ImageProvider.fromResource(context, icon)) // Установка иконки
        placemark.setText(title)
        return PlacemarkManager(placemark, mapObjects)
    }

    override fun deleteMapObjects() { //удаление всех пметок
        mapObjects.clear()
    }
}