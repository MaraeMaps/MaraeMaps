package com.example.maps.core

import android.content.Context
import com.example.maps.ui.MainActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import kotlinx.coroutines.channels.ticker

/**
 *
 * This class looks after the customisation of our google maps cluster items.
 *
 * it sets the image for the marker icon and sets the text for the info pop-ups.
 *
 * @author Lucy Sladden
 *
 */
class CustomClusterRenderer(
    context: Context, map: GoogleMap?,
    clusterManager: ClusterManager<MainActivity.MyItem?>?
) :
    DefaultClusterRenderer<MainActivity.MyItem?>(context, map, clusterManager) {
    private val mContext: Context
    override fun onBeforeClusterItemRendered(
        item: MainActivity.MyItem,
        markerOptions: MarkerOptions
    ) {
        val markerDescriptor: BitmapDescriptor = BitmapDescriptorFactory.defaultMarker(
            BitmapDescriptorFactory.HUE_ORANGE);

        markerOptions.icon(markerDescriptor).snippet(item!!.snippet).title(item!!.title);
    }

    init {
        mContext = context
    }
}