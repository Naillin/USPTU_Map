package com.example.usptu_map.map_operations.strategies.placemark
import com.example.usptu_map.map_operations.strategies.StrategyMapObject
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.PlacemarkMapObject
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class PlacemarkAnimationStrategy : StrategyMapObject {
    override fun apply(mapObject: MapObject) {
        if(mapObject is PlacemarkMapObject) {
           // val iconStyle = IconStyle().setRotationType(RotationType.ROTATE)
            //mapObject.setIconStyle(iconStyle)
            //mapObject.useAnimation().play()

            MainScope().launch {
                while (true) {
                    mapObject.setVisible(true)
                    delay(500)
                    mapObject.setVisible(false)
                    delay(500)
                }
            }
        }
    }
}

