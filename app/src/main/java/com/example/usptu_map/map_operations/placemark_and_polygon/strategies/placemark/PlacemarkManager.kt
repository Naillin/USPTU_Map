package com.example.usptu_map.map_operations.placemark_and_polygon.strategies.placemark
import com.example.usptu_map.map_operations.placemark_and_polygon.strategies.StrategyMapObject
import com.yandex.mapkit.map.PlacemarkMapObject

class PlacemarkManager(private val placemark : PlacemarkMapObject) {
    fun addStrategy(strategy: StrategyMapObject): PlacemarkManager {
        strategy.apply(placemark)
        return this
    }
}