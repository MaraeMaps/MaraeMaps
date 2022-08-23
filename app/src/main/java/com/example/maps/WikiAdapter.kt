package com.example.maps.com.example.maps


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.maps.R
import com.example.maps.core.Marae
import com.example.maps.core.MaraeController
import java.util.*
import kotlin.collections.ArrayList

/**
 * Custom [RecyclerView.Adapter] class that helps display Marae wiki entries to a user
 *
 * @param maraeList ArrayList of marae to be displayed
 * @author Hugo Phibbs
 */
class WikiAdapter(private val maraeList: ArrayList<Marae>) :
    RecyclerView.Adapter<WikiAdapter.MaraeWikiEntryViewHolder>(), Filterable {

    /** List of marae actually shown to a user, changes according to user input*/
    var maraeListShown = ArrayList<Marae>(maraeList)

    /**
     * Custom ViewHolder class to display Marae info in the wiki view
     *
     * @param view View that this ViewHolder holds
     */
    class MaraeWikiEntryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val descriptionTV: TextView;

        init {
            descriptionTV = view.findViewById(R.id.marae_description_tv);
            addListener()
        }

        /**
         * Adds an on click listener to the view that this ViewHolder has
         */
        private fun addListener() {
            descriptionTV.setOnClickListener {
                // TODO: Open a new marae info screen
            }
        }
    }

    /**
     * Creates a MaraeEntryView object when this Adapter is created.
     *
     * The aforementioned MaraeEntryView is then used for individual entries in the Recycler view contained in this Fragment
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaraeWikiEntryViewHolder {
        // What does attach to root actually do?
        val maraeEntryView =
            LayoutInflater.from(parent.context).inflate(R.layout.marae_wiki_entry, parent, false)
        return MaraeWikiEntryViewHolder(maraeEntryView);
    }

    /**
     * Binds a new MaraeEntryVH to this Adapter
     *
     * @param holder MaraeWikiEntryViewHolder to be binded.
     * @param position Int for the position of a Marae (in list of Marae that the RecyclerView holds) that the inputted holder should show
     */
    override fun onBindViewHolder(holder: MaraeWikiEntryViewHolder, position: Int) {
        holder.descriptionTV.text = MaraeController.maraeToString(maraeListShown[position])
    }

    /**
     * Gets the number of items(Marae) that can be possibly viewed by the RecyclerView that this Adapter manages
     *
     * @return Int for number of items as described
     */
    override fun getItemCount(): Int {
        return maraeListShown.size;
    }

    /**
     * Gets the Filter that is used to filter displayed results of the RecyclerView that this Adapter manages
     *
     * @return Filter filter object that is used for filtering Marae as mentioned
     */
    override fun getFilter(): Filter {
        return maraeFilter;
    }

    /**
     * Simple custom Filter class to filter out Marae results, based on a search String
     *
     * @author Hugo Phibbs
     */
    private val maraeFilter = object : Filter() {

        /**
         * Performs the filtering of Marae to be displayed
         *
         * @param constraint CharSequence entered by a user that is used to filter results
         */
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val newMarae = ArrayList<Marae>();

            if (constraint === null || constraint.isEmpty()) {
                newMarae.addAll(maraeList)
            } else {
                // TODO may need to handle Maori accents
                val constraintAdjusted = constraint.toString().lowercase(Locale.ROOT).trim()
                for (marae in maraeList) {
                    for (keyWord in MaraeController.keyWords(marae)) {
                        if (keyWord.lowercase().contains(constraintAdjusted)) {
                            newMarae.add(marae)
                        }
                    }
                }
            }
            val filterResults = FilterResults()
            filterResults.values = newMarae;
            return filterResults
        }

        /**
         * Publishes the results of filtering to the RecyclerVIew
         *
         * @param constraint CharSequence that was used to filter Marae shown
         * @param constraint filterResults FilterResults object that contains the results of filtering
         */
        override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
            maraeListShown.clear()
            if (filterResults != null) {
                maraeListShown.addAll(filterResults.values as ArrayList<Marae>)
            }
            notifyDataSetChanged()
        }

    }
}