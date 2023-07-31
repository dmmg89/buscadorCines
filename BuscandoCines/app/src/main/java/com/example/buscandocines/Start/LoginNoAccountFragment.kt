package com.example.buscandocines.Start

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.buscandocines.MainActivity
import com.example.buscandocines.R
import com.example.buscandocines.databinding.FragmentLoginNoAccountBinding


class LoginNoAccountFragment : Fragment() {

    lateinit var binding: FragmentLoginNoAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginNoAccountBinding.inflate(layoutInflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.noAccountSingIn.setOnClickListener {
            try{
                Log.i(TAG, "Intento de Ingresar a MainActivity")
                val intent = Intent(context,MainActivity::class.java)
                startActivity(intent)
                Log.d(TAG, "Intento terminado")
            }catch (e:Exception){
                Log.w(TAG,"Actividad no inicializada")
            }
        }
    }

}