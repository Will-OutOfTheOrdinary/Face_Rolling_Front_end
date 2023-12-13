package com.example.face_rolling

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.face_rolling.data.User
import com.example.face_rolling.ui.postLog.calendar.Calendar
import com.example.face_rolling.ui.postLog.home.Home
import com.example.face_rolling.ui.postLog.home.UpPhoto
import com.example.face_rolling.ui.postLog.message.ChatList
import com.example.face_rolling.ui.postLog.person.Person
import com.example.face_rolling.ui.postLog.team.Team
import com.example.face_rolling.util.photo.MainScreen


@SuppressLint("StaticFieldLeak")
@Composable


fun NavigationControl(viewModel: MyViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home_group",) {

        homeGraph(navController, viewModel)

        composable("main_screen"){
            MainScreen()
        }
        composable("person") { Person(navController,user = User.Me, viewModel = viewModel)}
        composable("team") { Team(viewModel = viewModel)}
        composable("message") { ChatList(viewModel.chatList) }

        composable("my_calendar") { Calendar() }


    }

    when (viewModel.selectedTab) {
        0 -> {
            navController.navigate("main_screen")
//            navController.navigate("home_group")
        }
        1 -> {
            navController.navigate("my_calendar")
//            navController.navigate("main_screen")
        }

        2 -> {
            navController.navigate("team")

        }

        3 -> {
            navController.navigate("message")
        }
        4 -> {
            navController.navigate("person")
        }
    }
}

fun NavGraphBuilder.homeGraph(navController: NavController,viewModel: MyViewModel) {
    navigation(startDestination = "home", route = "home_group") {
        composable("home") { Home(viewModel)}
        composable("up_to_photo") { UpPhoto(navController, null) }
    }
}