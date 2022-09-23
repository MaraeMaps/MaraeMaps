package com.example.maps.core

import android.content.Context
import com.example.maps.ui.MainActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.example.maps.ui.*
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory


class CustomClusterRenderer (
    context: Context, map: GoogleMap?,
    clusterManager: ClusterManager<MainActivity.MyItem?>?
) :
    DefaultClusterRenderer<MainActivity.MyItem?>(context, map, clusterManager) {
    private val mContext: Context
    override fun onBeforeClusterItemRendered(
        item: MainActivity.MyItem?,
        markerOptions: MarkerOptions?
    ) {
        val markerDescriptor: BitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE);
        markerOptions!!.icon(markerDescriptor).snippet(item!!.getIwi());
    }

    init {
        mContext = context
    }
}