package com.example.buscandocines.MovieOption

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.buscandocines.R


class SelectedMovieTopFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewTop = inflater.inflate(R.layout.fragment_selected_movie_top, container, false)
        val activity2 = activity as SelectedMovie
        var cartel = viewTop.findViewById<ImageView>(R.id.selectedMovieImage)
        var title = viewTop.findViewById<TextView>(R.id.selectedMovieTitle)
        var details = viewTop.findViewById<TextView>(R.id.selectedMovieDetails)
        val movieName = activity2.intent.getStringExtra("Movie")
        val movieDetails = activity2.intent.getStringExtra("Details")
        Log.i(TAG, "activity " +  movieName)


        title.text = movieName
        details.text = movieDetails




        /*when(activity2.intent.getStringExtra("Movie")){



        }*/


        return viewTop
    }



}