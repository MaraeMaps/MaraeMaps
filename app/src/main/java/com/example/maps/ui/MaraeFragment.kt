package com.example.maps.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.maps.R
import com.example.maps.core.Marae
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.fragment_marae.*

/**
 * Fragment to give a detailed breakdown of a chosen Marae.
 *
 * Provides a rich view. With a Google Street and Maps View
 *
 * @author Hugo Phibbs
 */
class MaraeFragment : Fragment() {

    /** Current Marae detailed on this Fragment */
    private lateinit var chosenMarae : Marae;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_marae, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addContent();
    }

    override fun onResume() {
        super.onResume()
        addFragmentTitle()
    }

    /**
     * Adds ui content to this Fragment, calls supporting methods
     */
    private fun addContent() {
        findChosenMarae()
        addMaraeInfo()
        addStreetView()
        addMap()
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
     * Adds Google Street View of the chosen Marae
     */
    private fun addStreetView() {
        maraeStreetView.getStreetViewPanoramaAsync { streetViewPanorama ->
            streetViewPanorama.setPosition(
                LatLng(chosenMarae.X, chosenMarae.Y)
            )
        };
    }

    /**
     * Adds a Google Map View of the chosen marae
     */
    private fun addMap() {
        maraeMapView.getMapAsync(object: OnMapReadyCallback {
            override fun onMapReady(googleMap: GoogleMap) {
                // TODO googleMap.addMarker()
            }
        })
    }

    /**
     * Adds text to this fragment providing information on the currently selected marae
     */
    private fun addMaraeInfo() {
        maraeTitleTextView.text = """${chosenMarae.Name}"""
        maraeIwiTextView.text = """Iwi: ${chosenMarae.Iwi}""";
        maraeHapuTextView.text = """Hapu: ${chosenMarae.Hapu}"""
        maraeAddressTextView.text = """Address: ${chosenMarae.Location}"""
    }
}