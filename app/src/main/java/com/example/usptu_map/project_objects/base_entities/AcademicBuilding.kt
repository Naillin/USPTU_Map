package com.example.usptu_map.project_objects.base_entities

import android.os.Parcel

class AcademicBuilding(
    name: String = "default",
    address: String = "deafult",
    coordinates: ParcelablePoint = ParcelablePoint(0.0, 0.0), // Широта и долгота
    coordinatesExit: ParcelablePoint = ParcelablePoint(0.0, 0.0), // Широта и долгота
    val departments: List<String> = listOf(), // Список кафедр
    val availableRooms: Int = 0, // Доступные аудитории
    buildingPolygonPoints: List<ParcelablePoint> = listOf()
): Building(name, address, coordinates, coordinatesExit,"Academic", buildingPolygonPoints) {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "default",
        parcel.readString() ?: "default",
        parcel.readParcelable<ParcelablePoint>(ParcelablePoint::class.java.classLoader) ?: ParcelablePoint(0.0, 0.0),
        parcel.readParcelable<ParcelablePoint>(ParcelablePoint::class.java.classLoader) ?: ParcelablePoint(0.0, 0.0),
        arrayListOf<String>().apply {
            parcel.readStringList(this)
        },
        parcel.readInt(),
        parcel.createTypedArrayList(ParcelablePoint.CREATOR) ?: listOf()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
        parcel.writeStringList(departments)
        parcel.writeInt(availableRooms)
    }

    override fun describeContents(): Int = 0

    fun getAcademicDetails(): String {
        val departmentsList = departments.joinToString(separator = ", ")
        return "${getInfo()}, Departments: $departmentsList, Available rooms: $availableRooms"
    }
}