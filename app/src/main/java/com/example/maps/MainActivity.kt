package com.example.maps

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
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
        setupNav(createMaraeListBundle());
    }

    /**
     * Sets up the navigation component of the main activity
     *
     * @param maraeListBundle Bundle containing an arrayList of marae to be passed as arguments
     */
    private fun setupNav(maraeListBundle : Bundle) {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        navController.setGraph(R.navigation.nav_graph, maraeListBundle);

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.mapsFragment, R.id.wikiFragment, R.id.infoFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)

        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            navController.navigate(item.itemId, maraeListBundle)
            true
        }
    }

    /**
     * Creates a Bundle object for passing a list of marae between fragments
     *
     * @return Bundle object as described
     */
    private fun createMaraeListBundle() : Bundle {
        // TODO use saved instance
        val bufferedReader = InputStreamReader(assets.open("Marae.json")).buffered()
        val maraeList = getMaraeList(bufferedReader)
        val bundle = Bundle()
        bundle.putParcelableArrayList("maraeList", maraeList)
        return bundle
    }

    fun getMaraeList(bufferedReader : BufferedReader): ArrayList<Marae> {
        val jsonString = bufferedReader.use(BufferedReader::readText)
        val arrayMaraeType = object : TypeToken<ArrayList<Marae>>() {}.type
        return Gson().fromJson(jsonString, arrayMaraeType)
    }
}