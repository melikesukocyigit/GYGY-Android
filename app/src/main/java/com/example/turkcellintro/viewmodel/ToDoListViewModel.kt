package com.example.turkcellintro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turkcellintro.data.TodoRepository
import com.example.turkcellintro.model.Todo
import com.example.turkcellintro.model.TodoRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ToDoListViewModel : ViewModel() {
    private val repository = TodoRepository()
    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        fetchTodos()
    }

    fun fetchTodos() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val result = repository.getTodos()
                _todos.value = result
            } catch (e: Exception){
                _error.value = e.message ?: "Veriler çekilirken bir hata oluştu."
            } finally {
                _isLoading.value = false
            }
        }
    }

    // TODO 1 Çözümü: Silme İşlemi ve Veri Yenileme
    fun deleteTodo(id: Int) {
        viewModelScope.launch {
            try {
                repository.deleteTodo(id)
                fetchTodos()
            } catch (e: Exception) {
                _error.value = "Silme işlemi başarısız: ${e.message}"
            }
        }
    }

    // TODO 2 Çözümü: Ekleme İşlemi ve Veri Yenileme
    fun addTodo(title: String, description: String? = null) {
        viewModelScope.launch {
            try {
                val newTodoRequest = TodoRequest(title = title, description = description)
                repository.addTodo(newTodoRequest)
                fetchTodos()
            } catch (e: Exception) {
                _error.value = "Ekleme işlemi başarısız: ${e.message}"
            }
        }
    }}