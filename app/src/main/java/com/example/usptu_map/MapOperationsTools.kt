package com.example.usptu_map

import android.graphics.Color
import com.example.usptu_map.project_objects.Сoordinates
import com.yandex.mapkit.geometry.LinearRing
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.geometry.Polygon
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.map.PolygonMapObject
import com.yandex.mapkit.mapview.MapView

class MapOperationsTools(private val mapView: MapView) {
    val placemarkList: MutableList<PlacemarkMapObject> = mutableListOf()
    val polygonList: MutableList<PolygonMapObject> = mutableListOf()

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

    fun addPolygonOnMap(points: List<Point> = listOf(Point(50.0, 50.0)), color: Int = Color.RED){
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
}