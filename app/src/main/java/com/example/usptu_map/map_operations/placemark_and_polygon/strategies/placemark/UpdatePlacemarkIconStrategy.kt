package com.example.usptu_map.map_operations.placemark_and_polygon.strategies.placemark

import android.content.Context
import com.example.usptu_map.map_operations.placemark_and_polygon.strategies.StrategyMapObject
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.runtime.image.ImageProvider

class UpdatePlacemarkIconStrategy(private val context: Context,private val iconResId: Int):
    StrategyMapObject {
    override fun apply(mapObject: MapObject) {
        if(mapObject is PlacemarkMapObject) {
            mapObject.setIcon(ImageProvider.fromResource(context, iconResId))
        }
    }
}