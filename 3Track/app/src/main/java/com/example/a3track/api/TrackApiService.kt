package com.example.a3track.api

import com.example.a3track.api.model.LoginRequestBody
import com.example.a3track.api.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface TrackApiService {

    @POST(BackendConstants.LOGIN_URL)
    suspend fun login(@Body request: LoginRequestBody): LoginResponse


}