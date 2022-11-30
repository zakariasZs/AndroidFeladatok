package com.example.a3track.api.model

import android.media.Image
import com.google.gson.annotations.SerializedName


data class ProductResponse(
    @SerializedName("rating")
    val rating: Double,

    @SerializedName("amount_type")
    val amountType: String,

    @SerializedName("price_type")
    val priceType: String,

    @SerializedName("product_id")
    val productId: String,

    @SerializedName("username")
    val username: String,

    @SerializedName("is_active")
    val isActive: Boolean,

    @SerializedName("price_per_unit")
    val pricePerUnit: String,

    @SerializedName("units")
    val units: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("images")
    val images: List<Image>,

    @SerializedName("creation_time")
    val creationTime: Long
)
