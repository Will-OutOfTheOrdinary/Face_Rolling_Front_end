package com.example.face_rolling

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.face_rolling.ui.postLog.ChooseExcelFileScreen
import com.example.face_rolling.ui.postLog.calendar.Calendar
import com.example.face_rolling.ui.postLog.home.Home
import com.example.face_rolling.ui.postLog.home.UpPhoto
import com.example.face_rolling.ui.postLog.message.ChatList
import com.example.face_rolling.ui.postLog.person.ImageSelectionScreen
import com.example.face_rolling.ui.postLog.person.Person
import com.example.face_rolling.ui.postLog.team.Team
import com.example.face_rolling.ui.postLog.team.TeamMemberListShow

//import com.example.face_rolling.util.photo.MainScreen


@SuppressLint("StaticFieldLeak")
@Composable
fun NavigationControl(viewModel: MyViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home_group",) {

        homeGraph(navController, viewModel)

//        composable("main_screen"){
//            MainScreen()
//        }
        composable("person") { Person(navController, viewModel = viewModel)}
        composable("up_excel_file") { ChooseExcelFileScreen()}


        composable("team") { Team(viewModel = viewModel,navController)}
        composable("image_selected") { ImageSelectionScreen(viewModel = viewModel)}
        composable("team_list_show") {
            TeamMemberListShow(viewModel.teamMemberList)
        }

        composable("message") { ChatList(viewModel.chatList) }

        composable("my_calendar") { Calendar() }


    }

    when (viewModel.selectedTab) {
        0 -> {
//            navController.navigate("main_screen"){popUpTo("main_screen") { inclusive = true }}

            navController.navigate("home_group"){popUpTo("person") { inclusive = true }}
        }
        1 -> {
            navController.navigate("my_calendar"){ popUpTo("home_group") { inclusive = false }}
//            navController.navigate("main_screen")
        }

        2 -> {
            navController.navigate("team"){ popUpTo("my_calendar") { inclusive = true }}

        }

//        3 -> {
//            navController.navigate("message"){ popUpTo("team") { inclusive = true }}
//        }
//        4 -> {
//            navController.navigate("person"){ popUpTo("message") { inclusive = true }}
//        }
        4 -> {
            navController.navigate("person"){ popUpTo("team") { inclusive = true }}
        }
    }
}

fun NavGraphBuilder.homeGraph(navController: NavController,viewModel: MyViewModel) {
    navigation(startDestination = "home", route = "home_group") {
        composable("home") { Home(viewModel)}
        composable("up_to_photo") { UpPhoto(navController, null) }
    }
}