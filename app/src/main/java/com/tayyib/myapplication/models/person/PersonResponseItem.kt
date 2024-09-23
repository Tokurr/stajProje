package com.tayyib.myapplication.models.person

import com.google.gson.annotations.SerializedName

data class PersonResponseItem(
    @SerializedName("Firstname")
    val firstname: String,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("Lastname")
    val lastname: String
)