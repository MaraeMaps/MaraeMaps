package com.example.maps.ui.notifications

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.maps.R

/**
 *
 * Settings fragment that will load the preferences set out in root_preferences.xml.
 *
 */

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

}