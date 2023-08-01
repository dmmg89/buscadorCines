package com.example.buscandocines.API


import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIQuery {

@GET("cinema")
suspend fun  getAllCinema(): List<CinemaDataClass>


@GET("cinema/{id}")
suspend fun getCinemaByID(@Path("id") cinemaID : Int) :CinemaDataClass

@GET("cinema")
suspend fun getCinemaByFranchise(@Query("franchise") franchise:String): List<CinemaDataClass>

}