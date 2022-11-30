package com.example.a3track.api

import com.example.a3track.api.model.LoginRequestBody
import com.example.a3track.api.model.LoginResponse
import com.example.a3track.api.model.ProductsListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MarketPlaceApiService {

    @POST(BackendConstants.LOGIN_URL)
    suspend fun login(@Body request: LoginRequestBody): LoginResponse

    @GET(BackendConstants.GET_PRODUCT_URL)
    suspend fun getProducts(@Header(BackendConstants.HEADER_TOKEN) token: String): ProductsListResponse
}