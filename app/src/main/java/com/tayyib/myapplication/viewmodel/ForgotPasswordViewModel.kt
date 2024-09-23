package com.tayyib.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tayyib.myapplication.models.forgotpassword.ForgotPasswordResponse
import com.tayyib.myapplication.repository.ForgotPasswordRepository
import com.tayyib.myapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(private val forgotPasswordRepository: ForgotPasswordRepository ):ViewModel(){

    private val _forgotPasswordStatus = MutableLiveData<Resource<ForgotPasswordResponse>>()
    val forgotPasswordStatus: MutableLiveData<Resource<ForgotPasswordResponse>>
        get() = _forgotPasswordStatus

    fun forgotPassword(email: String, language: String) {

        viewModelScope.launch {

            forgotPasswordRepository.forgotPassword(email,language).collect { result ->
                _forgotPasswordStatus.postValue(result)
            }

        }

        }

    }
