package com.example.miracleplan.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.miracleplan.data.model.*
import com.example.miracleplan.data.network.ApiService

class AuthViewModel(private val apiService: ApiService) : ViewModel() {

    private val _accessToken = MutableLiveData<AccessTokenResponse>()
    val accessToken: LiveData<AccessTokenResponse> get() = _accessToken

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _userResponse = MutableLiveData<UserResponse>()
    val userResponse: LiveData<UserResponse> get() = _userResponse

//    fun createUser(username: String, password: String, email: String) {
//        viewModelScope.launch {
//            try {
//                val request = UserCreateRequest(username, password)
//                val response = apiService.createUser(request).execute()
//                if (response.isSuccessful) {
//                    _userResponse.value = response.body()
//                } else {
//                    val error = response.errorBody()?.string() ?: "Unknown error"
//                    _errorMessage.value = "Error: $error"
//                    Log.e("AuthViewModel", "Error creating user: $error")
//                }
//            } catch (e: Exception) {
//                _errorMessage.value = "An error occurred: ${e.message}"
//                Log.e("AuthViewModel", "Exception creating user", e)
//            }
//        }
//    }
//
//    fun getAccessToken(username: String, password: String) {
//        viewModelScope.launch {
//            try {
//                val request = TokenRequest(username, password)
//                val response = apiService.login(request).execute()
//                if (response.isSuccessful) {
//                    _accessToken.value = response.body()
//                } else {
//                    val error = response.errorBody()?.string() ?: "Unknown error"
//                    _errorMessage.value = "Error: $error"
//                    Log.e("AuthViewModel", "Error fetching access token: $error")
//                }
//            } catch (e: Exception) {
//                _errorMessage.value = "An error occurred: ${e.message}"
//                Log.e("AuthViewModel", "Exception fetching access token", e)
//            }
//        }
//    }
//
//    fun refreshToken(refreshToken: String) {
//        viewModelScope.launch {
//            try {
//                val request = RefreshTokenRequest(refreshToken)
//                val response = apiService.refreshToken(request).execute()
//                if (response.isSuccessful) {
//                    _accessToken.value = response.body()
//                } else {
//                    val error = response.errorBody()?.string() ?: "Unknown error"
//                    _errorMessage.value = "Error: $error"
//                    Log.e("AuthViewModel", "Error refreshing token: $error")
//                }
//            } catch (e: Exception) {
//                _errorMessage.value = "An error occurred: ${e.message}"
//                Log.e("AuthViewModel", "Exception refreshing token", e)
//            }
//        }
//    }
}
