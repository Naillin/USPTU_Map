package com.example.usptu_map.map_operations.placemark_and_polygon.strategies.polygon

import com.example.usptu_map.map_operations.placemark_and_polygon.strategies.StrategyMapObject
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.PolygonMapObject

class PolygonFillColorStrategy(private val color : Int) :StrategyMapObject {
    override fun apply(mapObject: MapObject) {
        if(mapObject is PolygonMapObject){
            mapObject.fillColor = color
        }
    }
}