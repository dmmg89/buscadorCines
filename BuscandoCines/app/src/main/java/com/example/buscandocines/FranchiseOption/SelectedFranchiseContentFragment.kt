package com.example.buscandocines.FranchiseOption

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
import com.example.buscandocines.API.CinemaDataClass
import com.example.buscandocines.Adapters.CustomSelectedCinemaAdapter
import com.example.buscandocines.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val BASE_URL = "https://my-json-server.typicode.com/dmmg89/dbMovies/"

class SelectedFranchiseContentFragment : Fragment() {





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewContent = inflater.inflate(R.layout.fragment_selected_franchise_content, container, false)

        return viewContent
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val franchiseFragment = arguments?.getString("SelectedFranchise")

        val franchiseFragment = arguments?.getString("SelectedFranchise")


        val recyclerView = view.findViewById<RecyclerView>(R.id.selectedFranchiseRecycler)


        runBlocking {
            launch (Dispatchers.IO){
                try {
                    Log.d(TAG, franchiseFragment.toString())
                    val consulta = getDataFromAPI(franchiseFragment.toString())
                    Log.d(TAG, consulta.toString())
                    val adapter =  CustomSelectedCinemaAdapter(consulta)
                    Log.d(TAG,"Adaptador puesto")
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    recyclerView.adapter = adapter
                }catch (e:Exception){
                    Log.d(TAG,"Consulta y adaptador no conectados", e.cause)
                }
            }
        }
    }



    private suspend fun getDataFromAPI(franchise: String): List<CinemaDataClass> {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        Log.d(TAG,"Retrofit inicializado")

        val apiService = retrofit.create(APIQuery::class.java)
        var call = apiService.getCinemaByFranchise(franchise)

        return call
    }

    fun newInstance(franchise: String):SelectedFranchiseContentFragment{
        val fragment = SelectedFranchiseContentFragment()
        val args = Bundle()
        args.putString("SelectedFranchise", franchise)
        fragment.arguments = args
        return fragment
    }

   /* companion object{
        fun newInstance(franchise: String):SelectedFranchiseContentFragment{
            val fragment = SelectedFranchiseContentFragment()
            val args = Bundle()
            args.putString("SelectedFranchise", franchise)
            fragment.arguments = args
            return fragment
        }
    }*/

}