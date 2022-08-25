package com.example.maps

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.maps.core.Marae
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment(), GoogleMap.OnMarkerClickListener,GoogleMap.InfoWindowAdapter  {

    private var myContentsView: View? = null

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        myContentsView = layoutInflater.inflate(R.layout.popup, null);

        // Create a Marker array and iterate through marae to add them to the map
        var mMarkers: java.util.ArrayList<Marker> = java.util.ArrayList()


        //This stuff to end is the issue.
        var maraeArray: Array<Marae> = requireArguments().getParcelableArray("maraeArray") as Array<Marae>

        val pos = LatLng(maraeArray[0].Y, maraeArray[0].X)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(pos))
        googleMap.setInfoWindowAdapter(this)


        if (maraeArray != null){
            for (marae in maraeArray) {
                val LL = LatLng(marae.Y, marae.X)
                val marker: Marker = googleMap.addMarker(MarkerOptions().position(LL).title(marae.Name))
                marker.tag = marae
                println(marker.tag.toString())
                mMarkers.add(marker)
        }
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
        val ma: Marae = p0.tag as Marae
        val iwi = myContentsView?.findViewById<TextView>(com.example.maps.R.id.iwi)
        val title = myContentsView?.findViewById<TextView>(com.example.maps.R.id.title)
        val region = myContentsView?.findViewById<TextView>(com.example.maps.R.id.region)
        val location = myContentsView?.findViewById<TextView>(com.example.maps.R.id.location)
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

    /**
     * Required. Will create a new instance of Maps fragment with a Bundle.
     * Bundle has our data in it.
     */
    companion object{

        fun newMapFrag(): MapsFragment{
            return MapsFragment()
        }
    }
}