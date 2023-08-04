package com.example.buscandocines.Start

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import com.example.buscandocines.MainActivity
import com.example.buscandocines.R
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginAccountFragment : Fragment() {

lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewContent = inflater.inflate(R.layout.fragment_login_account, container, false)


        return viewContent
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FirebaseApp.initializeApp(view.context)
        auth = Firebase.auth

        val switchAccount = view.findViewById<Switch>(R.id.accountSwitch)
        val emailEdit = view.findViewById<EditText>(R.id.loginEmail)
        val passwordEdit = view.findViewById<EditText>(R.id.loginPassword)
        val passwordConfirmLabel = view.findViewById<TextView>(R.id.userLabelLogin)
        val passwordConfirmEdit = view.findViewById<EditText>(R.id.loginPassword2)
        val registerButton = view.findViewById<Button>(R.id.registerButton)
        val entryButton = view.findViewById<Button>(R.id.loginButton)
        switchAccount.setOnCheckedChangeListener{_, isChecked->
            if(isChecked){

                passwordConfirmLabel.visibility = View.INVISIBLE
                passwordConfirmEdit.visibility = View.INVISIBLE
                registerButton.visibility = View.GONE
                entryButton.visibility = View.VISIBLE


            }else{
                passwordConfirmLabel.visibility = View.VISIBLE
                passwordConfirmEdit.visibility = View.VISIBLE
                registerButton.visibility = View.VISIBLE
                entryButton.visibility = View.GONE
            }
        }

        registerButton.setOnClickListener {
            val email = emailEdit.text
            val password = passwordEdit.text
            val password2 = passwordConfirmEdit.text
            if (password.toString() == password2.toString()){
                createUser(email.toString(), password.toString())

            }else{
                showMessage("Las contraseñas no coinciden")
            }
        }

        entryButton.setOnClickListener {
            val email = emailEdit.text
            val password = passwordEdit.text
            signIn(email.toString(),password.toString())


        }


    }

    private fun showMessage(message:String){
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }

    private fun createUser(email:String,password:String){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            task->
            if(task.isSuccessful){
                showMessage("Usuario creado")

            }else{
                showMessage("Error: Intente de nuevo")
            }
        }
    }

    private fun signIn(email: String,password: String){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            task ->
            if (task.isSuccessful){
                showMessage("Bienvenid@ -  $email")
                try{
                    Log.i(ContentValues.TAG, "Intento de Ingresar a MainActivity")
                    val intent = Intent(context, MainActivity::class.java)
                    intent.putExtra("User", email)
                    startActivity(intent)
                    Log.d(ContentValues.TAG, "Intento terminado")
                }catch (e:Exception){
                    Log.w(ContentValues.TAG,"Actividad no inicializada")
                }
            }else{
                showMessage("Datos incorrectos")
            }
        }
    }
}