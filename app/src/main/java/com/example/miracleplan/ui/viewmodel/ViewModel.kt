package com.example.miracleplan.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miracleplan.data.model.*
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

class TodoViewModel(private val apiService: ApiService) : ViewModel() {
    private val _todos = MutableLiveData<List<TodoResponse>>()
    val todos: LiveData<List<TodoResponse>> get() = _todos

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun getTodos(token: String) {
        viewModelScope.launch {
            try {
                val response = apiService.getTodos("Bearer $token").execute()
                if (response.isSuccessful) {
                    _todos.value = response.body()
                } else {
                    val error = response.errorBody()?.string() ?: "Unknown error"
                    _errorMessage.value = "Error fetching todos: $error"
                    Log.e("TodoViewModel", "Error fetching todos: $error")
                }
            } catch (e: Exception) {
                _errorMessage.value = "An error occurred: ${e.message}"
                Log.e("TodoViewModel", "Exception fetching todos", e)
            }
        }
    }

    fun createTodo(token: String, todoCreateRequest: TodoCreateRequest) {
        viewModelScope.launch {
            try {
                val response = apiService.createTodo("Bearer $token", todoCreateRequest).execute()
                if (response.isSuccessful) {
                    getTodos(token)
                } else {
                    val error = response.errorBody()?.string() ?: "Unknown error"
                    _errorMessage.value = "Error creating todo: $error"
                    Log.e("TodoViewModel", "Error creating todo: $error")
                }
            } catch (e: Exception) {
                _errorMessage.value = "An error occurred: ${e.message}"
                Log.e("TodoViewModel", "Exception creating todo", e)
            }
        }
    }

    fun updateTodo(token: String, todoId: Int, todoUpdateRequest: TodoUpdateRequest) {
        viewModelScope.launch {
            try {
                val response =
                    apiService.updateTodo(todoId, "Bearer $token", todoUpdateRequest).execute()
                if (response.isSuccessful) {
                    getTodos(token)
                } else {
                    val error = response.errorBody()?.string() ?: "Unknown error"
                    _errorMessage.value = "Error updating todo: $error"
                    Log.e("TodoViewModel", "Error updating todo: $error")
                }
            } catch (e: Exception) {
                _errorMessage.value = "An error occurred: ${e.message}"
                Log.e("TodoViewModel", "Exception updating todo", e)
            }
        }
    }
}

class GroupViewModel(private val apiService: ApiService) : ViewModel() {
    private val _groups = MutableLiveData<List<GroupResponse>>()
    val groups: LiveData<List<GroupResponse>> get() = _groups

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
}
