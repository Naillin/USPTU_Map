package com.example.usptu_map.map_operations

import com.yandex.mapkit.location.Location

/**
 * Callback слушатель изменения локации пользователя
 */
interface UserLocationUpdateListener {
    fun onLocationUpdated(location: Location)

}