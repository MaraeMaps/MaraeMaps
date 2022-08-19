package com.example.maps

import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {

            val bufferedReader = InputStreamReader(assets.open("Marae.json")).buffered()
            val maraeArray = getMaraeCollection(bufferedReader)
            val bundle: Bundle = Bundle()

            bundle.putParcelableArray("maraeArray", maraeArray)

            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<MapsFragment>(R.id.fragment_container_view, args = bundle)
            }
        }

            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<NavHostFragment>(androidx.navigation.fragment.R.id.nav_host_fragment_container)
            }

        val navView: BottomNavigationView = findViewById(R.id.nav_view2)

            val navController = findNavController(R.id.nav_host_fragment_activity_main)

            val appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.navigation_maps, R.id.navigation_wiki, R.id.navigation_settings
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
            replace<WikiFragment>(R.id.map_fragment_container_view)
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