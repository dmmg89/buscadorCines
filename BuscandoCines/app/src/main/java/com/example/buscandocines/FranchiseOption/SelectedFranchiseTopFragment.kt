package com.example.buscandocines.FranchiseOption

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.buscandocines.Adapters.FranchiseEnum
import com.example.buscandocines.R


class SelectedFranchiseTopFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewTop =inflater.inflate(R.layout.fragment_selected_franchise_top, container, false)

        val activity = activity as SelectedFranchise
        var logo = viewTop.findViewById<ImageView>(R.id.selectedTheaterPicture)
        var title = viewTop.findViewById<TextView>(R.id.selectedTheaterTitle)
        var sloganText = viewTop.findViewById<TextView>(R.id.selectedTheaterSlogan)

        when(activity.franchise){
            FranchiseEnum.CINEDOT.franchise -> {
                logo.setImageResource(R.drawable.logo_cinedot)
                title.text = FranchiseEnum.CINEDOT.franchise
                sloganText.text= FranchiseEnum.CINEDOT.slogan
            }

            FranchiseEnum.CINEPOLIS.franchise -> {
                logo.setImageResource(R.drawable.logo_cinepolis)
                title.text = FranchiseEnum.CINEPOLIS.franchise
                sloganText.text= FranchiseEnum.CINEPOLIS.slogan
            }

            FranchiseEnum.CINEMEX.franchise -> {
                logo.setImageResource(R.drawable.logo_cinemex)
                title.text = FranchiseEnum.CINEMEX.franchise
                sloganText.text = FranchiseEnum.CINEMEX.slogan
            }
        }


        return viewTop
    }



}