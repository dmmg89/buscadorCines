package com.example.buscandocines.API


import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIQuery {


    //Cinema Query

@GET("cinema")
suspend fun  getAllCinema(): List<CinemaDataClass>

@GET("cinema/{id}")
suspend fun getCinemaByID(@Path("id") cinemaID : Int) :CinemaDataClass

@GET("cinema")
suspend fun getCinemaByFranchise(@Query("franchise") franchise:String): List<CinemaDataClass>



    //Movie Query

@GET("movie")
suspend fun getAllMoviesFromAPI(): List<MovieDataClass>

@GET("movie/{id}")
suspend fun getMovieByID(@Path ("id")movieID:Int): MovieDataClass
@GET("movie")
suspend fun getMovieByName(@Query("name") name:String): List<MovieDataClass>

}