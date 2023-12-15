package com.example.face_rolling.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.boguszpawlowski.composecalendar.sample.CustomComponentsSample
import io.github.boguszpawlowski.composecalendar.sample.CustomSelectionSample

import io.github.boguszpawlowski.composecalendar.sample.StaticCalendarSample
import io.github.boguszpawlowski.composecalendar.sample.ViewModelSample
import io.github.boguszpawlowski.composecalendar.sample.WeekCalendarSample

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
//    if (BuildConfig.DEBUG) {
//      Timber.plant(DebugTree())
//    }
    setContent {
      MainScreen()
    }
  }
}

@Composable
fun MainScreen() {
  MaterialTheme(
    colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
  ) {
    Surface(
      modifier = Modifier.fillMaxSize(),
    ) {
      val navController = rememberNavController()

      NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainMenu(navController = navController) }
        composable("static") { StaticCalendarSample() }
        composable("week") { WeekCalendarSample() }
        composable("selection") { SelectableCalendarSample() }
        composable("components") { CustomComponentsSample() }
        composable("custom_selection") { CustomSelectionSample() }
        composable("viewmodel") { ViewModelSample() }
//        composable("kotlinx_datetime") { KotlinXDateTimeSample() }
      }
    }
  }
}

@Composable
fun MainMenu(navController: NavController) {
  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Button(onClick = { navController.navigate("static") }) {
      Text(text = "Static Calendar")
    }
    Spacer(modifier = Modifier.height(16.dp))

    Button(onClick = { navController.navigate("selection") }) {
      Text(text = "Selectable Calendar")
    }
    Spacer(modifier = Modifier.height(16.dp))

    Button(onClick = { navController.navigate("week") }) {
      Text(text = "Week Calendar")
    }
    Spacer(modifier = Modifier.height(16.dp))

    Button(onClick = { navController.navigate("components") }) {
      Text(text = "Custom Components")
    }
    Spacer(modifier = Modifier.height(16.dp))

    Button(onClick = { navController.navigate("custom_selection") }) {
      Text(text = "Custom Selection")
    }
    Spacer(modifier = Modifier.height(16.dp))

    Button(onClick = { navController.navigate("viewmodel") }) {
      Text(text = "ViewModel")
    }
    Spacer(modifier = Modifier.height(16.dp))

//    Button(onClick = { navController.navigate("kotlinx_datetime") }) {
//      Text(text = "Kotlinx DateTime")
//    }
  }
}
