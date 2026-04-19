package com.example.turkcellintro.di

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClient
{
    val supabaseClient = createSupabaseClient(
        supabaseUrl = "https://llvuglebsweyrcakjcdy.supabase.co",
        supabaseKey = "sb_publishable_OoTOOJ2BccbvBCzDkItxcw_ScVFB6-L"
    ) {
        install(Postgrest)
    }
}