package com.example.maps.core

import android.content.Context
import com.example.maps.ui.MainActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer

/**
 *
 * This class looks after the customisation of our Google Maps ClusterItems.
 *
 * It sets the image for the marker icon and sets the text for the info pop-ups.
 *
 * @author Lucy Sladden
 *
 */
class CustomClusterRenderer(
    context: Context, map: GoogleMap?,
    clusterManager: ClusterManager<MainActivity.MaraeItem?>?
) :
    DefaultClusterRenderer<MainActivity.MaraeItem?>(context, map, clusterManager) {

    override fun onBeforeClusterItemRendered(
        item: MainActivity.MaraeItem,
        markerOptions: MarkerOptions
    ) {
        val markerDescriptor: BitmapDescriptor = BitmapDescriptorFactory.defaultMarker(
            BitmapDescriptorFactory.HUE_ORANGE
        );
        markerOptions.icon(markerDescriptor).snippet(item!!.snippet).title(item!!.title);
    }
}