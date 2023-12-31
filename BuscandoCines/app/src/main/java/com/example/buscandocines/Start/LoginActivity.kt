package com.example.buscandocines.Start

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.buscandocines.MainContent.AccountFragment
import com.example.buscandocines.R
import com.example.buscandocines.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)


        val enterWOAccountBtn = binding.enterWithoutAccountButton
        val enterWAccountBtn = binding.enterWithAccountButton

        loadFragment(LoginAccountFragment())

        enterWAccountBtn.setOnClickListener {
            enterWAccountBtn.setBackgroundColor(getColor(R.color.backDarkColor))
            enterWOAccountBtn.setBackgroundColor(getColor(R.color.grey))
            loadFragment(LoginAccountFragment())
        }

        enterWOAccountBtn.setOnClickListener {
            enterWOAccountBtn.setBackgroundColor(getColor(R.color.backDarkColor))
            enterWAccountBtn.setBackgroundColor(getColor(R.color.grey))
            loadFragment(LoginNoAccountFragment())
        }



    }




    private fun loadFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameContentLogin,fragment)
        fragmentTransaction.commit()
    }



}