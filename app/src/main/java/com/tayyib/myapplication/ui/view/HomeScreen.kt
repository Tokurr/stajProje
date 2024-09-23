

package com.tayyib.myapplication.ui.view


import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.tayyib.myapplication.ui.theme.MyApplicationTheme
import androidx.navigation.compose.rememberNavController
import com.tayyib.myapplication.components.BottomBar
import com.tayyib.myapplication.preference.EncryptedPreferencesManager
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Alignment
import com.tayyib.myapplication.util.Resource
import com.tayyib.myapplication.viewmodel.HomeViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import com.tayyib.myapplication.components.TicketCard
import com.tayyib.myapplication.models.Ticket.TicketResponse
import com.tayyib.myapplication.ui.theme.bimserColor
import com.tayyib.myapplication.util.parseAndFormatDate

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController

)
{

    val viewModel: HomeViewModel = hiltViewModel()
    val myTicketStatus by viewModel.ticketStatus.observeAsState()
    val teamTicketStatus by viewModel.teamTicketStatus.observeAsState()

    var state by remember { mutableIntStateOf(0) }
    val titles = listOf("Bana Atanan", "Herkese Atanan")


    val context = LocalContext.current
    val preference = EncryptedPreferencesManager(context)
    val token = preference.getToken()
    val userId = preference.getUserID()

    LaunchedEffect(Unit) {
        if (myTicketStatus !is Resource.Loading && myTicketStatus !is Resource.Success) {

          viewModel.getMyTicket(token.toString(), userId.toString())

        }

        if(teamTicketStatus !is Resource.Loading && teamTicketStatus !is Resource.Success)
        {
            viewModel.getTeamTicket(token.toString(),userId.toString())
        }

    }

    Scaffold(


        modifier = Modifier.fillMaxSize(),

        bottomBar = {
                BottomBar(navController = navController)
        }

    )
    { innerPadding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),

            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {

            TabRow(selectedTabIndex = state,
                containerColor = Color.White,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(tabPositions[state]),
                        color = Color.Black
                    )
                }
            ) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        selected = state == index,
                        onClick = { state = index },
                        text = { Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis,color = Color.Black) }
                    )
                }
            }

            if(state == 0)
            {
                when (myTicketStatus) {

                    is Resource.Loading -> {

                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            androidx.compose.material.CircularProgressIndicator(
                                color = bimserColor
                            )
                        }

                    }

                    is Resource.Success -> {
                        val tickets = (myTicketStatus as Resource.Success<TicketResponse>).data

                        if (tickets != null) {

                            LazyColumn (
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxSize()

                            ) {
                                items(tickets){
                                        ticket ->
                                    var sentDate = ticket.sentDate
                                    val subject = ticket.subject
                                    var message = ticket.message

                                    if(message.contains("<p>") || message.contains("</p>"))
                                    {
                                        message = message.replace("<p>", "")
                                        message = message.replace("</p>", "")
                                    }

                                    val product = ticket.products
                                    val assignedUser = ticket.assignedUser
                                    val ticketImportanceLevels = ticket.ticketImportanceLevels
                                    val ticketStatus = ticket.ticketStatuses
                                    val ticketTypes = ticket.ticketTypes
                                    val name = ticket.name
                                    println()
                                    sentDate = parseAndFormatDate(sentDate)
                                    TicketCard(
                                        name = name,
                                        sentDate = sentDate,
                                        subject = subject,
                                        message = message,
                                        products = product,
                                        assignedUser = assignedUser,
                                        ticketImportanceLevels = ticketImportanceLevels,
                                        ticketStatus = ticketStatus,
                                        ticketTypes = ticketTypes,
                                        tickedId = ticket.id.toString(),
                                        navController = navController,
                                    )


                                }
                            }

                        }

                    }

                    is Resource.Error -> {
                        Toast.makeText(context, (myTicketStatus as Resource.Error<TicketResponse>).message ?: "Login failed", Toast.LENGTH_SHORT).show()
                    }

                    else -> {}

                }

            }


            if(state == 1)
            {
                when (teamTicketStatus) {

                    is Resource.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is Resource.Success -> {
                        val tickets = (teamTicketStatus as Resource.Success<TicketResponse>).data

                        if (tickets != null) {

                            LazyColumn (
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxSize()

                            ) {
                                items(tickets){
                                        ticket ->
                                    var sentDate = ticket.sentDate
                                    val subject = ticket.subject
                                    var message = ticket.message

                                    if(message.contains("<p>") || message.contains("</p>"))
                                    {
                                        message = message.replace("<p>", "")
                                        message = message.replace("</p>", "")
                                    }

                                    val product = ticket.products
                                    val assignedUser = ticket.assignedUser
                                    val ticketImportanceLevels = ticket.ticketImportanceLevels
                                    val ticketStatus = ticket.ticketStatuses
                                    val ticketTypes = ticket.ticketTypes
                                    val ticketId = ticket.id
//                                    println("ticket idddddd: $ticketId")
                                 sentDate =  parseAndFormatDate(sentDate)
                                    TicketCard(
                                        name = ticket.name,
                                        sentDate = sentDate,
                                        subject = subject,
                                        message = message,
                                        products = product,
                                        assignedUser = assignedUser,
                                        ticketImportanceLevels = ticketImportanceLevels,
                                        ticketStatus = ticketStatus,
                                        ticketTypes = ticketTypes,
                                        tickedId = ticketId.toString(),
                                        navController = navController,

                                    )


                                }
                            }

                        }

                    }

                    is Resource.Error -> {
                        Toast.makeText(context, (teamTicketStatus as Resource.Error<TicketResponse>).message ?: "Login failed", Toast.LENGTH_SHORT).show()
                    }
                    else -> {}

                }

            }

        }
        }

    }


@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    val navController = rememberNavController()
    MyApplicationTheme {

        HomeScreen(
            navController
        )
    }

}