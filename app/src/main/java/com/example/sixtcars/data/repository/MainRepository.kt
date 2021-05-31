package com.example.sixtcars.data.repository

import com.example.sixtcars.data.remote.api.ApiService
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MainRepository @Inject constructor(private val apiService: ApiService) {
    
    suspend fun getCars() = apiService.fetchCarsAsync()

}