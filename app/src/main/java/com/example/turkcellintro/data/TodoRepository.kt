package com.example.turkcellintro.data

import com.example.turkcellintro.di.SupabaseClient
import com.example.turkcellintro.model.Todo
import com.example.turkcellintro.model.TodoRequest // Bunu import ettik
import io.github.jan.supabase.postgrest.postgrest

class TodoRepository {
    private val db = SupabaseClient.supabaseClient.postgrest

    suspend fun getTodos(): List<Todo> {
        return db.from("todos").select().decodeList()
    }

    suspend fun deleteTodo(id: Int) {
        db.from("todos").delete {
            filter {
                Todo::id eq id
            }
        }
    }
    suspend fun addTodo(todoRequest: TodoRequest) {
        db.from("todos").insert(todoRequest)
    }
}