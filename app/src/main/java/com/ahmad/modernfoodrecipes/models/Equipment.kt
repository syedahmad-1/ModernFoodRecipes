package com.ahmad.modernfoodrecipes.models


import com.google.gson.annotations.SerializedName

data class Equipment(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("localizedName")
    val localizedName: String,
    @SerializedName("name")
    val name: String
)