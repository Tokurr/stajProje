

// LoginViewModel.kt
package com.tayyib.myapplication.viewmodel

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tayyib.myapplication.di.DataStoreManager
import com.tayyib.myapplication.models.login.AuthResponse
import com.tayyib.myapplication.repository.UserRepository
import com.tayyib.myapplication.models.login.LoginResponse
import com.tayyib.myapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val loginResponse: LoginResponse,
    @ApplicationContext private val context: Context
    ) : ViewModel() {
    private val _loginStatus = MutableLiveData<Resource<AuthResponse>>()
    val loginStatus: LiveData<Resource<AuthResponse>> get() = _loginStatus
    fun getTokenAndLogin(email: String, userPassword: String) {
        viewModelScope.launch {
            userRepository.getTokenAndLogin(email, userPassword).collect { result ->
                _loginStatus.postValue(result)
            }
        }
    }
    fun getLoginResponse(): LoginResponse {
        return loginResponse
    } }