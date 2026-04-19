package com.example.turkcellintro.model

import kotlinx.serialization.Serializable

// Listeleme ve silme için bunu kullanıyoruz (ID'si var)
@Serializable
data class Todo(
    val id: Int,
    val title: String,
    val description: String? = null
)


// SADECE ekleme (insert) işlemi için bunu kullanacağız (ID'si YOK)
@Serializable
data class TodoRequest(
    val title: String,
    val description: String? = null
)