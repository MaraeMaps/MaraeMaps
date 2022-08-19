package com.example.maps.core

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Data class for a Marae class
 *
 * @author Lucy Sladden
 */
@Parcelize
data class Marae(
    val Alternate: String?,
    val Comments: String?,
    val Feedback: String?,
    val GIS_MID: Int,
    /* Link to google maps for this Marae*/
    val GoogleMaps: String?,
    val Hapu: String?,
    val Iwi: String?,
    /* Location of this Marae, ie address*/
    val Location: String?,
    val Name: String?,
    val Search: String?,
    val Source: String?,
    val TKM_MID: Int,
    val TPK_Region: String?,
    val Type: String?,
    val Wharenui: String?,
    /** X coordinate of this Marae */
    val X: Double,
    /** Y coordinate of this Marae */
    val Y: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        Alternate = parcel.readString(),
        Comments = parcel.readString(),
        Feedback = parcel.readString(),
        GIS_MID = parcel.readInt(),
        GoogleMaps = parcel.readString(),
        Hapu = parcel.readString(),
        Iwi = parcel.readString(),
        Location = parcel.readString(),
        Name = parcel.readString(),
        Search = parcel.readString(),
        Source = parcel.readString(),
        TKM_MID = parcel.readInt(),
        TPK_Region = parcel.readString(),
        Type = parcel.readString(),
        Wharenui = parcel.readString(),
        X =  parcel.readDouble(),
        Y = parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Alternate)
        parcel.writeString(Comments)
        parcel.writeString(Feedback)
        parcel.writeInt(GIS_MID)
        parcel.writeString(GoogleMaps)
        parcel.writeString(Hapu)
        parcel.writeString(Iwi)
        parcel.writeString(Location)
        parcel.writeString(Name)
        parcel.writeString(Search)
        parcel.writeString(Source)
        parcel.writeInt(TKM_MID)
        parcel.writeString(TPK_Region)
        parcel.writeString(Type)
        parcel.writeString(Wharenui)
        parcel.writeDouble(X)
        parcel.writeDouble(Y)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Marae> {
        override fun createFromParcel(parcel: Parcel): Marae {
            return Marae(parcel)
        }

        override fun newArray(size: Int): Array<Marae?> {
            return arrayOfNulls(size)
        }
    }
}