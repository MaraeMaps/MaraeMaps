package com.example.maps.ui

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.maps.R
import kotlinx.android.synthetic.main.fragment_settings.*
import java.util.*

/**
 * Fragment for settings of this app.
 *
 * Includes any actual credits, plus a credits and about us section
 *
 * @author Hugo Phibbs
 */
class SettingsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        updateLocale("mi")
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCreditsParagraph()
    }

    /**
     * Creates the paragraph where a user can find the source information of this app
     */
    private fun initCreditsParagraph() {
        credits.text = Html.fromHtml(getString(R.string.credits))
        Linkify.addLinks(credits, Linkify.WEB_URLS)
        credits.movementMethod = LinkMovementMethod.getInstance();
    }

    private fun updateLocale(loc: String) {
        val locale = Locale(loc);
        Locale.setDefault(locale)
        val resources = activity?.getResources();
        val config = resources?.configuration;
        config?.setLocale(locale);

        resources?.updateConfiguration(config, resources!!.displayMetrics)
    }

}