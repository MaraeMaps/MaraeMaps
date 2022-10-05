package com.example.maps.ui

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.navigation.fragment.findNavController
import com.example.maps.R
import com.example.maps.core.CustomClusterRenderer
import com.example.maps.core.Marae

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.ClusterManager.*

/**
 * Fragment to show a Maps view of Marae around NZ
 *
 * Uses the Google Maps to display Marae
 *
 * Intended to be the main landing fragment when you launch the app
 *
 * @author Harry Pirrit
 */
class MapsFragment : Fragment(), GoogleMap.OnMarkerClickListener, InfoWindowAdapter {

    private var myContentsView: View? = null
    private lateinit var clusterManager: ClusterManager<MainActivity.MaraeItem?>
    private var chosenMarae: Marae? = null;

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        myContentsView = layoutInflater.inflate(R.layout.popup, null);

        var maraeList: ArrayList<Marae> =
            arguments?.getParcelableArrayList<Marae>("maraeList") as ArrayList<Marae>

        googleMap.setInfoWindowAdapter(this)

        clusterManager = ClusterManager(context, googleMap)

        val renderer = CustomClusterRenderer(requireContext(), googleMap, clusterManager)
        clusterManager.renderer = renderer

        clusterManager.setOnClusterItemClickListener(
            OnClusterItemClickListener {
                // if true, click handling stops here and do not show info view, do not move camera
                // you can avoid this by calling:
                // renderer.getMarker(clusterItem).showInfoWindow();
                false
            })

        clusterManager.markerCollection.setInfoWindowAdapter(this)
        googleMap.setInfoWindowAdapter(clusterManager.markerManager)
        clusterManager.setOnClusterItemInfoWindowClickListener { stringClusterItem ->

            val action = MapsFragmentDirections.actionMapsFragmentToMaraeFragment(stringClusterItem!!.getMarae())
            findNavController().navigate(action)

        }


        if (maraeList != null) {
            for (marae in maraeList) {
                val LL = LatLng(marae.Y, marae.X)
                //val marker: Marker = googleMap.addMarker(MarkerOptions().position(LL).title(marae.Name))!!
                //marker.tag = marae
                //println(marker.tag.toString())
                //mMarkers.add(marker)
            }
        }

        fun addItems() {
            // Add cluster items (markers) to the cluster manager.
            var lat = maraeList.get(0).Y
            var lng = maraeList.get(0).X

            // Add ten cluster items in close proximity, for purposes of this example.
            for (i in maraeList) {
                lat = i.Y
                lng = i.X
                val item = MainActivity.MaraeItem(lat, lng, i, "${i.Name}", "${i.Iwi}"
                )
                clusterManager.addItem(item)
            }
        }

        fun setUpClusterer() {
            // Position the map.
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-41.276601, 173.275072), 5f))
            // Initialize the manager with the context and the map.
            // (Activity extends context, so we can pass 'this' in the constructor.)

            // Point the map's listeners at the listeners implemented by the cluster
            // manager.
            googleMap.setOnCameraIdleListener(clusterManager)
            googleMap.setOnMarkerClickListener(clusterManager)

            addItems()
        }
        setUpClusterer()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onMarkerClick(p0: Marker): Boolean {
        TODO("Not yet implemented")
    }

    override fun getInfoWindow(p0: Marker): View? {
        return null
    }

    override fun getInfoContents(p0: Marker): View? {
        val iwi = myContentsView?.findViewById<TextView>(R.id.iwi)
        val title = myContentsView?.findViewById<TextView>(R.id.title)
        if (iwi != null) {
            if (p0.snippet == "") {
                "Iwi information not available".also { iwi.text = it }
            } else {
                (p0.snippet).also { iwi.text = it }
            }
        }
        if (title != null) {
            title.text = p0.title
        }
        return myContentsView

    }
}
