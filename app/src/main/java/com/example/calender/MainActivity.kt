package com.example.calender

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calender.ui.theme.CalenderTheme
import kotlinx.coroutines.launch
import com.example.calender.http.Greeting
import com.example.calender.http.Weather
import com.example.calender.view.CalendarApp

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalenderTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    JsonView()

//                }
                CalendarApp()
            }
        }
    }
}

@Composable
fun JsonView(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    var text: MutableState<List<Weather>?> = remember { mutableStateOf(null) }
    LaunchedEffect(true) {
        scope.launch {
            text.value = try {
                Greeting().greeting()
            } catch (e: Exception) {
                null
//                                e.localizedMessage ?: "error"
            }
        }
    }

    if (text.value != null) {
        LazyColumn {
            items(text.value!!) { item: Weather ->
                Row { Text("${item.day}日 最高気温${item.temperature.max}") }
            }
        }
    } else {
        Text("読み込んでいます。")
    }

}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    CalenderTheme {
//        Greeting("Android")
//    }
//}