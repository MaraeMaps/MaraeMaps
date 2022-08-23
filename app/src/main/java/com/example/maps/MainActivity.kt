package com.example.maps

import android.app.Activity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.maps.core.Marae
import com.example.maps.databinding.ActivityMainBinding
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
class MainActivity : AppCompatActivity() {

    /**
     * Binding that binds this Activity to it's view
     */
    private lateinit var binding: ActivityMainBinding

    /**
     * Main method that is called when this Activity is created.
     *
     * Does necessary setup work in order for this Activity to function properly
     *
     * @param savedInstanceState Bundle containing information on the previous state of this activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bufferedReader = InputStreamReader(assets.open("Marae.json")).buffered()
        val  maraeCollection = getMaraeArray(bufferedReader)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        // What does nav host fragment activity main do?
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
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
            replace<WikiFragment>(R.id.mainContentFragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)// TODO set a name?
        }
    }

    /**
     * Gets a list of all the Marae to be used in this app
     *
     * @param bufferedReader [BufferedReader] to be used to read in a Marae data file
     * @return Array of Marae to be used
     */
    private fun getMaraeArray(bufferedReader : BufferedReader): Array<Marae> {
        // TODO initialize bufferedReader in here, not outside?
        val jsonString = bufferedReader.use(BufferedReader::readText)

        val arrayMaraeType = object : TypeToken<Array<Marae>>() {}.type
        return Gson().fromJson(jsonString, arrayMaraeType)
    }
}