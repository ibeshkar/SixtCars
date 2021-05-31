package com.example.sixtcars.data.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Car(
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("modelIdentifier")
    var modelIdentifier: String? = "",
    @SerializedName("modelName")
    var modelName: String? = "",
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("make")
    var make: String? = "",
    @SerializedName("group")
    var group: String? = "",
    @SerializedName("color")
    var color: String? = "",
    @SerializedName("series")
    var series: String? = "",
    @SerializedName("fuelType")
    var fuelType: String? = "",
    @SerializedName("fuelLevel")
    var fuelLevel: Double = 0.0,
    @SerializedName("transmission")
    var transmission: String? = "",
    @SerializedName("licensePlate")
    var licensePlate: String? = "",
    @SerializedName("latitude")
    var latitude: Double = 0.0,
    @SerializedName("longitude")
    var longitude: Double = 0.0,
    @SerializedName("innerCleanliness")
    var innerCleanliness: String? = "",
    @SerializedName("carImageUrl")
    var carImageUrl: String? = ""
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(modelIdentifier)
        parcel.writeString(modelName)
        parcel.writeString(name)
        parcel.writeString(make)
        parcel.writeString(group)
        parcel.writeString(color)
        parcel.writeString(series)
        parcel.writeString(fuelType)
        parcel.writeDouble(fuelLevel)
        parcel.writeString(transmission)
        parcel.writeString(licensePlate)
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
        parcel.writeString(innerCleanliness)
        parcel.writeString(carImageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Car> {
        override fun createFromParcel(parcel: Parcel): Car {
            return Car(parcel)
        }

        override fun newArray(size: Int): Array<Car?> {
            return arrayOfNulls(size)
        }
    }
}