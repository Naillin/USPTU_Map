package com.example.usptu_map.project_objects.base_entities

import android.os.Parcel
import android.os.Parcelable
import com.yandex.mapkit.geometry.Point

data class ParcelablePoint(
    val latitude: Double,
    val longitude: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble()
    ) {
    }

    fun toMapKitPoint(): Point = Point(latitude, longitude)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ParcelablePoint> {
        override fun createFromParcel(parcel: Parcel): ParcelablePoint {
            return ParcelablePoint(parcel)
        }

        override fun newArray(size: Int): Array<ParcelablePoint?> {
            return arrayOfNulls(size)
        }

        fun fromMapKitPoint(point: Point): ParcelablePoint = ParcelablePoint(point.latitude, point.longitude)
    }
}
