package com.example.usptu_map.project_objects.base_entities

import android.os.Parcel
import com.example.usptu_map.R

class AcademicBuilding(
    name: Int = R.string.first_corpus,
    address: String = "deafult",
    coordinates: ParcelablePoint = ParcelablePoint(0.0, 0.0), // Широта и долгота
    val departments: List<String> = listOf(), // Список кафедр
    val availableRooms: Int = 0, // Доступные аудитории
    buildingPolygonPoints: List<ParcelablePoint> = listOf()
): Building(name, address, coordinates, "Academic", buildingPolygonPoints) {
    constructor(parcel: Parcel) : this(
        parcel.readInt() ?: R.string.first_corpus,
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