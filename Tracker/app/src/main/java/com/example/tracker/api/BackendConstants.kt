package com.example.tracker.api

object BackendConstants {

    /**
     * Project backend base URL.
     */
    const val BASE_URL = "http://tracker-3track.a2hosted.com/"

    /**
     * Specific URL segments, which will be concatenated with the base URL.
     */
    const val LOGIN_URL = "login"
    const val GET_TASKS_URL = "task/getTasks"
    const val GET_USER_DETAIL_URL = "user"
    const val GET_USERS = "users"
    const val GET_DEPARTMENTS = "department"
    const val GET_MY_GROUPS = "department"
    const val POST_TASK = "task/create"
    const val UPDATE_PROFILE = "users/updateProfile"
    const val GET_ACTIVITIES = "activity/getActivities"

    /**
     * Header values.
     */
    const val HEADER_TOKEN = "token"
}