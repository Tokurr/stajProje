package com.tayyib.myapplication.screen
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.Icons

const val NAME = "name"
const val SURNAME = "surname"
const val EMAIL = "surname"
const val ID = "id"
sealed class Screen(val route: String,val title: String, val icon: ImageVector? = null) {
    object Home: Screen(route = "home_screen","Destek Talep",Icons.Outlined.DateRange)
    object Login: Screen(route = "login_screen","Login")
    object ForgotPassword: Screen(route = "forgot_password_screen","Forgot Password")
    object Profile: Screen(route = "profile_screen","Profil",Icons.Outlined.AccountCircle)
    object TicketDetail: Screen(route = "ticket_detail_screen","Ticket Detail")
}