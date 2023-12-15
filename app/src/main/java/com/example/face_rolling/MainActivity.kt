package com.example.face_rolling

import BottomSheetModal
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.face_rolling.data.User
import com.example.face_rolling.data.User.Companion.Me
import com.example.face_rolling.store.SharedPreferencesHelper
import com.example.face_rolling.ui.postLog.home.WeBottomBar
import com.example.face_rolling.ui.postLog.person.ImageSelectionScreen
import com.example.face_rolling.ui.preLog.Login
import com.example.face_rolling.ui.preLog.Register
import com.example.face_rolling.ui.theme.Face_RollingTheme
import com.example.face_rolling.ui.theme.White
import com.example.face_rolling.util.ToastUtils
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.gson.Gson

@SuppressLint("StaticFieldLeak")

class MainActivity : ComponentActivity() {

    private val viewModel: MyViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreferencesHelper = SharedPreferencesHelper.getInstance(this)
        val toastUtils = ToastUtils.getInstance(this)



        super.onCreate(savedInstanceState)
        setContent {

            Face_RollingTheme {
                rememberSystemUiController().setStatusBarColor(
                    Color.Transparent,
                    darkIcons = androidx.compose.material.MaterialTheme.colors.isLight
                ) // 状态栏背景：透明，字体颜色：黑色
//                rememberSystemUiController().setStatusBarColor(Teal200, darkIcons = false) // 状态栏背景：青色，字体颜色：白色
//                WindowCompat.setDecorFitsSystemWindows(window,false) // 状态栏隐藏（可占用）
                Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ScaffoldExample(viewModel = viewModel, sharedPreferencesHelper)
                }
            }
        }
    }

    override fun onResume() {
        val sharedPreferencesHelper = SharedPreferencesHelper.getInstance(this)
        val value = sharedPreferencesHelper.getData("me","empty")
        if (value != "empty") {
            Me = Gson().fromJson(value, User::class.java)
        }
        super.onResume()
    }
    override fun onBackPressed() {
        if (viewModel.preIndex == 2 && !viewModel.ifCanLogin) {
            viewModel.preIndex = 1
        } else {
            super.onBackPressed()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ScaffoldExample(viewModel: MyViewModel, sharedPreferencesHelper: SharedPreferencesHelper) {

//    var ifLog by mutableStateOf(false)
//    LaunchedEffect(Unit){
//        ifLog = sharedPreferencesHelper.getData("ifCanLogin", "false").toBoolean()
//        Log.d("tag", "ScaffoldExample1: $ifLog")
//    }

    LaunchedEffect(Unit){
        viewModel.ifLoginAuto = sharedPreferencesHelper.getData("ifCanLogin", "false").toBoolean()
    }
    if (viewModel.ifCanLogin || viewModel.ifLoginAuto) {
        PostLog(viewModel = viewModel, sharedPreferencesHelper)
    } else {
        when (viewModel.preIndex) {
            1 -> Login(viewModel = viewModel, sharedPreferencesHelper)
            2 -> Register()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostLog(viewModel: MyViewModel, sharedPreferencesHelper: SharedPreferencesHelper) {

    val navController = rememberNavController()
    var presses by remember { mutableIntStateOf(0) }
    Scaffold(
        bottomBar = {

            // 设置导航栏项目
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestinationId = navBackStackEntry?.destination?.route

//            WeBottomBar(selected = currentDestinationId == item.route) {
            WeBottomBar(selected = viewModel.selectedTab) {
                viewModel.selectedTab = it
            }
        },
        topBar = {
            Surface(modifier = Modifier
                .height(45.dp)
                .fillMaxWidth()
                .background(White),
                ) {
                Column(

                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(White)

                ) {
                    Text(text = "Face_Rolling", fontWeight = FontWeight.Bold)
                }
            }
        }

        ) {


        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentScale = ContentScale.FillBounds,
                contentDescription = null
            )
            NavigationControl(viewModel)

        }
    }


    BottomSheetModal(viewModel.ifBottomSheet, onDismiss = {}) {
        ImageSelectionScreen(viewModel)
    }
}
