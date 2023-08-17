package com.example.buscandocines

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.buscandocines.MainContent.AccountFragment
import com.example.buscandocines.MainContent.FranchiseFragment
import com.example.buscandocines.MainContent.MoviesFragment
import com.example.buscandocines.MainContent.PremiereFragment

import com.example.buscandocines.MainContent.TopMainFragment
import com.example.buscandocines.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    lateinit var fusedLocationClient :FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("Error", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            val token = task.result

            Log.d("FCM_TOKEN",token)
            Toast.makeText(baseContext,"FCM token: $token", Toast.LENGTH_SHORT).show()
        })

try {

    fusedLocationClient = LocationServices.getFusedLocationProviderClient(baseContext)
    val latitudGPS = CurrentUbication.latitude
    val longitudGPS = CurrentUbication.longitude
    getLocation()
    Log.d(TAG, "Valores de ubicación")
    Log.d(TAG, "Latitude " +latitudGPS)
    Log.d(TAG, "Latitude " +longitudGPS)

}catch (e:Exception){
    Log.d(TAG, "No se consiguió la ubicación")
}


        //binding - elementos ligados
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTopFragment(TopMainFragment())
        replaceFragmentContent(MoviesFragment())
        binding.bottomNavigationView.menu.findItem(R.id.moviesBottom).setChecked(true)
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.moviesBottom -> {
                    replaceFragmentContent(MoviesFragment())
                    getLocation()
                    Log.d(TAG, "Valores de ubicación")
                    Log.d(TAG, "Latitude " +CurrentUbication.latitude)
                    Log.d(TAG, "Latitude " +CurrentUbication.longitude)

                }
                R.id.theaterBottom -> {
                    replaceFragmentContent(FranchiseFragment())
                    getLocation()
                    Log.d(TAG, "Valores de ubicación")
                    Log.d(TAG, "Latitude " +CurrentUbication.latitude)
                    Log.d(TAG, "Latitude " +CurrentUbication.longitude)
                }
                R.id.premiereBottom->{
                    replaceFragmentContent(PremiereFragment())
                }
                R.id.accountBottom -> {
                    replaceFragmentContent(AccountFragment())

                }

                else-> {}
            }
            true
        }





    }

    private fun setTopFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.topContainer,fragment)
        fragmentTransaction.commit()
    }


    private fun replaceFragmentContent(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }


    companion object{
        const val PERMISSION_UBICATION = 33
    }

    private fun checkGranted(permission :String):Boolean{
        return ActivityCompat.checkSelfPermission(this,permission) == PackageManager.PERMISSION_GRANTED
    }

    private fun checkPermissions() =
        checkGranted(Manifest.permission.ACCESS_FINE_LOCATION) && checkGranted(Manifest.permission.ACCESS_COARSE_LOCATION)


    private fun requestPermissions(){
        ActivityCompat.requestPermissions(this,
        arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_UBICATION
        )
    }

    private fun isLocationEnabled(): Boolean{
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    @SuppressLint("MissingPermission")
    private fun getLocation(){
        if(checkPermissions()&&isLocationEnabled()){
            try{
            fusedLocationClient.locationAvailability
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                CurrentUbication.latitude = location?.latitude?.toDouble() ?: CurrentUbication.latitude
                CurrentUbication.longitude = location?.longitude?.toDouble() ?: CurrentUbication.longitude
            }
            }catch (e:Exception){
                Log.d(TAG, e.cause.toString())
            }

        }else{
            requestPermissions()
        }
    }

    object CurrentUbication{
        var latitude =19.32400391485862
        var longitude =-99.17896197076101
    }

}