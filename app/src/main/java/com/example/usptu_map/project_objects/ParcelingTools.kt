package com.example.usptu_map.project_objects

import android.os.Parcel
import android.os.Parcelable

object ParcelingTools {
    fun <T : Parcelable> T.toByteArray(): ByteArray {
        val parcel = Parcel.obtain()
        this.writeToParcel(parcel, 0)
        val bytes = parcel.marshall()
        parcel.recycle()
        return bytes
    }

    fun <T> ByteArray.toParcelable(parcelableCreator: Parcelable.Creator<T>): T? {
        val parcel = Parcel.obtain()
        parcel.unmarshall(this, 0, this.size)
        parcel.setDataPosition(0)
        val result = parcelableCreator.createFromParcel(parcel)
        parcel.recycle()
        return result
    }
}