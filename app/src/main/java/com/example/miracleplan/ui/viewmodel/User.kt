package com.example.miracleplan.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miracleplan.data.model.UserResponse
import com.example.miracleplan.data.network.ApiService
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class UserViewModel(private val apiService: ApiService) : ViewModel() {
    private val _userProfile = MutableLiveData<UserResponse>()
    val userProfile: LiveData<UserResponse> get() = _userProfile

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun getUserProfile(token: String) {
        viewModelScope.launch {
            try {
                val response = apiService.getProfile("Bearer $token").execute()
                if (response.isSuccessful) {
                    _userProfile.value = response.body()
                } else {
                    val error = response.errorBody()?.string() ?: "Unknown error"
                    _errorMessage.value = "Error fetching user profile: $error"
                    Log.e("UserViewModel", "Error fetching user profile: $error")
                }
            } catch (e: Exception) {
                _errorMessage.value = "An error occurred: ${e.message}"
                Log.e("UserViewModel", "Exception fetching user profile", e)
            }
        }
    }

    fun updateProfile(token: String, file: MultipartBody.Part) {
        viewModelScope.launch {
            try {
                val response = apiService.updateProfile("Bearer $token", file).execute()
                if (response.isSuccessful) {
                    Log.d("UserViewModel", "Success update profile")
                } else {
                    val error = response.errorBody()?.string() ?: "Unknown error"
                    _errorMessage.value = "Error updating profile: $error"
                    Log.e("UserViewModel", "Error updating profile: $error")
                }
            } catch (e: Exception) {
                _errorMessage.value = "An error occurred: ${e.message}"
                Log.e("UserViewModel", "Exception updating profile", e)
            }
        }
    }

    fun deleteProfile(token: String) {
        viewModelScope.launch {
            try {
                val response = apiService.deleteProfile("Bearer $token").execute()
                if (response.isSuccessful) {
                    _userProfile.value = null
                } else {
                    val error = response.errorBody()?.string() ?: "Unknown error"
                    _errorMessage.value = "Error deleting profile: $error"
                    Log.e("UserViewModel", "Error deleting profile: $error")
                }
            } catch (e: Exception) {
                _errorMessage.value = "An error occurred: ${e.message}"
                Log.e("UserViewModel", "Exception deleting profile", e)
            }
        }
    }
}