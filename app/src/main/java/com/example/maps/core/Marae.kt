package com.example.maps.core

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
    val Feedback: Any,
    /* Unknown */
    val GIS_MID: Int,
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
    /* String of containing tags separated by spaces that could be used to search for this Marae in a search engine*/
    val Search: String,
    /* String any sources of information of this Marae */
    val Source: String,
    /* Unknown */
    val TKM_MID: Int,
    /* String for the TPK(Te Puni Kōkiri) region that this Marae is located in*/
    val TPK_Region: String,
    /* String the type of this Marae, e.g. tribal */
    val Type: String,
    /* String for the name of the Wharenui belonging to this Marae*/
    val Wharenui: String,
    /** X coordinate of this Marae */
    val X: Double,
    /** Y coordinate of this Marae */
    val Y: Double,
)