package com.example.buscandocines.MainContent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buscandocines.Adapters.CustomMovieAdapter
import com.example.buscandocines.R


class MoviesFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewMovie = inflater.inflate(R.layout.fragment_movies, container, false)

        // Inflate the layout for this fragment
        return viewMovie
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerViewMovies = view.findViewById<RecyclerView>(R.id.moviesRecycler)
        val adapter = CustomMovieAdapter()

        recyclerViewMovies.layoutManager = LinearLayoutManager(context)
        recyclerViewMovies.adapter = adapter
    }



}