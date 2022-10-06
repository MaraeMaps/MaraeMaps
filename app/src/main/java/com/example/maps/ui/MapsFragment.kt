package com.example.maps.ui

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.navigation.fragment.findNavController
import androidx.navigation.Navigation.findNavController
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

import com.google.android.gms.maps.model.MarkerOptions

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

    var freshLaunch = true

    private var myContentsView: View? = null
    private lateinit var clusterManager: ClusterManager<MainActivity.MyItem?>
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

        // Create a Marker array and iterate through marae to add them to the map
        var mMarkers: java.util.ArrayList<Marker> = java.util.ArrayList()

        var maraeList: ArrayList<Marae> =
            arguments?.getParcelableArrayList<Marae>("maraeList") as ArrayList<Marae>

        // centre of NZ is -40.6993, 174.1392
        val centre = LatLng(-41.6993, 174.1932)

        // if first time launching the app
        if (freshLaunch == true) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(centre))
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(5F))

        }
        googleMap.setInfoWindowAdapter(this)

        // cluster manager tasks
            // if its not a fresh launch, don't create new clusterManager
        if (!(freshLaunch == false)) {
            clusterManager = ClusterManager(context, googleMap)
            val renderer = CustomClusterRenderer(requireContext(), googleMap, clusterManager)
            clusterManager.renderer = renderer
            clusterManager.setOnClusterItemClickListener(
                OnClusterItemClickListener {
                    false
                })
            clusterManager.markerCollection.setInfoWindowAdapter(this)
            googleMap.setInfoWindowAdapter(clusterManager.markerManager)
            clusterManager.setOnClusterItemInfoWindowClickListener { stringClusterItem ->
                val action =
                    MapsFragmentDirections.actionMapsFragmentToMaraeFragment(stringClusterItem!!.getMarae())
                findNavController().navigate(action)

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
                val item = MainActivity.MyItem(
                    lat, lng, i, "${i.Name}", "${i.Iwi}"
                )
                clusterManager.addItem(item)
            }
        }

        fun setUpClusterer() {
            // Point the map's listeners at the listeners implemented by the cluster
            // manager.
            googleMap.setOnCameraIdleListener(clusterManager)
            googleMap.setOnMarkerClickListener(clusterManager)

            addItems()
            freshLaunch = false
        }

        if (!(freshLaunch == false)) {
            setUpClusterer()
        }

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
