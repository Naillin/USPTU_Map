package com.example.usptu_map.project_objects.base_entities

import android.os.Parcel

class Dormitory(
    name: String,
    address: String,
    coordinates: ParcelablePoint,
    val capacity: Int, // Вместимость общежития
    val number: Int, // Номер общежития
    buildingPolygonPoints: List<ParcelablePoint> = listOf()
): Building(name, address, coordinates, "Dormitory", buildingPolygonPoints) {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "default",
        parcel.readString() ?: "default",
        parcel.readParcelable<ParcelablePoint>(ParcelablePoint::class.java.classLoader) ?: ParcelablePoint(0.0, 0.0),
        parcel.readInt(),
        parcel.readInt(),
        parcel.createTypedArrayList(ParcelablePoint.CREATOR) ?: listOf()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags) // Вызов метода суперкласса для записи общих свойств
        parcel.writeInt(capacity)
        parcel.writeInt(number)
    }

    override fun describeContents(): Int = 0

    fun getDormitoryDetails(): String {
        return "${getInfo()}, Capacity: $capacity, Number: $number"
    }
}