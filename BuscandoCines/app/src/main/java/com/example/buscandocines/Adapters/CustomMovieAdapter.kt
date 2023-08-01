package com.example.buscandocines.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.buscandocines.MovieOption.SelectedMovie
import com.example.buscandocines.R

class CustomMovieAdapter : RecyclerView.Adapter<CustomMovieAdapter.ViewHolder>(){


    val titles = mutableListOf<String>("Oppenheimer",
                                        "Barbie",
                                        "La consagración",
                                         "Elementos")


    val details = arrayOf("Clasificación: B15 \n Duración 183 min",
        "Clasificación: B \n Duración: 115 min",
        "Clasificación B15 \n Duración: 90 min",
        "Clasificación: A \n Duración 110 min"
    )


   /* val images = arrayOf(R.drawable.oppenheimer_cartel,
        R.drawable.barbie_cartel,
        R.drawable.consagracion_cartel,
        R.drawable.elementos_cartel)*/




   
    override fun getItemCount(): Int = titles.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.movie_card_layout, viewGroup, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
       viewHolder.itemTitle.text = titles[position]
        viewHolder.itemDetails.text = details[position]

      /* viewHolder.itemImage.setImageResource(images[position])*/


        viewHolder.itemButton.setOnClickListener{

            val context = viewHolder.itemView.context
            val intent = Intent(context, SelectedMovie::class.java)
            intent.putExtra("Pelicula", "Nombre")
            context.startActivity(intent)


        }

    }

    inner class  ViewHolder(itemView :View):RecyclerView.ViewHolder(itemView){
        var itemImage : ImageView
        var itemTitle: TextView
        var itemDetails: TextView
        var itemButton: Button

        init {
            itemImage = itemView.findViewById(R.id.movie_image)
            itemTitle = itemView.findViewById(R.id.movie_title)
            itemDetails = itemView.findViewById(R.id.movie_details)
            itemButton = itemView.findViewById(R.id.movie_button)
            itemButton.setBackgroundColor(itemView.context.resources.getColor(R.color.secundaryColor))
        }

    }

}