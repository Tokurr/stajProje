package com.tayyib.myapplication.models.message

data class MessageRequest(
    val LastTransaction: LastTransaction,
    val Ticket: Ticket
)