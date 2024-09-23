package com.tayyib.myapplication.models.Ticket

import com.google.gson.annotations.SerializedName

data class TicketResponseItem(

    @SerializedName("AgentLastActionDate") val agentLastActionDate: String,
    @SerializedName("AssignedUser") val assignedUser: String,
    @SerializedName ("CloseDate")val closeDate: String,
    @SerializedName ("CustomerId") val customerId: Int,
    @SerializedName ("CustomerLastActionDate") val customerLastActionDate: String,
    @SerializedName ("Id") val id: Int,
    @SerializedName ("InitialId") val initialId: String,
    @SerializedName ("Message") val message: String,
    @SerializedName ("Name") val name: String,
    @SerializedName ("PartnerLastActionDate") val partnerLastActionDate: Any,
    @SerializedName ("Products") val products: String,
    @SerializedName ("SentDate") val sentDate: String,
    @SerializedName ("Subject") val subject: String,
    @SerializedName ("TicketImportanceLevels") val ticketImportanceLevels: String,
    @SerializedName ("TicketStatuses") val ticketStatuses: String,
    @SerializedName ("TicketTypes") val ticketTypes: String
)