package com.example.maps.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.maps.*
import com.example.maps.core.Marae
import com.example.maps.core.MaraeController
import com.example.maps.core.MaraeController.Companion.addMaraeDetail
import java.text.Normalizer
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
     * Custom ViewHolder class
     *
     *
     * @param view View that this ViewHolder holds
     */
    class MaraeWikiEntryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val maraeLocationTV: TextView;
        val maraeIwiTV: TextView;
        val maraeNameTV: TextView;
        val holdingView: View

        init {
            maraeNameTV = view.findViewById(R.id.marae_name_tv)
            maraeIwiTV = view.findViewById(R.id.marae_iwi_tv);
            maraeLocationTV = view.findViewById(R.id.marae_location_tv);
            holdingView = view
        }

        /**
         * Adds an on click listener to the view that this ViewHolder has
         */
        fun addListener(marae: Marae) {
            val action = WikiFragmentDirections.actionWikiFragmentToMaraeFragment(marae)
            holdingView.setOnClickListener {
                holdingView.findNavController().navigate(action)
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
     * Binds a new MaraeEntryVH to this Adapter.
     *
     * Adds marae detail to the inputted holder
     *
     * @param holder MaraeWikiEntryViewHolder to be binded.
     * @param position Int for the position of a Marae (in list of Marae that the RecyclerView holds) that the inputted holder should show
     */
    override fun onBindViewHolder(holder: MaraeWikiEntryViewHolder, position: Int) {
        val currentMarae = maraeListShown[position]
        holder.addListener(currentMarae)
        holder.maraeNameTV.text = currentMarae.Name
        val resources = holder.holdingView.resources
        addMaraeDetail(holder.maraeIwiTV, currentMarae.Iwi, resources)
        addMaraeDetail(holder.maraeLocationTV, currentMarae.Location, resources)
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
                var searchStrings = constraint.toString().split(" ").toTypedArray();
                searchStrings = searchStrings.map { normalizeString(it) }.toTypedArray()
                for (marae in maraeList) {
                    if (!shouldFilterMarae(MaraeController.keyWords(marae), searchStrings)) {
                        newMarae.add(marae);
                    }
                }
            }
            val filterResults = FilterResults()
            filterResults.values = newMarae;
            return filterResults
        }

        /**
         * Filtering helper for helping searching marae
         *
         * Decides if a marae should be added or not based on it's keywords
         *
         * Search strings should be normalized first using normalizeString(str)
         *
         * @return Boolean false if a marae should be added to marae to be seen, otherwise true
         */
        private fun shouldFilterMarae(
            maraeKeyWords: Array<String?>,
            searchStrings: Array<String>
        ): Boolean {
            for (keyWord in maraeKeyWords) {
                if (keyWord == null) {
                    continue
                }
                val keyWordAdj = normalizeString(keyWord);
                for (searchString in searchStrings) {
                    if (keyWordAdj.contains(searchString)) {
                        return false
                    }
                }
            }
            return true;
        }

        /**
         * Normalizes a String, turns it into lowercase, removes accents etc
         *
         * Credit to https://stackoverflow.com/questions/51731574/removing-accents-and-diacritics-in-kotlin
         *
         * @param str String to be normalized
         * @return Inputted String normalized
         */
        private fun normalizeString(str: String): String {
            val REGEX_UNACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()
            val temp = Normalizer.normalize(str.lowercase(), Normalizer.Form.NFD)
            return REGEX_UNACCENT.replace(temp, "").trim()
        }

        /**
         * Publishes the results of filtering to the RecyclerView
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