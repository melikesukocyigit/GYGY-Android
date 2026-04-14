package com.example.turkcellintro.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ToDoListViewModel : ViewModel()
{
    // To-do list stateini yönetmek
    val toDoList = mutableStateListOf<String>("Veri 1", "Veri 2", "Veri 3")

    fun addToDo(text: String)
    {
        toDoList.add(text);
    }

    fun deleteToDo(index: Int)
    {
        toDoList.removeAt(index);
    }
}