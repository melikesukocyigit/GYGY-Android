package com.example.turkcellintro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.turkcellintro.ui.theme.TurkcellIntroTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var name: String = "Halit"
        enableEdgeToEdge()
        setContent {
            Scaffold {
                    paddingValues ->  MyAppStart(Modifier.padding(paddingValues))
            }
        }
    }
}

// Bir fonksiyon eğer UI fonksiyonu ise.. @Composable olması zorunludur..
@Composable
fun MyAppStart(modifier: Modifier) {
    // Normal tanımlama => Ekranda değişikliği göremezsin
    //var count:Int=0

    // Androidde ekranı etkileyecek her türlü değişken bu şekilde tanımlanır..
    // Android değişkeni..

    val todos = listOf<String>(
        "Todo 1",
        "Todo 2",
        "Todo 3"
    )

    Column( modifier = modifier, verticalArrangement = Arrangement.SpaceAround ) {
        //Sayac()
        //Test()
        Liste(todos)
    }
}

@Composable
fun Liste(
    todos: List<String>
) {
    LazyColumn(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(todos) {
                todo -> ListeElemanı("Yapılacak İş: $todo")
        }
    }
}

@Composable
fun ListeElemanı(todo:String){
    Box(modifier = Modifier.fillMaxSize().padding(15.dp).background(Color.Gray).border(1.dp, Color.Red)){
        Text(todo)
    }
}

@Composable
fun Test() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red))
    {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .background(Color.Blue),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Merhaba")
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                Text("Merhaba 4")
                Text("Merhaba 5")
            }
            Text("Merhaba 2")
            Text("Merhaba 3")
        }
    }
}

@Composable
fun Sayac() {
    var count = remember { mutableStateOf(0) }

    Text("Sayı ${count.value}")
    Button(onClick = {
        count.value++
    }) {
        Text("Tıkla")
    }
}

// Recomposition => Ekranın ilgili kısmının tekrar çalıştırılmasına kabaca recomposition denir.