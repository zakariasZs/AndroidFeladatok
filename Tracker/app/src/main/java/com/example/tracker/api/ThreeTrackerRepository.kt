package com.example.tracker.api

import com.example.tracker.api.model.*

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
    suspend fun getUsers(token: String): retrofit2.Response<List<GetUserResponse>> {
        return RetrofitInstance.USER_API_SERVICE.getUsers(token)
    }
    suspend fun getDepartments(token: String): retrofit2.Response<List<GetDepartmentResponse>> {
        return RetrofitInstance.USER_API_SERVICE.getDepartments(token)
    }

    suspend fun getMyGroups(token: String): retrofit2.Response<List<MyGroupsResponse>> {
        return RetrofitInstance.USER_API_SERVICE.getMyGroups(token)
    }

    suspend fun postTask(token: String, taskPostBody: TaskPostBody): retrofit2.Response<TaskPostResponse> {
        return RetrofitInstance.USER_API_SERVICE.taskPost(token, taskPostBody)
    }

    suspend fun updateProfile(token: String, UpdateProfile: UpdateProfile): retrofit2.Response<UpdateProfileResponse> {
        return RetrofitInstance.USER_API_SERVICE.updateProfile(token, UpdateProfile)
    }

    suspend fun getActivities(token: String): retrofit2.Response<List<ActivitiesResponse>> {
        return RetrofitInstance.USER_API_SERVICE.getActivities(token)
    }

    suspend fun updateTask(token: String, UpdateTask: UpdateTask): retrofit2.Response<UpdateTaskResponse> {
        return RetrofitInstance.USER_API_SERVICE.updateTask(token, UpdateTask)
    }
}