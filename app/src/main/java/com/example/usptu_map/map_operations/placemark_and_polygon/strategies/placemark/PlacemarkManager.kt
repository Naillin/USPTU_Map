package com.example.usptu_map.map_operations.placemark_and_polygon.strategies.placemark
import com.example.usptu_map.map_operations.placemark_and_polygon.strategies.StrategyMapObject
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.map.PolygonMapObject

class PlacemarkManager(private val placemark : PlacemarkMapObject, private val mapObjects : MapObjectCollection) {
    fun addStrategy(strategy: StrategyMapObject): PlacemarkManager {
        strategy.apply(placemark)
        return this
    }
    fun getObject(): PlacemarkMapObject {
        return placemark
    }

    fun deleteObject() {
        mapObjects.remove(placemark)
    }
}