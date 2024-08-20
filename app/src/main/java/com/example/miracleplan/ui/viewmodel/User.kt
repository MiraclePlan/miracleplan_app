package com.example.miracleplan.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miracleplan.data.model.AccessTokenResponse
import com.example.miracleplan.data.model.RefreshTokenRequest
import com.example.miracleplan.data.model.TokenRequest
import com.example.miracleplan.data.model.UserCreateRequest
import com.example.miracleplan.data.model.UserResponse
import com.example.miracleplan.data.network.ApiService
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class UserViewModel(private val apiService: ApiService) : ViewModel() {

    private val _loginResponse = MutableLiveData<AccessTokenResponse>()
    val loginResponse: LiveData<AccessTokenResponse> get() = _loginResponse

    private val _registerResponse = MutableLiveData<UserResponse>()
    val registerResponse: LiveData<UserResponse> get() = _registerResponse

    private val _refreshTokenResponse = MutableLiveData<AccessTokenResponse>()
    val refreshTokenResponse: LiveData<AccessTokenResponse> get() = _refreshTokenResponse

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage


    fun loginUser(username: String, password: String) {
        viewModelScope.launch {
            try {
                val tokenRequest = TokenRequest(username, password)
                val response = apiService.login(tokenRequest).execute()
                if (response.isSuccessful) {
                    _loginResponse.value = response.body()
                } else {
                    val error = response.errorBody()?.string() ?: "Unknown error"
                    _errorMessage.value = "Error logging in: $error"
                    Log.e("UserViewModel", "Error logging in: $error")
                }
            } catch (e: Exception) {
                _errorMessage.value = "An error occurred: ${e.message}"
                Log.e("UserViewModel", "Exception logging in", e)
            }
        }
    }

    fun registerUser(username: String, password: String) {
        viewModelScope.launch {
            try {
                val userCreateRequest = UserCreateRequest(username, password)
                val response = apiService.register(userCreateRequest).execute()
                if (response.isSuccessful) {
                    _registerResponse.value = response.body()
                } else {
                    val error = response.errorBody()?.string() ?: "Unknown error"
                    _errorMessage.value = "Error registering user: $error"
                    Log.e("UserViewModel", "Error registering user: $error")
                }
            } catch (e: Exception) {
                _errorMessage.value = "An error occurred: ${e.message}"
                Log.e("UserViewModel", "Exception registering user", e)
            }
        }
    }

    fun refreshToken(refreshToken: String) {
        viewModelScope.launch {
            try {
                val refreshTokenRequest = RefreshTokenRequest(refreshToken)
                val response = apiService.refreshToken(refreshTokenRequest).execute()
                if (response.isSuccessful) {
                    _refreshTokenResponse.value = response.body()
                } else {
                    val error = response.errorBody()?.string() ?: "Unknown error"
                    _errorMessage.value = "Error refreshing token: $error"
                    Log.e("UserViewModel", "Error refreshing token: $error")
                }
            } catch (e: Exception) {
                _errorMessage.value = "An error occurred: ${e.message}"
                Log.e("UserViewModel", "Exception refreshing token", e)
            }
        }
    }
}
