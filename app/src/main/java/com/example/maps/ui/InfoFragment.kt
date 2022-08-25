package com.example.maps.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceFragmentCompat
import com.example.maps.R
import com.example.maps.ui.placeholder.PlaceholderContent

/**
 * A fragment representing our list of info: settings, about us, credits.
 * @author Kavan Chay
 */

class InfoFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}