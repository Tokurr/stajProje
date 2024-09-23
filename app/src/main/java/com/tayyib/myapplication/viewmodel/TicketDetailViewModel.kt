package com.tayyib.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tayyib.myapplication.models.hidden_type.HiddenTypeResponse
import com.tayyib.myapplication.models.message.MessageRequest
import com.tayyib.myapplication.models.message.MessageResponse
import com.tayyib.myapplication.models.person.PersonResponse
import com.tayyib.myapplication.models.ticket_detail.TicketDetailResponse
import com.tayyib.myapplication.models.ticket_status.TicketStatusResponse
import com.tayyib.myapplication.repository.ChangeHiddenTypeRepository
import com.tayyib.myapplication.repository.MessageRepository
import com.tayyib.myapplication.repository.PersonRepository
import com.tayyib.myapplication.repository.TicketDetailRepository
import com.tayyib.myapplication.repository.TicketStatusRepository
import com.tayyib.myapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TicketDetailViewModel @Inject constructor(private val ticketDetailRepository: TicketDetailRepository, private val personRepository: PersonRepository,private val messageRepository: MessageRepository,private val changeHiddenTypeRepository: ChangeHiddenTypeRepository,private val ticketStatusRepository: TicketStatusRepository):ViewModel()  {

    private val _ticketDetailStatus = MutableLiveData<Resource<TicketDetailResponse>>()
    val ticketDetailStatus: LiveData<Resource<TicketDetailResponse>> get() = _ticketDetailStatus



    fun getTicketDetail(token : String, ticketId : String)
    {
        viewModelScope.launch {

           ticketDetailRepository.getTicketDetail(token,ticketId).collect{
               _ticketDetailStatus.postValue(it)

           }

        }

    }
    private val _ticketDetailStatus2 = MutableLiveData<Resource<TicketDetailResponse>>()
    val ticketDetailStatus2: LiveData<Resource<TicketDetailResponse>> get() = _ticketDetailStatus2

    fun getTicketDetail2(token : String, ticketId : String)
    {
        viewModelScope.launch {

            ticketDetailRepository.getTicketDetail(token,ticketId).collect{
                _ticketDetailStatus2.postValue(it)

            }

        }

    }



    private val _messageStatus = MutableLiveData<Resource<MessageResponse>>()
    val messageStatus : LiveData<Resource<MessageResponse>> get() = _messageStatus

    fun replyTicket(token:String, messageRequest: MessageRequest){
        viewModelScope.launch {
            messageRepository.replyTicket(token,messageRequest).collect{
                _messageStatus.postValue(it)
            } } }

    private val _personStatus = MutableLiveData<Resource<PersonResponse>>()
    val personStatus: LiveData<Resource<PersonResponse>> get() = _personStatus

    fun getBimserPerson(token: String, userId: String)
    {
        viewModelScope.launch {
            personRepository.getPerson(token,userId).collect{
                _personStatus.postValue(it)
            }
        }
    }

    private val _hiddenTypeStatus = MutableLiveData<Resource<HiddenTypeResponse>>()
    val hiddenTypeStatus: LiveData<Resource<HiddenTypeResponse>> get() = _hiddenTypeStatus


    fun changeHiddenType(token: String,tranId:String ,hiddenType: String)
    {
        viewModelScope.launch {
            changeHiddenTypeRepository.changeHiddenType(token,tranId,hiddenType).collect{
                _hiddenTypeStatus.postValue(it)
            }
        }
    }


    private val _ticketStatus = MutableLiveData<Resource<TicketStatusResponse>>()
    val ticketStatus: LiveData<Resource<TicketStatusResponse>> get() = _ticketStatus

    fun getTicketStatus(token: String)
    {
        viewModelScope.launch {

            ticketStatusRepository.getTicketStatus(token).collect{
                _ticketStatus.postValue(it)
            }
        }

    }


}