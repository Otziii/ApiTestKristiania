package com.jorfald.apitest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class QuoteFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var customLayoutManager: LinearLayoutManager
    private lateinit var customAdapter: CustomAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quote, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        customLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        customAdapter = CustomAdapter(
            listOf(
                HolderClass("Hei", 452),
                HolderClass("på", 64),
                HolderClass("deg", 523),
                HolderClass("Hei", 452),
                HolderClass("på", 64),
                HolderClass("deg", 523),
                HolderClass("Hei", 452),
                HolderClass("på", 64),
                HolderClass("deg", 523),
                HolderClass("Hei", 452),
                HolderClass("på", 64),
                HolderClass("deg", 523),
                HolderClass("Hei", 452),
                HolderClass("på", 64),
                HolderClass("deg", 523),
                HolderClass("Hei", 452),
                HolderClass("på", 64),
                HolderClass("deg", 523),
                HolderClass("Hei", 452),
                HolderClass("på", 64),
                HolderClass("deg", 523),
                HolderClass("Hei", 452),
                HolderClass("på", 64),
                HolderClass("deg", 523),
                HolderClass("Hei", 452),
                HolderClass("på", 64),
                HolderClass("deg", 523),
                HolderClass("Hei", 452),
                HolderClass("på", 64),
                HolderClass("deg", 523),
                HolderClass("Hei", 452),
                HolderClass("på", 64),
                HolderClass("deg", 523),
                HolderClass("Hei", 452),
                HolderClass("på", 64),
                HolderClass("deg", 523)
            ),
            { clickedPosition ->
                Log.d("CLICKED", clickedPosition.toString())
            },
            { position ->
                Log.d("CLICKED", "Small click: $position")
            }
        )

        recyclerView.layoutManager = customLayoutManager
        recyclerView.adapter = customAdapter
    }
}