package com.example.maps.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.maps.R
import com.example.maps.core.Marae
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * The main [Activity] that is used for this application
 *
 * Since our application only runs with one Activity, this is the single Activity that will be used
 * throughout the application
 *
 * @author Harry Pirrit, Lucy Sladden, Kavan Chay
 */
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    /**
     * Main method that is called when this Activity is created.
     *
     * Does necessary setup work in order for this Activity to function properly
     *
     * @param savedInstanceState Bundle containing information on the previous state of this activity
     */
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
        val navController = getNavHostController()
        navController.setGraph(R.navigation.nav_graph, maraeListBundle);

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.mapsFragment,
            R.id.wikiFragment,
            R.id.infoFragment
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            navController.navigate(item.itemId, maraeListBundle)
            true
        }
    }

    fun setActionBarTitle(title: String?) {
        supportActionBar!!.title = title
    }

    /**
     * Gets the navigation controller for the navigation host of this main activity
     *
     * Refactored for usability
     */
    private fun getNavHostController() : NavController {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostContainerView) as NavHostFragment
        return navHostFragment.navController
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = getNavHostController()
        return navController.navigateUp() || super.onSupportNavigateUp()
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
    
    /**
     * Gets a list of all the Marae to be used in this app
     *
     * @param bufferedReader [BufferedReader] to be used to read in a Marae data file
     * @return ArrayList of Marae to be used
     */
    private fun getMaraeList(bufferedReader : BufferedReader): ArrayList<Marae> {
        // TODO initialize bufferedReader in here, not outside?
        val jsonString = bufferedReader.use(BufferedReader::readText)
        val arrayMaraeType = object : TypeToken<ArrayList<Marae>>() {}.type
        return Gson().fromJson(jsonString, arrayMaraeType)
    }
}