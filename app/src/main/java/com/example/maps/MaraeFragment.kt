package com.example.maps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.maps.core.Marae
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.fragment_marae.*

/**
 * A simple [Fragment] subclass.
 * Use the [MaraeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MaraeFragment : Fragment() {

    private lateinit var chosenMarae : Marae;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_marae, container, false)
    }

    private fun addContent() {
        findChosenMarae()
        addMaraeInfo()
        addStreetView()
        addMap()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addContent();
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

    private fun addMaraeInfo() {
        maraeIwiTextView.text = """${chosenMarae.Name}"""
        maraeIwiTextView.text = """Iwi: ${chosenMarae.Iwi}""";
        maraeHapuTextView.text = """Hapu: ${chosenMarae.Hapu}"""
        maraeAddressTextView.text = """Address: ${chosenMarae.Location}"""
    }
}