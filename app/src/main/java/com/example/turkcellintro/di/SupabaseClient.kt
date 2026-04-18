package com.example.turkcellintro.di

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClient
{
    val supabaseClient = createSupabaseClient(
        supabaseUrl = "https://wcjhbxskgxjxpthgrhej.supabase.co", // TODO: local.properties'den gelmeli
        supabaseKey = "sb_publishable_Szgrh1f6814wuvwL471jlA_TXlbjvbR"
    ) {
        install(Postgrest)
    }
}