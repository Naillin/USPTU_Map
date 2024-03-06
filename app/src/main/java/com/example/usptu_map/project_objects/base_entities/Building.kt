package com.example.usptu_map.project_objects.base_entities

import com.yandex.mapkit.geometry.Point

open class Building(
    val name: String,
    val address: String,
    val coordinates: Point, // Широта и долгота
    val type: String
) {
    fun getInfo(): String {
        return "Building Name: $name, Address: $address, Type: $type"
    }
}