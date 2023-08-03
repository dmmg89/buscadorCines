package com.example.buscandocines.MovieOption

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.buscandocines.R

lateinit var movie:String
lateinit var details:String




class SelectedMovie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_movie)

        movie = intent.getStringExtra("Movie").toString()
        details = intent.getStringExtra("Details").toString()
        Log.d(TAG, "Movie en activity " + movie)

        /*if(savedInstanceState == null){*/
            supportFragmentManager.beginTransaction()
                .add(R.id.movieSelectedTop,SelectedMovieTopFragment())
                .commit()
      /*  }*/

        val fragmentBody = SelectedMovieContentFragment()
        /*val bundle = Bundle()
        bundle.putString("SelectedMovie", movie)
        bundle.putString("MovieDetails", details)
        fragmentBody.arguments = bundle*/
        supportFragmentManager.beginTransaction()
            .add(R.id.movieSelectedRecycler,fragmentBody)
            .commit()


    }
}