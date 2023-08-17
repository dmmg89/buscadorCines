package com.example.buscandocines.MainContent

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buscandocines.API.APIQuery
import com.example.buscandocines.API.MovieDataClass
import com.example.buscandocines.API.MovieLightDataClass
import com.example.buscandocines.Adapters.CustomMovieAdapter
import com.example.buscandocines.R
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

val URL_MOVIES = "https://my-json-server.typicode.com/dmmg89/dbMovies/"

val URL_MOVIES = "https://my-json-server.typicode.com/dmmg89/dbMovies/"

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
        val spinnerWithDays = view.findViewById<Spinner>(R.id.spinnerDays)
        val listDaysString = mutableListOf<String>()

        for(item in getListOfDays()){
            listDaysString.add(formatterFunction(item))
        }

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listDaysString)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerWithDays.adapter = adapter

        spinnerWithDays.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                val daySelected = listDaysString[position]

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }



        runBlocking {
            launch {
                try {
                    val query = getAllMovies()
                    Log.d(TAG, "Consulta exitosa")
                    val adapter = CustomMovieAdapter(lightenerList(query))
                    Log.d(TAG, "Consulta en adaptador")
                    recyclerViewMovies.layoutManager = GridLayoutManager(context,2)
                    recyclerViewMovies.adapter = adapter
                }catch (e:Exception){
                    Log.d(TAG, "Consulta de películas no exitosa", e.cause)
                }
            }
        }



    }

    private fun lightenerList(rawData : List<MovieDataClass>): List<MovieLightDataClass>{
        val setMovies = mutableSetOf<MovieLightDataClass>()

        try {
            Log.d(TAG, "Inicial lightener")
            for(index in 1..rawData.size){
                Log.d(TAG,"Inicio bucle")
                val itemList = rawData[index]
                var movieName = itemList.name
                var movieDetails = itemList.details
                var movieDay = itemList.day

                var movieLight = MovieLightDataClass(movieName,movieDetails,movieDay)
                Log.d(TAG,"Creado")

                setMovies.add(movieLight)
            }

        }catch (e:Exception){
            Log.d(TAG, "No se concluyó lightener", e.cause)
        }

       return setMovies.toList()
    }

    private fun formatterFunction(rawDate: LocalDate): String{

        val formatter = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM", Locale("es","ES"))
        return rawDate.format(formatter)
    }

    private fun getListOfDays(): List<LocalDate>{
        val currentDay = LocalDate.now()
        var nextDay = currentDay.plusDays(1)
        val listOfDays = mutableListOf<LocalDate>()
        listOfDays.add(currentDay)

        while (nextDay.dayOfWeek != DayOfWeek.WEDNESDAY){
            listOfDays.add(nextDay)
            nextDay = nextDay.plusDays(1)

        }
        return listOfDays
    }

    private suspend fun getAllMovies():List<MovieDataClass>{
        val retrofit = Retrofit.Builder().baseUrl(URL_MOVIES)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        Log.d(TAG, "Retrofit iniciado")

        val apiService = retrofit.create(APIQuery::class.java)
        var call = apiService.getAllMoviesFromAPI()
        return call
    }

    private fun lightenerList(rawData : List<MovieDataClass>): List<MovieLightDataClass>{
        val setMovies = mutableSetOf<MovieLightDataClass>()

        try {
            Log.d(TAG, "Inicial lightener")
            for(index in 1..rawData.size){
                Log.d(TAG,"Inicio bucle")
                val itemList = rawData[index]
                var movieName = itemList.name
                var movieDetails = itemList.details
                var movieDay = itemList.day

                var movieLight = MovieLightDataClass(movieName,movieDetails,movieDay)
                Log.d(TAG,"Creado")

                setMovies.add(movieLight)
            }

        }catch (e:Exception){
            Log.d(TAG, "No se concluyó lightener", e.cause)
        }

       return setMovies.toList()
    }

    private fun formatterFunction(rawDate: LocalDate): String{

        val formatter = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM", Locale("es","ES"))
        return rawDate.format(formatter)
    }

    private fun getListOfDays(): List<LocalDate>{
        val currentDay = LocalDate.now()
        var nextDay = currentDay.plusDays(1)
        val listOfDays = mutableListOf<LocalDate>()
        listOfDays.add(currentDay)

        while (nextDay.dayOfWeek != DayOfWeek.WEDNESDAY){
            listOfDays.add(nextDay)
            nextDay = nextDay.plusDays(1)

        }
        return listOfDays
    }

}