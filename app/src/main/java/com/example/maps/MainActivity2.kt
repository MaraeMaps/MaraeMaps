package com.example.maps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.maps.core.Marae
import com.example.maps.databinding.ActivityMain2Binding
import com.example.maps.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity2 : AppCompatActivity(R.layout.activity_main2) {
    private lateinit var binding: ActivityMain2Binding

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
//
//
        val navController = findNavController(R.id.nav_host_fragment_activity_main2)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_maps, R.id.navigation_wiki, R.id.navigation_settings
            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        R.id.nav_host_fragment_container.setupWithNavController(navController)
    }

    fun getMaraeCollection(bufferedReader : BufferedReader): Array<Marae> {

        val jsonString = bufferedReader.use(BufferedReader::readText)

        val arrayMaraeType = object : TypeToken<Array<Marae>>() {}.type
        return Gson().fromJson(jsonString, arrayMaraeType)
    }
}
