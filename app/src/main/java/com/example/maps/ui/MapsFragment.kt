package com.example.maps.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.maps.R
import com.example.maps.core.Marae
import com.example.maps.core.MyItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager


/**
 * Fragment to show a Maps view of Marae around NZ
 *
 * Uses the Google Maps to display Marae
 *
 * Intended to be the main landing fragment when you launch the app
 *
 * @author Harry Pirrit
 */
class MapsFragment : Fragment(), GoogleMap.OnMarkerClickListener, InfoWindowAdapter  {

    private var myContentsView: View? = null
    private lateinit var clusterManager: ClusterManager<MyItem>

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

        var maraeList: ArrayList<Marae> = arguments?.getParcelableArrayList<Marae>("maraeList") as ArrayList<Marae>

        val pos = LatLng(maraeList[0].Y, maraeList[0].X)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(pos))
        //googleMap.setInfoWindowAdapter(this)



        if (maraeList == null){
            for (marae in maraeList) {
                val LL = LatLng(marae.Y, marae.X)
                val marker: Marker = googleMap.addMarker(MarkerOptions().position(LL).title(marae.Name))!!
                marker.tag = marae
                println(marker.tag.toString())
                mMarkers.add(marker)
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
                val item = MyItem(lat, lng, "${i.Name}", "", "${i.Iwi}" , "${i.TPK_Region}", "${i.Location}")
                clusterManager.addItem(item)
            }
        }

        fun setUpClusterer() {
            var lat = maraeList.get(0).Y
            var lng = maraeList.get(0).X

            // Position the map.
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lat,lng), 0f))

            // Initialize the manager with the context and the map.
            // (Activity extends context, so we can pass 'this' in the constructor.)
            clusterManager = ClusterManager(context, googleMap)

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
        val ma: Marae = p0.tag as Marae
        val iwi = myContentsView?.findViewById<TextView>(R.id.iwi)
        val title = myContentsView?.findViewById<TextView>(R.id.title)
        val region = myContentsView?.findViewById<TextView>(R.id.region)
        val location = myContentsView?.findViewById<TextView>(R.id.location)
        if (iwi != null) {
            if (ma.Iwi == ""){
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
        }
        return myContentsView

    }

    fun onInfoWindowClick(p0: Marker) {
        val ma: Marae = p0.tag as Marae
        val action = WikiFragmentDirections.actionWikiFragmentToMaraeFragment(ma)
        findNavController().navigate(action)
    }
}