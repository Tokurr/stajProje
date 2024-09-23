package com.tayyib.myapplication.models.ticket_detail

import com.google.gson.annotations.SerializedName

data class TicketDetailResponseItem(

    @SerializedName("AssignedUserId")
    val AssignedUserId: Int,
    @SerializedName("AssignedUserName")
    val AssignedUserName: String,
    @SerializedName("HiddenType")
    val HiddenType: Int,
    @SerializedName("Id")
    val Id: Int,
    @SerializedName("Message")
    val Message: String,
    @SerializedName("Name")
    val Name: String,
    @SerializedName("SenderUserId")
    val SenderUserId: Int,
    @SerializedName("SenderUserName")
    val SenderUserName: String,
    @SerializedName("SentDate")
    val SentDate: String,
    @SerializedName("TicketId")
    val TicketId: Int,
    @SerializedName("Type")
    val Type: Int
)