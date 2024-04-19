package com.example.usptu_map.map_operations.strategies.placemark

import com.example.usptu_map.map_operations.strategies.StrategyMapObject
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.map.PlacemarkMapObject

class ClickListenerStrategy(private val placemarkTapListener : MapObjectTapListener) : StrategyMapObject {
    override fun apply(mapObject: MapObject) {
        if(mapObject is PlacemarkMapObject){
            mapObject.addTapListener(placemarkTapListener)
        }
    }
}