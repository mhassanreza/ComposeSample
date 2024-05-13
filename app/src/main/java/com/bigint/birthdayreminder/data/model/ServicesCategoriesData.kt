package com.bigint.birthdayreminder.data.model

import android.os.Parcel
import android.os.Parcelable

data class ServicesCategoriesData(
    val createdOn: String,
    val iconUrl: String,
    val id: Int,
    val name: String,
    val title: String?,
    val showOnHomeScreen: Boolean,
    val sortOrder: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(createdOn)
        parcel.writeString(iconUrl)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(title)
        parcel.writeByte(if (showOnHomeScreen) 1 else 0)
        parcel.writeInt(sortOrder)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ServicesCategoriesData> {
        override fun createFromParcel(parcel: Parcel): ServicesCategoriesData {
            return ServicesCategoriesData(parcel)
        }

        override fun newArray(size: Int): Array<ServicesCategoriesData?> {
            return arrayOfNulls(size)
        }
    }
}