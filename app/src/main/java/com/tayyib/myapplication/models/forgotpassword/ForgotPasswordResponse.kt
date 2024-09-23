package com.tayyib.myapplication.models.forgotpassword

import com.google.gson.annotations.SerializedName

class ForgotPasswordResponse (

    @SerializedName("Status")
     val status: Boolean,

    @SerializedName("Message")
     val message: String

)