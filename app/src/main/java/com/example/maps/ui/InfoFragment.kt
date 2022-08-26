package com.example.maps.ui

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat


/**
 * A fragment representing our list of settings.
 * @author Kavan Chay
 */

class InfoFragment : PreferenceFragmentCompat() {

    private var ourPreference: ListPreference? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(com.example.maps.R.xml.root_preferences, rootKey)
    }

}