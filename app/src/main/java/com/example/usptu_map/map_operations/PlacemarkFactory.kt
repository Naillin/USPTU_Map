package com.example.usptu_map.map_operations

import com.example.usptu_map.project_objects.coordinates.MapPoints
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.PlacemarkAnimation
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.mapview.MapView

interface PlacemarkProvider {
    fun setPlacemarkOpacity(placemark: PlacemarkMapObject, opacity: Float)// прозрачность

    fun removePlacemark(placemark: PlacemarkMapObject)//удалить метку

    fun updatePlacemarkPoint(placemark: PlacemarkMapObject, newPoint: Point)//изменить координаты метки

    fun updatePlacemarkIcon(placemark: PlacemarkMapObject, newIcon: Int)//изменить иконку
    fun updatePlacemarkText(placemark: PlacemarkMapObject, newTitle: String) //изменить заголовок
}

class PlacemarkFactory(private val mapView: MapView) : PlacemarkProvider {
    private val mapObjects = mapView.map.mapObjects.addCollection()


    fun addPlacemarkOnMap(title: String = "", point: Point = MapPoints.CENTER_USPTU_CITY_COORD, icon: Int = 0) : PlacemarkMapObject {
        val context = mapView.context
        val placemark = mapObjects.addPlacemark(point)
        placemark.setIcon(com.yandex.runtime.image.ImageProvider.fromResource(context, icon)) // Установка иконки
        placemark.setText(title)
        //placemark.opacity = 0.5f // Настройка прозрачности

        //placemark.useAnimation(PlacemarkAnimation.scale(1.0f, 2.0f, Animation.Type.SMOOTH, 5.0f))
        //placemark.playAnimation()
        return placemark
    }
    override fun setPlacemarkOpacity(placemark: PlacemarkMapObject, opacity: Float) {
        placemark.opacity = opacity
    }

    override fun removePlacemark(placemark: PlacemarkMapObject) {
        mapObjects.remove(placemark)
    }


    override fun updatePlacemarkPoint(placemark: PlacemarkMapObject, newPoint: Point) {
        placemark.geometry = newPoint
    }

    override fun updatePlacemarkIcon(placemark: PlacemarkMapObject, newIcon: Int) {
        val context = mapView.context
        placemark.setIcon(com.yandex.runtime.image.ImageProvider.fromResource(context, newIcon))
    }
    override fun updatePlacemarkText(placemark: PlacemarkMapObject, newTitle: String) {
        placemark.setText(newTitle)
    }





}