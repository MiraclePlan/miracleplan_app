package com.example.miracleplan.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miracleplan.data.model.GroupCreateRequest
import com.example.miracleplan.data.model.GroupResponse
import com.example.miracleplan.data.model.UserResponse
import com.example.miracleplan.data.network.ApiService
import kotlinx.coroutines.launch

class GroupViewModel(private val apiService: ApiService) : ViewModel() {
    private val _groups = MutableLiveData<List<GroupResponse>>()
    val groups: LiveData<List<GroupResponse>> get() = _groups

    private val _notJoinedGroups = MutableLiveData<List<GroupResponse>>()
    val notJoinedGroups: LiveData<List<GroupResponse>> get() = _notJoinedGroups

    private val _groupMembers = MutableLiveData<List<UserResponse>>()
    val groupMembers: LiveData<List<UserResponse>> get() = _groupMembers

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun getJoinedGroups(token: String) {
        viewModelScope.launch {
            try {
                val response = apiService.getJoinedGroups("Bearer $token").execute()
                if (response.isSuccessful) {
                    _groups.value = response.body()
                } else {
                    val error = response.errorBody()?.string() ?: "Unknown error"
                    _errorMessage.value = "Error fetching joined groups: $error"
                    Log.e("GroupViewModel", "Error fetching joined groups: $error")
                }
            } catch (e: Exception) {
                _errorMessage.value = "An error occurred: ${e.message}"
                Log.e("GroupViewModel", "Exception fetching joined groups", e)
            }
        }
    }

    fun createGroup(token: String, groupCreateRequest: GroupCreateRequest) {
        viewModelScope.launch {
            try {
                val response = apiService.createGroup("Bearer $token", groupCreateRequest).execute()
                if (response.isSuccessful) {
                    getJoinedGroups(token)
                } else {
                    val error = response.errorBody()?.string() ?: "Unknown error"
                    _errorMessage.value = "Error creating group: $error"
                    Log.e("GroupViewModel", "Error creating group: $error")
                }
            } catch (e: Exception) {
                _errorMessage.value = "An error occurred: ${e.message}"
                Log.e("GroupViewModel", "Exception creating group", e)
            }
        }
    }

    fun deleteGroup(token: String, groupId: Int) {
        viewModelScope.launch {
            try {
                val response = apiService.deleteGroup(groupId, "Bearer $token").execute()
                if (response.isSuccessful) {
                    getJoinedGroups(token)
                } else {
                    val error = response.errorBody()?.string() ?: "Unknown error"
                    _errorMessage.value = "Error deleting group: $error"
                    Log.e("GroupViewModel", "Error deleting group: $error")
                }
            } catch (e: Exception) {
                _errorMessage.value = "An error occurred: ${e.message}"
                Log.e("GroupViewModel", "Exception deleting group", e)
            }
        }
    }

    fun joinGroup(token: String, groupId: Int) {
        viewModelScope.launch {
            try {
                val response = apiService.joinGroup(groupId, "Bearer $token").execute()
                if (response.isSuccessful) {
                    getJoinedGroups(token)
                } else {
                    val error = response.errorBody()?.string() ?: "Unknown error"
                    _errorMessage.value = "Error joining group: $error"
                    Log.e("GroupViewModel", "Error joining group: $error")
                }
            } catch (e: Exception) {
                _errorMessage.value = "An error occurred: ${e.message}"
                Log.e("GroupViewModel", "Exception joining group", e)
            }
        }
    }

    fun leaveGroup(token: String, groupId: Int) {
        viewModelScope.launch {
            try {
                val response = apiService.leaveGroup(groupId, "Bearer $token").execute()
                if (response.isSuccessful) {
                    getJoinedGroups(token)
                } else {
                    val error = response.errorBody()?.string() ?: "Unknown error"
                    _errorMessage.value = "Error leaving group: $error"
                    Log.e("GroupViewModel", "Error leaving group: $error")
                }
            } catch (e: Exception) {
                _errorMessage.value = "An error occurred: ${e.message}"
                Log.e("GroupViewModel", "Exception leaving group", e)
            }
        }
    }

    fun getNotJoinedGroups(token: String) {
        viewModelScope.launch {
            try {
                val response = apiService.getNotJoinedGroups("Bearer $token").execute()
                if (response.isSuccessful) {
                    _notJoinedGroups.value = response.body()
                } else {
                    val error = response.errorBody()?.string() ?: "Unknown error"
                    _errorMessage.value = "Error: $error"
                    Log.e("GroupViewModel", "Error fetching not-joined groups: $error")
                }
            } catch (e: Exception) {
                _errorMessage.value = "An error occurred: ${e.message}"
                Log.e("GroupViewModel", "Exception fetching not-joined groups", e)
            }
        }
    }

    fun getGroupMembers(token: String, groupId: Int) {
        viewModelScope.launch {
            try {
                val response = apiService.getGroupMembers(groupId, "Bearer $token").execute()
                if (response.isSuccessful) {
                    _groupMembers.value = response.body()
                } else {
                    val error = response.errorBody()?.string() ?: "Unknown error"
                    _errorMessage.value = "Error: $error"
                    Log.e("GroupViewModel", "Error fetching group members: $error")
                }
            } catch (e: Exception) {
                _errorMessage.value = "An error occurred: ${e.message}"
                Log.e("GroupViewModel", "Exception fetching group members", e)
            }
        }
    }
}