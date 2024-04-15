package com.example.usptu_map.map_operations

import android.graphics.Color
import com.yandex.mapkit.geometry.LinearRing
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.geometry.Polygon
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.PolygonMapObject
import com.yandex.mapkit.mapview.MapView

class PolygonFactory(private val mapView: MapView) {
    private val mapObjects: MapObjectCollection = mapView.map.mapObjects.addCollection()

    fun addPolygonOnMap(points: List<Point> = listOf(Point(50.0, 50.0)), color: Int = Color.RED) : PolygonMapObject {
        // Создание полигона
        val polygon = Polygon(LinearRing(points), listOf())

        // Добавление полигона на карту и настройка его внешнего вида
        val polygonMapObject = mapObjects.addPolygon(polygon)
        polygonMapObject.fillColor = color // Пример: полупрозрачный красный цвет
        polygonMapObject.strokeColor = Color.BLACK // Цвет границы
        polygonMapObject.strokeWidth = 2.0f // Ширина границы

        return polygonMapObject
    }

    fun updatePolygonColor(polygonMapObject: PolygonMapObject, color: Int) : PolygonMapObject  {
        polygonMapObject.fillColor = color

        return polygonMapObject
    }

    fun updatePolygonStrokeColor(polygonMapObject: PolygonMapObject, color: Int) : PolygonMapObject  {
        polygonMapObject.strokeColor = color

        return polygonMapObject
    }

    fun updatePolygonStrokeWidth(polygonMapObject: PolygonMapObject, width: Float) : PolygonMapObject  {
        polygonMapObject.strokeWidth = width

        return polygonMapObject
    }

    fun removePolygon(polygonMapObject: PolygonMapObject) : PolygonMapObject  {
        mapObjects.remove(polygonMapObject)

        return polygonMapObject
    }



}