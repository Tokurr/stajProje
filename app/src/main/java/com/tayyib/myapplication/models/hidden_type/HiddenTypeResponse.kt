package com.tayyib.myapplication.models.hidden_type

import com.google.gson.annotations.SerializedName

class HiddenTypeResponse(

    @SerializedName("Status")
    val status: Boolean,
    @SerializedName("Message")
    val message: String

)