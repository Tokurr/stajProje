package com.tayyib.myapplication.models.message

import com.google.gson.annotations.SerializedName

data class MessageResponse (

    @SerializedName("Status")
    val status: Boolean,

    @SerializedName("Message")
    val message: String


)