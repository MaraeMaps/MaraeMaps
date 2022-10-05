package com.example.maps.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.maps.R
import com.example.maps.core.Marae
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.maps.android.clustering.ClusterItem
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
    private fun setupNav(maraeListBundle: Bundle) {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        val navController = getNavHostController()
        navController.setGraph(R.navigation.nav_graph, maraeListBundle);

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.mapsFragment,
                R.id.wikiFragment,
                R.id.settingsFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            navController.navigate(item.itemId, maraeListBundle)
            true
        }
    }

    /**
     * Called when a user navigates up. Overrides arent method.
     *
     * Intended to allow back navigation button to work in sub fragments.
     */
    override fun onSupportNavigateUp(): Boolean {
        val navController = getNavHostController()
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    /**
     * Sets the title of the action bar in this activity
     *
     * Intended to used by fragments that aren't in the navigation graph, hence don't have a hard coded title
     */
    fun setActionBarTitle(title: String?) {
        supportActionBar!!.title = title
    }

    /**
     * Gets the navigation controller for the navigation host of this main activity
     *
     * Refactored for usability
     */
    private fun getNavHostController(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostContainerView) as NavHostFragment
        return navHostFragment.navController
    }

    /**
     * Creates a Bundle object for passing a list of marae between fragments
     *
     * @return Bundle object as described
     */
    private fun createMaraeListBundle(): Bundle {
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
    private fun getMaraeList(bufferedReader: BufferedReader): ArrayList<Marae> {
        val jsonString = bufferedReader.use(BufferedReader::readText)
        val arrayMaraeType = object : TypeToken<ArrayList<Marae>>() {}.type
        return Gson().fromJson(jsonString, arrayMaraeType)
    }

    /**
     * Custom ClusterItem class that is used for displaying Marae on the clustered map
     *
     * @author Lucy Sladden
     */
    class MaraeItem(lat: Double, lng: Double, marae: Marae, title: String, snippet: String) :
        ClusterItem {

        /* Position of this item (marae) */
        private val position: LatLng

        /* Marae object a being displayed by this item*/
        private val marae: Marae

        /* Title of a marae marker */
        private var title: String

        /* Snippet text that is shown for a marae marker*/
        private var snippet: String

        init {
            position = LatLng(lat, lng)
            this.marae = marae
            this.title = title
            this.snippet = snippet
        }


        override fun getPosition(): LatLng {
            return position
        }

        override fun getTitle(): String? {
            return title
        }

        override fun getSnippet(): String? {
            return if (marae.Iwi == "") {
                "Iwi information not available\n\nLocation: " + marae.Location + "\n\nRegion: " + marae.TPK_Region
            } else {
                "Iwi: " + marae.Iwi + "\n\nLocation: " + marae.Location + "\n\nRegion: " + marae.TPK_Region
            }
        }

        /**
         * Marae object that this MaraeItem contains.
         */
        fun getMarae(): Marae {
            return marae
        }
    }

}