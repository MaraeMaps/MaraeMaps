package com.example.maps

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Environment

import android.os.Bundle
import android.os.Parcelable
import android.system.Os.open
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.example.maps.R
import com.example.maps.core.Marae
import com.example.maps.core.MaraeController

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.channels.AsynchronousFileChannel.open
import kotlin.reflect.typeOf

//private val Parcelable.X: Double
//    get() {return }
//private val Parcelable.Y: Double
//    get() {return -44.44}
//private val Parcelable.Name: String
//    get() {return "Test Name Marae"}

class MapsFragment : Fragment() {



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

        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        // Create a Marker array and iterate through marae to add them to the map
        var mMarkers: java.util.ArrayList<Marker> = java.util.ArrayList()

        val maraeArray = requireArguments().getParcelableArray("maraeArray")

        if (maraeArray != null){
            for (marae in maraeArray) {
                println("-- -- -- --")
                println("MaraeX : ${marae}")
                println("-- -- -- --")

                val LL = LatLng(marae.Y, marae.X)
                mMarkers.add(
                    googleMap.addMarker(MarkerOptions().position(LL).title(marae.Name))
            )
        }
        }





    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val maraeArray = getArguments()?.getParcelableArray("maraeArray")
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}