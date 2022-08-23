package com.example.maps.core

/**
 * Data class for a Marae
 *
 * @author Lucy Sladden
 */
data class Marae(

    /* Link to Google Maps for this Marae*/
    val GoogleMaps: String,
    /* String that this Marae belongs to */
    val Hapu: String,
    /* String that this Marae belongs to */
    val Iwi: String,
    /* Location of this Marae, ie address*/
    val Location: String,
    /* String for the name of this Marae*/
    val Name: String,
    /* String for the name of the Wharenui belonging to this Marae*/
    val Wharenui: String,
    /** X coordinate of this Marae */
    val X: Double,
    /** Y coordinate of this Marae */
    val Y: Double,

    // TODO possibly remove the stuff bellow, I don't think that we need them

    val Type: String,
    val Source: String,
    val TKM_MID: Int,
    val TPK_Region: String,
    val Search: String,
    val GIS_MID: Int,
    val Feedback: Any,
    val Alternate: Any,
    val Comments: Any,
)