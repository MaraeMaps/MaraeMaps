package com.example.maps.core

import android.content.res.Resources
import android.widget.TextView
import com.example.maps.R

/**
 * Class for manipulating and formatting Marae
 *
 * Meant to accompany Marae data class.
 *
 * Provides general utility methods.
 *
 * @author Hugo Phibbs
 */
class MaraeController {

    /**
     * Companion object used for accessing static methods of MaraeController
     */
    companion object {

        /**
         * Returns a String representation of an inputted Marae
         *
         * @param marae Marae object to find a string representation for
         */
        fun maraeToString(marae: Marae): String {
            return "Marae object with name: ${marae.Name}, belonging to ${marae.Iwi}, located in ${marae.Location}"
        }

        /**
         * Function to return the keywords of a Marae
         *
         * Intended to be used for searching a list of marae
         *
         * @param marae Marae to find the keywords for
         * @return array of Strings as described
         */
        fun keyWords(marae: Marae): Array<String?> {
            return arrayOf(marae.Name, marae.Iwi, marae.Location)
        }

        /**
         * Adds detail for a particular field.
         *
         * Handles if the detail is empty.
         *
         * Refactored here for re-useablity for other ui classes =
         *
         * @param textView TextView object that displays the detail field of a particular marae.
         * @param detail String for a marae detail to be shown
         * @param resources Resources object that is used for this ui
         */
        fun addMaraeDetail(textView: TextView, detail: String?, resources : Resources) {
            if (detail == "") {
                textView.text = resources.getString(R.string.detail_not_found_label)
                textView.setTextColor(resources.getColor(R.color.light_gray));
            } else {
                textView.text = detail;
            }
        }
    }


}