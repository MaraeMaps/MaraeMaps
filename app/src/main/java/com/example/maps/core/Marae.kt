package com.example.maps.core

import android.os.Parcel
import android.os.Parcelable

/**
 * Data class for a Marae
 *
 * @author Lucy Sladden
 */
data class Marae(
    /** String for alternate name of this */
    val Alternate: String?,
    /* String for any comments that accompany this Marae*/
    val Comments: String?,
    /* String a link to provide any feedback on this Marae */
    val Feedback: String?,
    /* Unknown */
    val GIS_MID: Int,
    /* Link to Google Maps for this Marae*/
    val GoogleMaps: String?,
    /* String that this Marae belongs to */
    val Hapu: String?,
    /* String that this Marae belongs to */
    val Iwi: String?,
    /* Location of this Marae, ie address*/
    val Location: String?,
    /* String for the name of this Marae*/
    val Name: String?,
    /* String of containing tags separated by spaces that could be used to search for this Marae in a search engine*/
    val Search: String?,
    /* String any sources of information of this Marae */
    val Source: String?,
    /* Unknown */
    val TKM_MID: Int,
    /* String for the TPK(Te Puni Kōkiri) region that this Marae is located in*/
    val TPK_Region: String?,
    /* String the type of this Marae, e.g. tribal */
    val Type: String?,
    /* String for the name of the Wharenui belonging to this Marae*/
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
    )
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

    /**
     * Creator object to create Parcelable Marae Objects.
     *
     * @author Harry Pirrit
     */
    companion object CREATOR : Parcelable.Creator<Marae> {
        override fun createFromParcel(parcel: Parcel): Marae {
            return Marae(parcel)
        }

        override fun newArray(size: Int): Array<Marae?> {
            return arrayOfNulls(size)
        }
    }
}
