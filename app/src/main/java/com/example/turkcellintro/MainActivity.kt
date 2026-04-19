package com.example.turkcellintro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.turkcellintro.model.Todo
import com.example.turkcellintro.viewmodel.ToDoListViewModel

sealed class Screen(val route: String) {
    data object Register: Screen("register")
    data object Homepage: Screen("homepage")
    data object AddTodo: Screen("add_todo")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold { paddingValues ->
                MyNavigatableApp(Modifier.padding(paddingValues))
            }
        }
    }
}

@Composable
fun MyNavigatableApp(modifier: Modifier) {
    val navController = rememberNavController()

    // ÇÖZÜM: ViewModel'i burada tek bir kez oluşturuyoruz (Tek Kaynak - Single Source of Truth)
    val sharedViewModel: ToDoListViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Homepage.route) {
        composable(Screen.Register.route) {
            RegisterScreen(modifier, navController)
        }
        composable(Screen.Homepage.route) {
            // ViewModel'i parametre olarak içeri yolluyoruz
            Homepage(modifier, navController, sharedViewModel)
        }
        composable(Screen.AddTodo.route) {
            // Aynı ViewModel'i ekleme sayfasına da yolluyoruz
            AddTodoScreen(modifier, navController, sharedViewModel)
        }
    }
}

@Composable
fun Homepage(modifier: Modifier, navController: NavController, todoViewModel: ToDoListViewModel) {

    val todos by todoViewModel.todos.collectAsState()
    val isLoading by todoViewModel.isLoading.collectAsState()
    val error by todoViewModel.error.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.AddTodo.route)
            }) {
                Icon(Icons.Default.Add, contentDescription = "Ekle")
            }
        }
    ) { innerPadding ->
        Column(modifier = modifier.fillMaxSize().padding(innerPadding)) {
            when {
                isLoading -> { Text("Yükleniyor...", modifier = Modifier.padding(16.dp)) }
                error != null -> { Text("Hata: $error", modifier = Modifier.padding(16.dp)) }
                else -> {
                    if (todos.isEmpty()) {
                        Text("Görev listesi boş.", modifier = Modifier.padding(16.dp))
                    } else {
                        ToDoList(todos) { id ->
                            todoViewModel.deleteTodo(id)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ToDoList(toDoList: List<Todo>, onDelete: (Int) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(toDoList, key = { it.id }) { todo ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = todo.title, style = MaterialTheme.typography.titleLarge)
                    todo.description?.let { Text(text = it, style = MaterialTheme.typography.bodyMedium) }
                }
                IconButton(onClick = { onDelete(todo.id) }) {
                    Icon(Icons.Default.Close, contentDescription = "Sil")
                }
            }
        }
    }
}