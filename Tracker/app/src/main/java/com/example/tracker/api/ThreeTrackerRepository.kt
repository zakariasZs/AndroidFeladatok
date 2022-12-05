package com.example.tracker.api

import com.example.tracker.api.model.LoginRequestBody
import com.example.tracker.api.model.LoginResponse
import com.example.tracker.api.model.TasksResponse
import com.example.tracker.api.model.UserProfileResponse

class ThreeTrackerRepository {

    /**
     * 'suspend' keyword means that this function can be blocking.
     * We need to be aware that we can only call them from within a coroutine or an other suspend function!
     */
    suspend fun login(loginRequestBody: LoginRequestBody): retrofit2.Response<LoginResponse> {
        return RetrofitInstance.USER_API_SERVICE.login(loginRequestBody)
    }

    suspend fun getTasks(token: String): retrofit2.Response<List<TasksResponse>> {
        return RetrofitInstance.USER_API_SERVICE.getTasks(token)
    }

    suspend fun getUserProfileDetail(token: String): retrofit2.Response<UserProfileResponse> {
        return RetrofitInstance.USER_API_SERVICE.getUserProfileDetail(token)
    }
}