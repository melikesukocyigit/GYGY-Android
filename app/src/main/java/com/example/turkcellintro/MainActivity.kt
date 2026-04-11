package com.example.turkcellintro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold {
                    paddingValues ->  MyAppStart(Modifier.padding(paddingValues))
            }
        }
    }
}

@Composable
fun MyAppStart(modifier: Modifier)
{
    // State'i tanımla ki..
    // ikisi de burayı okuyabilsin-değiştirebilsin..
    var toDoList = remember { mutableStateListOf("Veri 1", "Veri 2","Veri 3") }


    Column(modifier = modifier.fillMaxSize()) {
        SingleInputExample() // child 1
        ToDoList(toDoList, onDelete = {i -> toDoList.removeAt(i)}) // child 2
    }
}
// State Hoisting -> State'i child(lar)dan alıp parent'a taşımak.

// State aynı
@Composable
fun SingleInputExample() {
    var text = remember { mutableStateOf("abc") }

    Column() {
        Text("Todo List")
        TextField(
            value=text.value, // Statik olduğu sürece ekranda güncellenmez.
            onValueChange = { newValue -> text.value = newValue }
        )
        Button(
            onClick = {
            }
        ) {
            Text("Tıkla")
        }
    }
}
// State aynı
@Composable
fun ToDoList(toDoList: List<String>, onDelete: (Int) -> Unit) {

    LazyColumn(modifier = Modifier.fillMaxSize())
    {
        itemsIndexed(toDoList) {
                index,todo ->
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Text(todo)
                IconButton(onClick = {
                    onDelete(index)
                }) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Sil")
                }
            }
        }
    }
}