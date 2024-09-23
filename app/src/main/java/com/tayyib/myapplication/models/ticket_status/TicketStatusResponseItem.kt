package com.tayyib.myapplication.models.ticket_status

import com.google.gson.annotations.SerializedName

data class TicketStatusResponseItem(
    @SerializedName("Id")
    val id: Int,
    @SerializedName("MultiLanguageId")
    val multiLanguageId: Int,
    @SerializedName("Name")
    val name: String
)