package com.example.usptu_map.map_operations

import androidx.core.content.ContextCompat
import com.example.usptu_map.R
import com.example.usptu_map.databinding.ActivityMainBinding
import com.example.usptu_map.project_objects.coordinates.MapPoints
import com.example.usptu_map.project_objects.coordinates.PolygonsMapPoints
import com.yandex.mapkit.Animation
import com.yandex.mapkit.map.CameraPosition

class MapOprations(private val binding: ActivityMainBinding) {
    private val placemarkFactory: PlacemarkFactory = PlacemarkFactory(binding.mapViewMain)
    private val polygonFactory: PolygonFactory = PolygonFactory(binding.mapViewMain)

    private var cameraPositionMain = CameraPosition(
        MapPoints.CENTER_USPTU_CITY_COORD,
        /* zoom = */ 17.0f,
        /* azimuth = */ 150.0f,
        /* tilt = */ 30.0f
    )

    fun getCameraPosition(): CameraPosition = with(binding) {
        return mapViewMain.map.cameraPosition
    }

    fun startPointMaps() = with(binding) {
        mapViewMain.map.isZoomGesturesEnabled = false

        mapViewMain.map.move(
            cameraPositionMain,
            Animation(com.yandex.mapkit.Animation.Type.SMOOTH, 1F),
            null
        )
    }

    fun customPlacemarksOfMap() {
        placemarkFactory.addPlacemarkOnMap("", MapPoints.CORPUSES[0], R.drawable.heart) //заменять текст иконками в которые интегрирован текст
        placemarkFactory.addPlacemarkOnMap("", MapPoints.CORPUSES[1], R.drawable.heart)
        placemarkFactory.addPlacemarkOnMap("", MapPoints.CORPUSES[2], R.drawable.heart)
        placemarkFactory.addPlacemarkOnMap("", MapPoints.CORPUSES[3], R.drawable.heart)
        placemarkFactory.addPlacemarkOnMap("", MapPoints.CORPUSES[4], R.drawable.heart)
        placemarkFactory.addPlacemarkOnMap("", MapPoints.CORPUSES[5], R.drawable.heart)
        placemarkFactory.addPlacemarkOnMap("", MapPoints.CORPUSES[6], R.drawable.heart)
        placemarkFactory.addPlacemarkOnMap("", MapPoints.CORPUSES[7], R.drawable.heart)
        placemarkFactory.addPlacemarkOnMap("", MapPoints.CORPUSES[8], R.drawable.heart)

    }

    fun polygonsOfMap() = with(binding) {
        PolygonsMapPoints.apply {
            polygonFactory.addPolygonOnMap(firstCorpus, ContextCompat.getColor(root.context, R.color.light_red))
            polygonFactory.addPolygonOnMap(elevenCorpus, ContextCompat.getColor(root.context, R.color.light_red))
            polygonFactory.addPolygonOnMap(ufkOneCorpus, ContextCompat.getColor(root.context, R.color.light_red))
            polygonFactory.addPolygonOnMap(ufkTwoCorpus, ContextCompat.getColor(root.context, R.color.light_red))
            polygonFactory.addPolygonOnMap(thirdCorpus, ContextCompat.getColor(root.context, R.color.light_red))
            polygonFactory.addPolygonOnMap(secondCorpus, ContextCompat.getColor(root.context, R.color.light_red))
            polygonFactory.addPolygonOnMap(fourthCorpus, ContextCompat.getColor(root.context, R.color.light_red))
            polygonFactory.addPolygonOnMap(sevenCorpus, ContextCompat.getColor(root.context, R.color.light_red))
            polygonFactory.addPolygonOnMap(eighthCorpus, ContextCompat.getColor(root.context, R.color.light_red))
            polygonFactory.addPolygonOnMap(firstDormitory, ContextCompat.getColor(root.context, R.color.light_red))
            polygonFactory.addPolygonOnMap(secondDormitory, ContextCompat.getColor(root.context, R.color.light_red))
            polygonFactory.addPolygonOnMap(thirdDormitory, ContextCompat.getColor(root.context, R.color.light_red))
            polygonFactory.addPolygonOnMap(fourthDormitory, ContextCompat.getColor(root.context, R.color.light_red))
            polygonFactory.addPolygonOnMap(fiveDormitory, ContextCompat.getColor(root.context, R.color.light_red))
            polygonFactory.addPolygonOnMap(sixDormitory, ContextCompat.getColor(root.context, R.color.light_red))
            polygonFactory.addPolygonOnMap(tenDormitory, ContextCompat.getColor(root.context, R.color.light_red))
            polygonFactory.addPolygonOnMap(dinningRoom, ContextCompat.getColor(root.context, R.color.light_red))

            polygonFactory.addPolygonOnMap(firstCorpusArea, ContextCompat.getColor(root.context, R.color.light_blue))
            polygonFactory.addPolygonOnMap(secondCorpusArea, ContextCompat.getColor(root.context, R.color.light_blue))
            polygonFactory.addPolygonOnMap(thirdCorpusArea, ContextCompat.getColor(root.context, R.color.light_blue))
            polygonFactory.addPolygonOnMap(fourthCorpusArea, ContextCompat.getColor(root.context, R.color.light_blue))
        }
    }
}