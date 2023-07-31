package com.example.buscandocines.Adapters

import android.content.ContentValues
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.buscandocines.FranchiseOption.SelectedFranchise
import com.example.buscandocines.MainContent.FranchiseFragment
import com.example.buscandocines.R

class CustomCinemaAdapter(val context: FranchiseFragment): RecyclerView.Adapter<CustomCinemaAdapter.ViewHolder>() {

    val franchiseData = FranchiseEnum.values()


    val franchise = arrayOf(FranchiseEnum.CINEDOT.franchise,
                            FranchiseEnum.CINEPOLIS.franchise,
                            FranchiseEnum.CINEMEX.franchise)
    val franchiseDetails = arrayOf(FranchiseEnum.CINEDOT.slogan,
                            FranchiseEnum.CINEPOLIS.slogan,
                             FranchiseEnum.CINEMEX.slogan)
    val franchiseLogo = arrayOf(
        R.drawable.logo_cinedot,
        R.drawable.logo_cinepolis,
        R.drawable.logo_cinemex
    )

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.cinema_card_layout,viewGroup,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemFranchise.text = franchise[position]
        holder.itemDetails.text = franchiseDetails[position]
        holder.itemImage.setImageResource(franchiseLogo[position])
        holder.itemButton.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, SelectedFranchise::class.java)
            intent.putExtra("Franchise",holder.itemFranchise.text)
            Log.i(ContentValues.TAG,"FRANQUICIA " + holder.itemFranchise.text)
            context.startActivity(intent)
        }

    }


    override fun getItemCount(): Int = franchise.size



    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage : ImageView
        var itemFranchise: TextView
        var itemDetails: TextView
        var itemButton: Button



        init {
            itemImage = itemView.findViewById(R.id.cinema_image)
            itemFranchise = itemView.findViewById(R.id.cinema_title)
            itemDetails = itemView.findViewById(R.id.cinema_details)
            itemButton = itemView.findViewById(R.id.cinema_button)
            itemButton.setBackgroundColor(itemView.context.resources.getColor(R.color.secundaryColor))

        }
    }


}