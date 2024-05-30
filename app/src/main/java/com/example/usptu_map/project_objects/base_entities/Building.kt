package com.example.usptu_map.project_objects.base_entities

import android.os.Parcel
import android.os.Parcelable

open class Building(
    val name: String = "default",
    val address: String = "default",
    val coordinates: ParcelablePoint = ParcelablePoint(0.0, 0.0), // Широта и долгота
    val coordinatesExit: ParcelablePoint = ParcelablePoint(0.0, 0.0), // Широта и долгота
    val type: String = "default",
    val buildingPolygonPoints: List<ParcelablePoint> = listOf()
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "default", // Обработка null с использованием Элвис-оператора
        parcel.readString() ?: "default",
        parcel.readParcelable<ParcelablePoint>(ParcelablePoint::class.java.classLoader) ?: ParcelablePoint(0.0, 0.0),
        parcel.readParcelable<ParcelablePoint>(ParcelablePoint::class.java.classLoader) ?: ParcelablePoint(0.0, 0.0),
        parcel.readString() ?: "default",
        parcel.createTypedArrayList(ParcelablePoint.CREATOR) ?: listOf() // Обработка null списка
    )

    fun getInfo(): String {
        return "Building Name: $name, Address: $address, Type: $type"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(address)
        parcel.writeParcelable(coordinates, flags)
        parcel.writeParcelable(coordinatesExit, flags)
        parcel.writeString(type)
        parcel.writeTypedList(buildingPolygonPoints)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Building> {
        override fun createFromParcel(parcel: Parcel): Building {
            return Building(parcel)
        }

        override fun newArray(size: Int): Array<Building?> {
            return arrayOfNulls(size)
        }
    }
}