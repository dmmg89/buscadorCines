package com.example.buscandocines.FranchiseOption

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.buscandocines.API.APIQuery
import com.example.buscandocines.API.CinemaDataClass
import com.example.buscandocines.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class SelectedFranchise : AppCompatActivity() {

    val arreglo = mutableListOf<String>()
/*
    val BASE_URL = "https://my-json-server.typicode.com/dmmg89/dbMovies/"*/


        lateinit var franchise: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_franchise)



        franchise = intent.getStringExtra("Franchise").toString()
        Log.d(TAG, "Franquicia en activity" + franchise)




     /*   val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(APIQuery::class.java)
        runBlocking {
            launch (Dispatchers.IO){
                try {

                    var query = apiService.getCinemaByFranchise(franchise)
                    query.forEach{element ->

                        arreglo.add(element.name)

                    }

                   *//* Log.d(TAG, "Solicitud de API")
                    Log.d(TAG, "franchise" + apiService.getCinemaByFranchise(franchise).toString())
                   *//**//* Log.d(TAG, "cinema" + apiService.getAllCinema().toString())*//**//*
                   *//**//* Log.d(TAG, "id" + apiService.getCinemaByID(2).toString())*//*
                    Log.i(TAG, "Solicitud API Exitosa")
                    Log.i(TAG, arreglo[1].toString())

                }catch (e:Exception){
                    Log.w(TAG,"Error en consulta")
                }
            }

        }*/



        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.theaterSelectedTop, SelectedFranchiseTopFragment())
                .commit()
        }



        val fragmentBody = SelectedFranchiseContentFragment()
        val bundle = Bundle()
        bundle.putString("SelectedFranchise", franchise)
        fragmentBody.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.theaterSelectedRecycler, fragmentBody)
            .commit()

    }



}