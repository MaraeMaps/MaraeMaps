package com.example.maps.ui.notifications

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.maps.R

/**
 * Fragment to allow user to change settings.
 */

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

}