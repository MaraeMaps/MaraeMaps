package com.example.maps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.maps.com.example.maps.WikiAdapter
import com.example.maps.core.Marae

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 *
 * @author Hugo Phibbs
 * @param maraeList list of all Marae that can be shown on this fragment
 */
class WikiFragment : Fragment() {

    /**
     * RecyclerView for showing Marae to a user
     */
    private lateinit var recyclerView: RecyclerView;

    /**
     * Search view for searching marae from the RecyclerView
     */
    private lateinit var maraeSearchView: SearchView;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_wiki, container, false)
        addComponentsToView(view);
        // Return the created view
        return view;
    }

    fun addComponentsToView(view: View) {
        initRecyclerView(view)
        maraeSearchView = view.findViewById(R.id.maraeSearchView);
        addSearchListener()
    }

    /**
     * Initialiazes the RecyclerView for different Marae
     *
     * Binds it to it's adapter etc
     *
     * @param view View object of this fragment
     */
    fun initRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.wikiRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.itemAnimator = DefaultItemAnimator()
        val maraeList: ArrayList<Marae> =
            arguments?.getParcelableArrayList<Marae>("maraeList") as ArrayList<Marae>
        recyclerView.adapter = WikiAdapter(maraeList)
    }

    /**
     * Adds a search listener to the search box
     */
    fun addSearchListener() {
        maraeSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                (recyclerView.adapter as WikiAdapter).filter.filter(query)
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                (recyclerView.adapter as WikiAdapter).filter.filter(query)
                return false // TODO anymore to add here, look into what this does!
            }

        })
    }
}