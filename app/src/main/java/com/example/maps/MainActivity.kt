package com.example.maps

import androidx.preference.PreferenceManager
import com.example.maps.MapsFragment
import com.example.maps.R
import com.example.maps.WikiFragment
import android.os.Bundle
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.maps.core.Marae
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    /**
     *
     * Main activity that hosts the navigation controller.
     * Default settings are loaded upon opening of app.
     * Bottom bar with buttons to click onto fragments for functionality also loaded.
     *
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {

            val bufferedReader = InputStreamReader(assets.open("Marae.json")).buffered()
            val maraeArray = getMaraeCollection(bufferedReader)
            val bundle: Bundle = Bundle()

            bundle.putParcelableArray("maraeArray", maraeArray)

            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<MapsFragment>(R.id.nav_host_frag, args = bundle)
            }
        }

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<NavHostFragment>(R.id.nav_host_frag)
        }

        PreferenceManager.setDefaultValues(baseContext, R.xml.root_preferences, false)

        val navView: BottomNavigationView = findViewById(R.id.bottom_nav)

        val navController = findNavController(R.id.nav_host_frag)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.maps, R.id.wiki, R.id.settings
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    /**
     * Switches to the wiki fragment as per a user's request
     */
    fun switchToWikiFragment() {
        // TODO generalise this method?
        supportFragmentManager.commit {
            replace<WikiFragment>(R.id.nav_host_frag)
            setReorderingAllowed(true)
            addToBackStack(null)// TODO set a name?
        }
    }

    fun getMaraeCollection(bufferedReader : BufferedReader): Array<Marae> {

        val jsonString = bufferedReader.use(BufferedReader::readText)

        val arrayMaraeType = object : TypeToken<Array<Marae>>() {}.type
        return Gson().fromJson(jsonString, arrayMaraeType)
    }
}