package com.example.usptu_map.map_operations.placemark_and_polygon.strategies

import com.yandex.mapkit.map.MapObject

interface StrategyMapObject {
    fun apply(mapObject: MapObject)
}