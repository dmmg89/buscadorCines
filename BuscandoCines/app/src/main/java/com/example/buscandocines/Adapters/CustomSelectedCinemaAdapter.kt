package com.example.buscandocines.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.buscandocines.API.CinemaDataClass
import com.example.buscandocines.R

class CustomSelectedCinemaAdapter(private val entryList: List<CinemaDataClass>): RecyclerView.Adapter<CustomSelectedCinemaAdapter.ViewHolder>() {




    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.selected_cinema_layout, viewGroup, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int  = entryList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataItem = entryList[position]
        holder.itemName.text = dataItem.name
        holder.itemDistance.text = "0 metros"
        holder.itemButton.setOnClickListener {

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

}