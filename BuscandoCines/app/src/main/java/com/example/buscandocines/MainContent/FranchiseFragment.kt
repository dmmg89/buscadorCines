package com.example.buscandocines.MainContent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buscandocines.Adapters.CustomCinemaAdapter
import com.example.buscandocines.R




class FranchiseFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_franchise, container, false)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerViewFranchise = view.findViewById<RecyclerView>(R.id.franchiseRecycler)
        val adapter = CustomCinemaAdapter(this)
        recyclerViewFranchise.layoutManager = LinearLayoutManager(context)
        recyclerViewFranchise.adapter = adapter

    }


}