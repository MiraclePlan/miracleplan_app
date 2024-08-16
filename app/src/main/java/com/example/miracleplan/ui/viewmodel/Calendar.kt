package com.example.miracleplan.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miracleplan.data.model.*
import com.example.miracleplan.data.network.ApiService
import kotlinx.coroutines.launch

class CalendarViewModel(private val apiService: ApiService) : ViewModel() {
    private val _calendarStatus = MutableLiveData<List<CalendarStatusResponse>>()
    val calendarStatus: LiveData<List<CalendarStatusResponse>> get() = _calendarStatus

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun getCalendarStatus(token: String) {
        viewModelScope.launch {
            try {
                val response = apiService.getCalendarStatus("Bearer $token").execute()
                if (response.isSuccessful) {
                    _calendarStatus.value = response.body()
                } else {
                    val error = response.errorBody()?.string() ?: "Unknown error"
                    _errorMessage.value = "Error: $error"
                    Log.e("CalendarViewModel", "Error fetching calendar status: $error")
                }
            } catch (e: Exception) {
                _errorMessage.value = "An error occurred: ${e.message}"
                Log.e("CalendarViewModel", "Exception fetching calendar status", e)
            }
        }
    }
}