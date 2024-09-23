package com.tayyib.myapplication.models.message

data class LastTransaction(
    val AssignedUserId: Int,
    val Status: Int,
    val Transaction: Transaction
)