package com.example.maps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.maps.core.Marae
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity2 : AppCompatActivity(R.layout.activity_main2) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val bufferedReader = InputStreamReader(assets.open("Marae.json")).buffered()
            val maraeArray = getMaraeCollection(bufferedReader)

            val bundle: Bundle = Bundle()
            bundle.putSerializable("maraeArray", maraeArray)


            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<MapsFragment>(R.id.fragment_container_view, args = bundle)
            }
        }
    }

    fun getMaraeCollection(bufferedReader : BufferedReader): Array<Marae> {

        // lateinit var jsonString: String
        val jsonString = bufferedReader.use(BufferedReader::readText)

        val arrayMaraeType = object : TypeToken<Array<Marae>>() {}.type
        return Gson().fromJson(jsonString, arrayMaraeType)
    }
}
