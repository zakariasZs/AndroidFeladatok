package com.example.tracker.api

import com.example.tracker.api.model.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApiService {

    @POST(BackendConstants.LOGIN_URL)
    suspend fun login(@Body loginRequest: LoginRequestBody): retrofit2.Response<LoginResponse>

    @GET(BackendConstants.GET_TASKS_URL)
    suspend fun getTasks(@Header(BackendConstants.HEADER_TOKEN) token: String): retrofit2.Response<List<TasksResponse>>

    @GET(BackendConstants.GET_USER_DETAIL_URL)
    suspend fun getUserProfileDetail(@Header(BackendConstants.HEADER_TOKEN) token: String): retrofit2.Response<UserProfileResponse>

    @GET(BackendConstants.GET_USERS)
    suspend fun getUsers(@Header(BackendConstants.HEADER_TOKEN) token: String): retrofit2.Response<List<GetUserResponse>>
}