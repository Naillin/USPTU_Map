package com.example.usptu_map.project_objects.base_entities

import android.os.Parcel

class Dormitory(
    name: String,
    address: String,
    coordinates: ParcelablePoint,
    val capacity: Int = 0, // Вместимость общежития
    buildingPolygonPoints: List<ParcelablePoint> = listOf()
): Building(name, address, coordinates, "Dormitory", buildingPolygonPoints) {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "default",
        parcel.readString() ?: "default",
        parcel.readParcelable<ParcelablePoint>(ParcelablePoint::class.java.classLoader) ?: ParcelablePoint(0.0, 0.0),
        parcel.readInt(),
        parcel.createTypedArrayList(ParcelablePoint.CREATOR) ?: listOf()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags) // Вызов метода суперкласса для записи общих свойств
        parcel.writeInt(capacity)
    }

    override fun describeContents(): Int = 0

    fun getDormitoryDetails(): String {
        return "${getInfo()}, Capacity: $capacity"
    }
}