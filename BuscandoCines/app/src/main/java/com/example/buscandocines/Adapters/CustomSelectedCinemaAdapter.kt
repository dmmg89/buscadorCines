package com.example.buscandocines.Adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.buscandocines.API.CinemaDataClass
import com.example.buscandocines.MainActivity
import com.example.buscandocines.R
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt


class CustomSelectedCinemaAdapter(private val entryList: List<CinemaDataClass>): RecyclerView.Adapter<CustomSelectedCinemaAdapter.ViewHolder>() {




    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.selected_cinema_layout, viewGroup, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int  = entryList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataItem = entryList[position]
        val userLatitude = MainActivity.CurrentUbication.latitude
        val userLongitude = MainActivity.CurrentUbication.longitude
        val cinemaLatitude = dataItem.latitude.toDouble()
        val cinemaLongitude = dataItem.longitude.toDouble()

        holder.itemName.text = dataItem.name
        holder.itemDistance.text = String.format("%.2f  km", distanceKM(cinemaLatitude,userLatitude,cinemaLongitude,userLongitude))
        holder.itemButton.setOnClickListener {
            val latitud = dataItem.latitude.toDouble()
            val longitud  = dataItem.longitude.toDouble()
            val mark = dataItem.franchise + "  " + dataItem.name
            val gmmIntenUri = Uri.parse("geo:$latitud,$longitud?q=$latitud,$longitud($mark)")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntenUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            holder.itemView.context.startActivity(mapIntent)



        }
    }


    inner class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        var itemName :TextView
        var itemDistance : TextView
        var itemButton : Button

        init {
            itemName = itemView.findViewById(R.id.cinema_name)
            itemDistance = itemView.findViewById(R.id.cinema_distance)
            itemButton = itemView.findViewById(R.id.cinema_maps_button)
            itemButton.setBackgroundColor(itemView.context.resources.getColor(R.color.secundaryColor))
        }
    }

    private fun distanceKM (lat1:Double, long1:Double, lat2:Double, long2:Double): Double{
        val earthRadius = 6371.0
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(long2 - long1)
        val a = sin(dLat / 2) * sin(dLat / 2) + cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) * sin(dLon / 2) * sin(dLon / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return c*earthRadius
    }

}