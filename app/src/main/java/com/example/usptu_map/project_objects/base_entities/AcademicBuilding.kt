package com.example.usptu_map.project_objects.base_entities

import com.yandex.mapkit.geometry.Point

class AcademicBuilding(
    name: String,
    address: String,
    coordinates: Point,
    val departments: List<String>, // Список кафедр
    val availableRooms: Int // Доступные аудитории
) : Building(name, address, coordinates, "Academic") {

    fun getAcademicDetails(): String {
        val departmentsList = departments.joinToString(separator = ", ")
        return "${getInfo()}, Departments: $departmentsList, Available rooms: $availableRooms"
    }
}