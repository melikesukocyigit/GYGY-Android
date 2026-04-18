package com.example.turkcellintro.data

import com.example.turkcellintro.di.SupabaseClient
import com.example.turkcellintro.model.Todo
import io.github.jan.supabase.postgrest.postgrest

class TodoRepository {
    private val db = SupabaseClient.supabaseClient.postgrest
    suspend fun getTodos(): List<Todo> {
        return db.from("todos").select().decodeList()
    }
}