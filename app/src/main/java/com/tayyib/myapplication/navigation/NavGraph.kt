package com.tayyib.myapplication.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.tayyib.myapplication.screen.Screen
import com.tayyib.myapplication.ui.view.ForgotPasswordScreen
import com.tayyib.myapplication.ui.view.HomeScreen
import com.tayyib.myapplication.ui.view.LoginScreen
import com.tayyib.myapplication.ui.view.ProfileScreen
import com.tayyib.myapplication.ui.view.TicketDetailScreen


@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Login.route,)
    {
        composable(route = Screen.Login.route)
        { LoginScreen(navController) }
        composable(route = Screen.ForgotPassword.route)
        { ForgotPasswordScreen(navController) }
        composable(
            route = "ticket_detail_screen/{ticketId}/{name}/{assignedUser}/{subject}/{message}"
        ) { backStackEntry ->
            val ticketId = backStackEntry.arguments?.getString("ticketId")
            val assignedUser = backStackEntry.arguments?.getString("assignedUser")
            val subject = backStackEntry.arguments?.getString("subject")
            val message = backStackEntry.arguments?.getString("message")
            val name = backStackEntry.arguments?.getString("name")
            TicketDetailScreen(
                navController, ticketId = ticketId.toString(), name = name.toString(), assignedUser = assignedUser.toString(), subject = subject.toString(), message = message.toString()) }
        navigation(startDestination = Screen.Home.route, route = "home_graph")
        { composable(route = Screen.Home.route)
        { HomeScreen(navController) }
            composable(route = Screen.Profile.route) { ProfileScreen(navController) }
        }
    }
}