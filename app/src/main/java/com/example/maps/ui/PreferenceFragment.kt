package com.example.maps.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.maps.R
import java.util.*

/**
 * A fragment for different settings preferences that a user can choose
 *
 * Is placed inside a SettingsFragmmetn
 *
 * @author Kavan Chay, Harry Pirrit and Hugo Phibbs
 */
class PreferenceFragment : PreferenceFragmentCompat() {

    /** Listener to hear for any changes to which app language should be used*/
    private lateinit var preferenceChangeListener: SharedPreferences.OnSharedPreferenceChangeListener;

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        initPreferenceChangeListener()
        updateLanguageSummary()
    }

    /**
     * Initializes the Preference change listener that listens to changes from users adjusting settings
     */
    private fun initPreferenceChangeListener() {
        preferenceChangeListener =
            SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
                updateLanguageSummary()
                updateLocale(sharedPreferences.getString(key, "")!!);
                requireActivity().recreate()
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferenceScreen.sharedPreferences!!.registerOnSharedPreferenceChangeListener(
            preferenceChangeListener
        );
        updateLanguageSummary()
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences!!.unregisterOnSharedPreferenceChangeListener(
            preferenceChangeListener
        )
    }

    /**
     * Updates the font size setting summary
     */
    private fun updateLanguageSummary() {
        val prefLanguage = "key_language"
        val langPref: Preference? = findPreference(prefLanguage);
        langPref!!.summary = String.format(
            resources.getString(R.string.language_summary),
            preferenceScreen.sharedPreferences!!.getString(prefLanguage, "")
        )
    }

    /**
     * Updates the Locale of the app as set by a user
     *
     * @param loc String for the locale to be set.
     */
    private fun updateLocale(loc: String) {
        val locale = Locale(loc);
        Locale.setDefault(locale)
        val resources = activity?.getResources();
        val config = resources?.configuration;
        config?.setLocale(locale);
        resources?.updateConfiguration(config, resources!!.displayMetrics)
    }

}