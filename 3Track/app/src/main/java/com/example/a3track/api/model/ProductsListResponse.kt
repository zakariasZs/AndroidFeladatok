package com.example.a3track.api.model

import com.google.gson.annotations.SerializedName


data class ProductsListResponse(
    @SerializedName("item_count")
    val itemCount: Int,

    @SerializedName("products")
    val products: List<ProductResponse>,

    @SerializedName("timestamp")
    val timeStamp: Long
)