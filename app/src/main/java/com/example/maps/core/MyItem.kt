package com.example.maps.core

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class MyItem(
    lat: Double,
    lng: Double,
    title: String,
    snippet: String,
    iwi: String,
    region: String,
    address: String,
) : ClusterItem {

    private val position: LatLng
    private val title: String
    private val snippet: String
    private val iwi: String
    private val region: String
    private val address: String



    override fun getPosition(): LatLng {
        return position
    }

    override fun getTitle(): String? {
        return title
    }

    override fun getSnippet(): String? {
        return "Snippet goes here"
//        return "Iwi: ${getIwi()}\nRegion: ${getRegion()}\nAddress: ${getAddress()}"
    }

    fun getIwi(): String? {
        return iwi
    }

    fun getRegion(): String? {
        return region
    }

    fun getAddress(): String? {
        return address
    }



    init {
        position = LatLng(lat, lng)
        this.title = title
        this.snippet = snippet
        this.iwi = iwi
        this.region = region
        this.address = address

    }
}