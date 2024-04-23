package com.example.usptu_map.map_operations.placemark_and_polygon.strategies.placemark
import android.content.Context
import android.graphics.PointF
import android.graphics.drawable.Drawable
import com.example.usptu_map.R
import com.example.usptu_map.R.drawable.ugntu2_placemark_animation
import com.example.usptu_map.map_operations.placemark_and_polygon.strategies.StrategyMapObject
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.map.RotationType
import com.yandex.runtime.image.AnimatedImageProvider
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class PlacemarkAnimationStrategy(private val image: AnimatedImageProvider) :
    StrategyMapObject {
    override fun apply(mapObject: MapObject) {
        if(mapObject is PlacemarkMapObject) {
            val animation = mapObject.useAnimation().apply {
                setIcon(image, IconStyle())
            }
            animation.play()
        }
    }
}

