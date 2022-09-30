package com.example.maps.ui

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.maps.R

/**
 * A fragment for different settings preferences that a user can choose
 *
 * @author Kavan Chay
 */

class PreferenceFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}