package com.example.usptu_map.project_objects.base_entities

import com.yandex.mapkit.geometry.Point

class Dormitory(
    name: String,
    address: String,
    coordinates: Point,
    val capacity: Int, // Вместимость общежития
    val number: Int // Номер общежития
) : Building(name, address, coordinates, "Dormitory") {

    fun getDormitoryDetails(): String {
        return "${getInfo()}, Capacity: $capacity, Number: $number"
    }
}