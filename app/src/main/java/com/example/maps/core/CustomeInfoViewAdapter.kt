package com.example.maps.core


import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.maps.R
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
import com.google.android.gms.maps.model.Marker


class CustomInfoViewAdapter(private val mInflater: LayoutInflater) : InfoWindowAdapter {
    override fun getInfoWindow(marker: Marker): View? {


        return null;
    }

    override fun getInfoContents(p0: Marker): View? {
        //val ma: Marae = p0.tag as Marae
        val popup: View = mInflater.inflate(R.layout.popup, null)
        popup.findViewById<TextView>(R.id.title).text = p0.title
        popup.findViewById<TextView>(R.id.iwi).text = p0.snippet

        /*val iwi = popup.findViewById<TextView>(R.id.iwi)
        val title = popup.findViewById<TextView>(R.id.title)
        val region = popup.findViewById<TextView>(R.id.region)
        val location = popup.findViewById<TextView>(R.id.location)
        if (iwi != null) {
            if (p0 == ""){
                iwi.text = "Iwi information not available"
            } else {
                iwi.text = "Iwi: " + ma.Iwi
            }
        }
        if (title != null) {
            title.text = ma.Name
        }
        if (region != null) {
            region.text = "Region: " + ma.TPK_Region
        }
        if (location != null) {
            location.text = "Address: " + ma.Location
        }*/
        return popup

    }
}