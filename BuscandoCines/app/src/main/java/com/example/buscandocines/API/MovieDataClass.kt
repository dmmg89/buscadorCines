package com.example.buscandocines.API

import java.time.LocalTime


data class MovieDataClass(
    val id: Int,
    val name: String,
    val details: String,
    val day: Int,
    val franchise: String,
    val cinema: String,
    val time: List<String>
)
