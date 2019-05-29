package com.example.programovich.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.programovich.Help.Image
import com.example.programovich.Help.Jsons
import com.example.programovich.R

class DetailsFragment : Fragment() {
    val jsons= Jsons()
    companion object {
        const val DATA_KEY = "data"
        @JvmStatic
        fun newInstance(element: Image) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(DATA_KEY, jsons.itemToJson(element))
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_fragment, container, false)
        val name = view.findViewById<TextView>(R.id.name)
        val date = view.findViewById<TextView>(R.id.date)
        val tags = view.findViewById<TextView>(R.id.tags)

        val element = jsons.itemFromJson(arguments?.getString(DATA_KEY)!!)

        name.text = element.name
        date.text = element.date

        // val tgs = element?.tags!!.joinToString(" #", prefix = "#")
        tags.text = element.tags

        return view
    }
}