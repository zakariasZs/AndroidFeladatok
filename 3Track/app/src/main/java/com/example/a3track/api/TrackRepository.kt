package com.example.a3track.api

import com.example.a3track.api.model.LoginRequestBody
import com.example.a3track.api.model.LoginResponse

/**
 * Author:  Zoltan Lorinczi
 * Date:    11/8/2021
 */
class TrackRepository {

    /**
     * 'suspend' keyword means that this function can be blocking.
     * We need to be aware that we can only call them from within a coroutine or an other suspend function!
     */
    suspend fun login(loginRequestBody: LoginRequestBody): LoginResponse {
        return RetrofitInstance.marketPlaceApiService.login(loginRequestBody)
    }

}