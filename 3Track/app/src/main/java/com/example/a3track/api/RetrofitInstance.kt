package com.example.a3track.api


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object RetrofitInstance {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BackendConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val marketPlaceApiService: TrackApiService by lazy {
        retrofit.create(TrackApiService::class.java)
    }
}