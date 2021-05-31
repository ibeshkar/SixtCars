package com.example.sixtcars.data.remote.api

import com.example.sixtcars.data.entity.Car
import retrofit2.http.GET

interface ApiService {

    @GET("codingtask/cars")
    suspend fun fetchCarsAsync(): MutableList<Car>
}