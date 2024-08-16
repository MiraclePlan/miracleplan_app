package com.example.miracleplan.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miracleplan.data.model.TodoCreateRequest
import com.example.miracleplan.data.model.TodoResponse
import com.example.miracleplan.data.model.TodoUpdateRequest
import com.example.miracleplan.data.network.ApiService
import kotlinx.coroutines.launch

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