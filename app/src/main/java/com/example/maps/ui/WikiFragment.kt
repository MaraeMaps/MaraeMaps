package com.example.maps.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.maps.R
import com.example.maps.core.Marae

/**
 * A [Fragment] class that is used for displaying a list view of Marae to a user
 *
 * @author Hugo Phibbs
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
        return inflater.inflate(R.layout.fragment_wiki, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addComponentsToView()
    }

    /**
     * Adds the components(sub views) to the view of this fragment
     */
    private fun addComponentsToView() {
        initRecyclerView()
        maraeSearchView = requireView().findViewById(R.id.maraeSearchView);
        addSearchListener()
    }

    /**
     * Initializes the RecyclerView for different Marae
     *
     * Binds it to it's adapter etc
     *
     * @param view View object of this fragment
     */
    private fun initRecyclerView() {
        recyclerView = requireView().findViewById(R.id.wikiRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.itemAnimator = DefaultItemAnimator()
        val maraeList: ArrayList<Marae> =
            arguments?.getParcelableArrayList<Marae>("maraeList") as ArrayList<Marae>
        recyclerView.adapter = WikiAdapter(maraeList)
    }

    /**
     * Adds a Listener to the SearchView that this fragment has
     */
    private fun addSearchListener() {
        maraeSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            /**
             * Called when query text entered by a user has been submimted
             *
             * Overrides [SearchView.OnQueryTextListener.onQueryTextSubmit]
             *
             * @return boolean true if the query has been handled by the listener, false to let the SearchView perform the default action.
             */
            override fun onQueryTextSubmit(query: String?): Boolean {
                (recyclerView.adapter as WikiAdapter).filter.filter(query)
                return false
            }


            /**
             * Called when query text entered by a user has changed
             *
             * Overrides [SearchView.OnQueryTextListener.onQueryTextChange]
             *
             * @return boolean, false if the SearchView should display suggestions, otherwise true if nothing is to be done
             */
            override fun onQueryTextChange(newText: String?): Boolean {
                return onQueryTextSubmit(newText)
            }
        })
    }
}