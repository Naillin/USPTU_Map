package com.example.usptu_map.map_operations.strategies.polygon

import com.example.usptu_map.map_operations.strategies.StrategyMapObject
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.PolygonMapObject

class PolygonStrokeStrategy(private val color: Int, private val width: Float) : StrategyMapObject {
    override fun apply(mapObject: MapObject) {
        if(mapObject is PolygonMapObject){
            mapObject.strokeColor=color
            mapObject.strokeWidth=width
        }
    }
}