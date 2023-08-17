package com.example.buscandocines.MovieOption

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buscandocines.API.APIQuery
import com.example.buscandocines.API.MovieDataClass
import com.example.buscandocines.Adapters.CustomSelectedMovieAdapter
import com.example.buscandocines.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

val BASE_URL = "https://my-json-server.typicode.com/dmmg89/dbMovies/"

class SelectedMovieContentFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewContent = inflater.inflate(R.layout.fragment_selected_movie_content, container, false)
        // Inflate the layout for this fragment
        return viewContent
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity2 = activity as SelectedMovie

        val movieSelected = activity2.intent.getStringExtra("Movie")
        val recyclerView = view.findViewById<RecyclerView>(R.id.selectedMovieRecycler)

        runBlocking {
            launch(Dispatchers.IO) {
                try {
                    Log.d(TAG, movieSelected.toString())
                    val query = getMovieFromAPI(movieSelected.toString())
                    Log.d(TAG, query.toString())
                    val adapter = CustomSelectedMovieAdapter(query)
                    Log.d(TAG, "Adaptador puesto")
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    recyclerView.adapter = adapter
                }catch (e:Exception){
                    Log.d(TAG, "Consulta y adaptador no conectados", e.cause)
                }
            }
        }
    }

    private suspend fun getMovieFromAPI(movie:String): List<MovieDataClass>{
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        Log.d(TAG, "Retrofit inicializado")

        val apiService = retrofit.create(APIQuery::class.java)
        val call = apiService.getMovieByName(movie)
        return call
    }

    fun newInstance(movie: String):SelectedMovieContentFragment{
        val fragment = SelectedMovieContentFragment()
        val args = Bundle()
        args.putString("SelectedMovie", movie)
        fragment.arguments = args
        return fragment
    }

}