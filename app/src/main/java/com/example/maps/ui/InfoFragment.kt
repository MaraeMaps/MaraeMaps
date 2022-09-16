package com.example.maps.ui

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.maps.R

/**
 * A fragment representing our list of info: settings, about us, credits.
 * @author Kavan Chay
 */

class InfoFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}