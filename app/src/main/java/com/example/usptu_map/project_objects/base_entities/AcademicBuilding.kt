package com.example.usptu_map.project_objects.base_entities

import android.os.Parcel

class AcademicBuilding(
    name: String,
    address: String,
    coordinates: ParcelablePoint,
    val departments: List<String>, // Список кафедр
    val availableRooms: Int, // Доступные аудитории
    buildingPolygonPoints: List<ParcelablePoint> = listOf()
): Building(name, address, coordinates, "Academic", buildingPolygonPoints) {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "default",
        parcel.readString() ?: "default",
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