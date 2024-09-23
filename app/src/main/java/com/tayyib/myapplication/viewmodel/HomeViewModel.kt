package com.tayyib.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tayyib.myapplication.models.Ticket.TicketResponse
import com.tayyib.myapplication.repository.MyTicketRepository
import com.tayyib.myapplication.repository.TeamTicketReposityory
import com.tayyib.myapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val MyTicketRepository: MyTicketRepository, private val TeamTicketRepository: TeamTicketReposityory):ViewModel() {

    private val _ticketStatus = MutableLiveData<Resource<TicketResponse>>()
    val ticketStatus: LiveData<Resource<TicketResponse>> get() = _ticketStatus

    private val _teamTicketStatus = MutableLiveData<Resource<TicketResponse>>()
    val teamTicketStatus: LiveData<Resource<TicketResponse>> get() = _teamTicketStatus

    fun getMyTicket(token: String, id:String){

        viewModelScope.launch {

           MyTicketRepository.getMyTicket(token,id).collect() { result->
               _ticketStatus.postValue(result)

           }

        }

    }


    fun getTeamTicket(token: String, id:String){

        viewModelScope.launch {
            TeamTicketRepository.getTeamTicket(token,id).collect() { result ->
                _teamTicketStatus.postValue(result)
            }

        }

    }



}