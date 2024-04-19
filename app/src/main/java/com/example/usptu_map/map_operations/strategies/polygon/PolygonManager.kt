package com.example.usptu_map.map_operations.strategies.polygon

import com.example.usptu_map.map_operations.strategies.StrategyMapObject
import com.yandex.mapkit.map.PolygonMapObject

class PolygonManager(private val polygon : PolygonMapObject) {
    fun addStrategy(strategy: StrategyMapObject): PolygonManager {
        strategy.apply(polygon)
        return this
    }
}