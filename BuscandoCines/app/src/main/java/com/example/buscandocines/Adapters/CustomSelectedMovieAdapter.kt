package com.example.buscandocines.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.buscandocines.API.MovieDataClass
import com.example.buscandocines.R

class CustomSelectedMovieAdapter(private val entryList : List<MovieDataClass>):
    RecyclerView.Adapter<CustomSelectedMovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        position: Int
    ): CustomSelectedMovieAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.selected_movie_layout, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = entryList.size

    override fun onBindViewHolder(holder: CustomSelectedMovieAdapter.ViewHolder, position: Int) {
       val dataItem = entryList[position]

        holder.itemFranchise.text = dataItem.franchise

        holder.itemName.text = dataItem.cinema
        holder.itemTime.text = dataItem.time.toString()
        holder.itemButton.setOnClickListener {

        }
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var itemFranchise : TextView
        var itemName : TextView
        var itemTime : TextView
        var itemButton : Button
        init {
            itemFranchise = itemView.findViewById(R.id.franchise_name)
            itemName = itemView.findViewById(R.id.cinema_name)
            itemTime = itemView.findViewById(R.id.cinema_time)
            itemButton = itemView.findViewById(R.id.cinema_maps_button)
        }
    }

}