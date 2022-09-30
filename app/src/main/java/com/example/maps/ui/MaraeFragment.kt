package com.example.maps.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.maps.R
import com.example.maps.core.Marae
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.StreetViewPanoramaCamera
import kotlinx.android.synthetic.main.fragment_marae.*


/**
 * Fragment to give a detailed breakdown of a chosen Marae.
 *
 * Provides a rich view. With a Google Street and Maps View
 *
 * @author Hugo Phibbs
 */
class MaraeFragment : Fragment(), OnMapReadyCallback, OnStreetViewPanoramaReadyCallback {

    /** Current Marae detailed on this Fragment */
    private lateinit var chosenMarae: Marae;

    /** Google MapsView of a particular Marae */
    private lateinit var maraeMapView: MapView;

    /** Google StreetViewPanoramaView of a particular Marae */
    private lateinit var maraeStreetView: StreetViewPanoramaView;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_marae, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addContent()
        initMapView(view, savedInstanceState)
        initStreetView(view, savedInstanceState)
    }

    private fun initMapView(view: View, savedInstanceState: Bundle?) {
        maraeMapView = view.findViewById(R.id.maraeMapView) as MapView
        maraeMapView.onCreate(savedInstanceState)
        maraeMapView.onResume()
        maraeMapView.getMapAsync(this)
    }

    private fun initStreetView(view: View, savedInstanceState: Bundle?) {
        maraeStreetView = view.findViewById(R.id.maraeStreetView) as StreetViewPanoramaView
        maraeStreetView.onCreate(savedInstanceState)
        maraeStreetView.onResume()
        maraeStreetView.getStreetViewPanoramaAsync(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        maraeMapView.onResume()
        maraeStreetView.onResume()
        super.onResume()
        addFragmentTitle()
    }

    override fun onPause() {
        maraeMapView.onResume()
        maraeStreetView.onResume()
        super.onPause()
    }

    override fun onDestroy() {
        maraeMapView.onDestroy()
        maraeStreetView.onDestroy()
        super.onDestroy()
    }

    override fun onStart() {
        maraeMapView.onStart()
        maraeStreetView.onStart()
        super.onStart()
    }

    override fun onStop() {
        maraeMapView.onStart()
        maraeStreetView.onStart()
        super.onStop()
    }

    override fun onLowMemory() {
        maraeMapView.onLowMemory()
        maraeStreetView.onLowMemory()
        super.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        maraeMapView.onSaveInstanceState(outState)
        maraeStreetView.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    /**
     * Adds ui content to this Fragment, calls supporting methods
     */
    private fun addContent() {
        findChosenMarae()
        addMaraeInfo()
    }

    /**
     * Adds a title to this fragment.
     *
     * Title is just the current marae that we are interested in
     */
    private fun addFragmentTitle() {
        (requireActivity() as MainActivity).setActionBarTitle(chosenMarae.Name);
    }

    /**
     * Finds and sets the Marae that this MaraeFragment is for
     */
    private fun findChosenMarae() {
        chosenMarae = arguments?.getParcelable("chosenMarae")!!;
    }

    /**
     * Adds text to this fragment providing information on the currently selected marae
     */
    private fun addMaraeInfo() {
        maraeTitleTextView.text = chosenMarae.Name
        addMaraeDetail(maraeIwiTextView, chosenMarae.Iwi)
        addMaraeDetail(maraeAddressTextView, chosenMarae.Location)
        addMaraeDetail(maraeHapuTextView, chosenMarae.Hapu)
        addMaraeDetail(maraeWharenuiTextView, chosenMarae.Wharenui)
    }

    private fun addMaraeDetail(textView: TextView, detail : String?) {
        if (detail == "") {
            textView.text = resources.getString(R.string.detail_not_found_label)
            textView.setTextColor(resources.getColor(R.color.light_gray));
        }
        else {
            textView.text = detail;
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val latLng = LatLng(chosenMarae.Y, chosenMarae.X)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap.addMarker(MarkerOptions().position(latLng).title(chosenMarae.Name))
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(10F))
    }

    override fun onStreetViewPanoramaReady(streetViewPanorama: StreetViewPanorama) {
        streetViewPanorama.setPosition(LatLng(chosenMarae.Y, chosenMarae.X))

        streetViewPanorama.setOnStreetViewPanoramaChangeListener { streetViewPanoramaLocation ->
            if (!(streetViewPanoramaLocation != null && streetViewPanoramaLocation.links != null)) {
                maraeStreetView.visibility = View.GONE;
                maraeStreetViewSubTitle.text = resources.getString(R.string.street_view_not_found)
                maraeStreetViewSubTitle.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            }
        }
    }
}