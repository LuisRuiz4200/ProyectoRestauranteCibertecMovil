package com.example.restaurante.data.repository

import android.app.Application
import com.example.restaurante.data.api.ApiServices
import com.example.restaurante.data.api.RetrofitInstance

open class BaseRepository(application: Application){

    protected var apiClient : ApiServices = RetrofitInstance.api.create(ApiServices::class.java)

    protected var apiGoogleMaps : ApiServices = RetrofitInstance.apiMaps.create(ApiServices::class.java)
}