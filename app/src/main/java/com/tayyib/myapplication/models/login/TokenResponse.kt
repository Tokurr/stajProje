package com.tayyib.myapplication.models.login

import com.google.gson.annotations.SerializedName

 data class TokenResponse(
    @SerializedName("Token") val token: String,
    @SerializedName("TokenId") val tokenId: String,
    @SerializedName("ExpireDate") val expireDate: String,
    @SerializedName("CreateDate") val createDate: String,
    @SerializedName("UserId") val userId: String
)