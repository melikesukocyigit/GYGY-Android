package com.example.turkcellintro.model

import kotlinx.serialization.Serializable

@Serializable
data class Todo(
    val id: Int,
    val title: String,
    val description: String? = null
)
{

}