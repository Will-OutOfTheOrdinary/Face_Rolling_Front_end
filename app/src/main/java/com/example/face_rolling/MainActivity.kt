package com.example.face_rolling

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.face_rolling.ui.postLog.home.Home
import com.example.face_rolling.ui.postLog.home.WeBottomBar
import com.example.face_rolling.ui.preLog.Login
import com.example.face_rolling.ui.preLog.Register
import com.example.face_rolling.ui.theme.Face_RollingTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MyViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Face_RollingTheme {
                ScaffoldExample(viewModel = viewModel)
            }
        }
    }

    override fun onBackPressed() {
        if (viewModel.preIndex == 2 && !viewModel.ifCanLogin) {
            viewModel.preIndex = 1
        } else {
            super.onBackPressed()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ScaffoldExample(viewModel: MyViewModel) {

    if (viewModel.ifCanLogin) {
        PostLog(viewModel = viewModel)
    } else {
        when (viewModel.preIndex) {
            1-> Login(viewModel = viewModel)
            2-> Register()
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostLog(viewModel: MyViewModel) {
    var presses by remember { mutableIntStateOf(0) }
    Scaffold(
        bottomBar = {
            WeBottomBar(selected = viewModel.selectedTab) {
                viewModel.selectedTab = it
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { presses++ }) {

            }
        }) {

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
}
@Preview
@Composable
fun view() {
    val viewModel: MyViewModel = viewModel()
    Face_RollingTheme {
        ScaffoldExample(viewModel = viewModel)
    }
}
