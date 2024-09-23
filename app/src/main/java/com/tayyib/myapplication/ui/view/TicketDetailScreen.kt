package com.tayyib.myapplication.ui.view
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tayyib.myapplication.components.AlertDialogExample
import com.tayyib.myapplication.components.ChatBox
import com.tayyib.myapplication.components.SelectTicketStatu
import com.tayyib.myapplication.components.SelectableUserRow
import com.tayyib.myapplication.components.TicketDetailCard
import com.tayyib.myapplication.components.TopAppBarComponent
import com.tayyib.myapplication.models.hidden_type.HiddenTypeResponse
import com.tayyib.myapplication.models.message.LastTransaction
import com.tayyib.myapplication.models.message.MessageRequest
import com.tayyib.myapplication.models.message.MessageResponse
import com.tayyib.myapplication.models.message.Ticket
import com.tayyib.myapplication.models.message.TicketX
import com.tayyib.myapplication.models.message.Transaction
import com.tayyib.myapplication.models.person.PersonResponse
import com.tayyib.myapplication.models.person.PersonResponseItem
import com.tayyib.myapplication.models.ticket_detail.TicketDetailResponse
import com.tayyib.myapplication.models.ticket_detail.TicketDetailResponseItem
import com.tayyib.myapplication.models.ticket_status.TicketStatusResponse
import com.tayyib.myapplication.models.ticket_status.TicketStatusResponseItem
import com.tayyib.myapplication.preference.EncryptedPreferencesManager
import com.tayyib.myapplication.screen.Screen
import com.tayyib.myapplication.ui.theme.bimserColor
import com.tayyib.myapplication.util.Resource
import com.tayyib.myapplication.util.parseAndFormatDate
import com.tayyib.myapplication.viewmodel.TicketDetailViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "MutableCollectionMutableState")
@Composable
fun TicketDetailScreen(
    navController: NavHostController,
    ticketId: String,
    name: String,
    assignedUser: String,
    subject: String,
    message: String
) {

    val viewModel: TicketDetailViewModel = hiltViewModel()
    val ticketDetailStatus by viewModel.ticketDetailStatus.observeAsState()
    val ticketDetailStatus2 by viewModel.ticketDetailStatus2.observeAsState()
    val personStatus by viewModel.personStatus.observeAsState()
    val messageStatus by viewModel.messageStatus.observeAsState()
    val hiddenTypeStatus by viewModel.hiddenTypeStatus.observeAsState()
    val ticketStatus by viewModel.ticketStatus.observeAsState()
    val ticketDetailList = remember { mutableStateListOf<TicketDetailResponseItem>() }


    var ticketDetailID by remember { mutableIntStateOf(0) }
    val selectedUsers = remember { mutableStateListOf<PersonResponseItem>() }
    val context = LocalContext.current
    val preference = EncryptedPreferencesManager(context)
    val token = preference.getToken()
    val userId = preference.getUserID()


    val showAlertDiaolog = remember { mutableStateOf(false) }
    val progressBar = remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }
    var showBottomSheetTicketStatus by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )
    LaunchedEffect(Unit) {
        if (ticketDetailStatus !is Resource.Loading && ticketDetailStatus !is Resource.Success) {
            println("token $token")
            println("ticketId $ticketId")
            viewModel.getTicketDetail(token.toString(), ticketId)
        }

        if (personStatus !is Resource.Loading && personStatus !is Resource.Success) {
            userId?.let { viewModel.getBimserPerson(token.toString(), it) }

        }

        if (ticketStatus !is Resource.Loading && ticketStatus !is Resource.Success) {
            viewModel.getTicketStatus(token.toString())
        }

    }


    LaunchedEffect(messageStatus) {

        when (messageStatus) {
            is Resource.Error -> {
                Toast.makeText(
                    context,
                    (messageStatus as Resource.Error<MessageResponse>).message ?: "Login failed",
                    Toast.LENGTH_LONG
                ).show()

            }

            is Resource.Loading -> {

            }

            is Resource.Success -> {
                Toast.makeText(context, "Mesaj Gönderilmiştir", Toast.LENGTH_LONG).show()
                viewModel.getTicketDetail2(token.toString(), ticketId)

            }

            null -> {

            }
        }

    }
    LaunchedEffect(ticketDetailStatus2) {

        when (ticketDetailStatus2) {
            is Resource.Error -> {
                Toast.makeText(
                    context,
                    (ticketDetailStatus2 as Resource.Error<TicketDetailResponse>).message
                        ?: "Login failed",
                    Toast.LENGTH_LONG
                ).show()

            }

            is Resource.Loading -> {

            }

            is Resource.Success -> {


                val ticketDetail2 = (ticketDetailStatus2 as Resource.Success).data
                ticketDetail2?.forEach { newItem ->
                    if (!ticketDetailList.any { it.Id == newItem.Id }) {
                        ticketDetailList.add(newItem)

                    }
                }


            }

            null -> {

            }
        }

    }




    if (progressBar.value) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }



    if (showBottomSheet) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { showBottomSheet = false },
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            containerColor = Color.White
            ) {

            Column (modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    text = "Lütfen eklemek istediğiniz kullanıcıları seçin.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = bimserColor
                )
            }



            Spacer(modifier = Modifier.height(16.dp))

            when (personStatus) {
                is Resource.Error -> {
                    Toast.makeText(
                        context,
                        (personStatus as Resource.Error<PersonResponse>).message ?: "Login failed",
                        Toast.LENGTH_SHORT
                    ).show()
                    progressBar.value = false
                }

                is Resource.Loading -> {

                    progressBar.value = true

                }

                is Resource.Success -> {
                    progressBar.value = false
                    val people = (personStatus as Resource.Success<PersonResponse>).data

                    if (people != null) {
                        LazyColumn(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            items(people) { person ->
                                val isSelected = selectedUsers.contains(person)
                                SelectableUserRow(
                                    person = person,
                                    isSelected = isSelected,
                                    onSelectionChange = { isSelectedNow ->
                                        if (isSelectedNow) {
                                            selectedUsers.add(person)
                                        } else {
                                            selectedUsers.remove(person)
                                        }
                                    }
                                )
                            }

                        }
                    }

                }

                null -> {

                }
            }


        }

    }
    var selectedStatus by remember { mutableStateOf<TicketStatusResponseItem?>(null) }


    if (showBottomSheetTicketStatus) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { showBottomSheetTicketStatus = false },
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        containerColor = Color.White,

        ) {

         Column (modifier = Modifier.fillMaxWidth(),
             horizontalAlignment = Alignment.CenterHorizontally

             ){
             Text(
                 text = "Lütfen mesaj konusunu seçin.",
                 style = MaterialTheme.typography.bodyLarge,
                 color = bimserColor
             )
         }



            Spacer(modifier = Modifier.height(16.dp))

            when (ticketStatus) {
                is Resource.Error -> {
                    Toast.makeText(
                        context,
                        (ticketStatus as Resource.Error<TicketStatusResponse>).message
                            ?: "Login failed",
                        Toast.LENGTH_SHORT
                    ).show()
                    progressBar.value = false
                }

                is Resource.Loading -> {
                    progressBar.value = true
                }

                is Resource.Success -> {
                    progressBar.value = false
                    val status = (ticketStatus as Resource.Success<TicketStatusResponse>).data

                    if (status != null) {
                        LazyColumn(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            items(status) { statu ->
                                SelectTicketStatu(
                                    statu = statu,
                                    isSelected = selectedStatus == statu,
                                    onSelectionChange = { isSelectedNow ->
                                        if (isSelectedNow) {
                                            selectedStatus = statu

                                        } else {
                                            selectedStatus = null
                                        }
                                    }
                                )
                            }
                        }
                    }
                }

                null -> {

                }
            }
        }
    }


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),

        topBar = {
            TopAppBarComponent(name, assignedUser, subject, message,
                onNavigationIconClick = {
                    navController.popBackStack()
                    navController.navigate(Screen.Home.route)
                },
                onPersonClick = { showBottomSheet = true },
                onMenuClick = { showBottomSheetTicketStatus = true }
            )
        },

        bottomBar = {

            val handleSendMessage: (String) -> Unit = { result ->


                if (selectedUsers.isEmpty() || selectedStatus == null) {
                    Toast.makeText(
                        context,
                        "Lütfen sağ üst taraftaki kişilerden ve menüden seçiminizi yapınız",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    for (item in selectedUsers) {
                        val transaction = Transaction(result, userId?.toInt()!!)
                        val lastTransaction = LastTransaction(item.id, selectedStatus!!.id, transaction)
                        val ticketX = TicketX(item.id, ticketId.toInt(), selectedStatus!!.id, 2)
                        val ticket = Ticket(ticketX)
                        val messageRequest = MessageRequest(lastTransaction, ticket)
                        viewModel.replyTicket(token.toString(), messageRequest)
                    }


                }

                selectedUsers.clear()

            }


            ChatBox(
                onSendChatClickListener = handleSendMessage,
                onIconClickListener = {}
            )
        }

    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
        {

            when (ticketDetailStatus) {

                is Resource.Error -> {
                    Toast.makeText(
                        context,
                        (ticketDetailStatus as Resource.Error<TicketDetailResponse>).message
                            ?: "Login failed",
                        Toast.LENGTH_SHORT
                    ).show()

                }

                is Resource.Loading -> {

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }

                }

                is Resource.Success -> {
                    val ticketDetail = (ticketDetailStatus as Resource.Success).data




                    if (ticketDetail != null) {
                        if (ticketDetailList.isEmpty()) {
                            ticketDetailList.addAll(ticketDetail)
                        }

                        LazyColumn(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxSize()
                        )

                        {


                            items(
                                items = ticketDetailList,
                            ) { ticketDetail ->


                                var sentDate = ticketDetail.SentDate
                                val senderUserName = ticketDetail.SenderUserName
                                val ticketName = ticketDetail.Name
                                val assignedUserName = ticketDetail.AssignedUserName
                                var ticketMessage = ticketDetail.Message

                                sentDate = parseAndFormatDate(sentDate)
                                if (ticketMessage.contains("<p>") || ticketMessage.contains("</p>")) {
                                    ticketMessage = ticketMessage.replace("<p>", "")
                                    ticketMessage = ticketMessage.replace("</p>", "")
                                }
                                TicketDetailCard(
                                    sentDate = sentDate,
                                    senderUserName = senderUserName,
                                    name = ticketName,
                                    assignedUserName = assignedUserName,
                                    message = ticketMessage,

                                    )
                                {
                                    ticketDetailID = ticketDetail.Id
                                    showAlertDiaolog.value = true

                                }


                            }

                        }

                    }

                }

                else -> {}
            }


        }
    }
    if (showAlertDiaolog.value) {
        AlertDialogExample(
            onDismissRequest = {
                Toast.makeText(context, "Reddedildi", Toast.LENGTH_LONG).show()
                showAlertDiaolog.value = false
            },
            onConfirmation = {

                viewModel.changeHiddenType(token.toString(), ticketDetailID.toString(), "3")

                when (hiddenTypeStatus) {


                    is Resource.Error -> {
                        Toast.makeText(
                            context,
                            (hiddenTypeStatus as Resource.Error<HiddenTypeResponse>).message
                                ?: "Login failed",
                            Toast.LENGTH_LONG
                        ).show()

                    }

                    is Resource.Loading -> {

                    }

                    is Resource.Success -> {
                        Toast.makeText(context, "Gizlendi", Toast.LENGTH_LONG).show()
                        showAlertDiaolog.value = false
                    }

                    null -> {

                    }
                }
                Toast.makeText(context, "Gizlendi", Toast.LENGTH_LONG).show()
                showAlertDiaolog.value = false
            },
            dialogTitle = "Mesaj Görünürlüğü",
            dialogText = "Mesajı gizlemek istiyor musunuz?",

            )
    }


}



