package com.example.buscandocines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.buscandocines.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //binding - elementos ligados
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bottomNavigationView.menu.findItem(R.id.moviesBottom)




    }



    private fun replaceFragmentContent(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }


}