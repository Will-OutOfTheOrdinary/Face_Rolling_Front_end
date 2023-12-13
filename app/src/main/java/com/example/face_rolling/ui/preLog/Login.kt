package com.example.face_rolling.ui.preLog

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.face_rolling.MyViewModel
import com.example.face_rolling.NavigationControl
import com.example.face_rolling.R
import com.example.face_rolling.ScaffoldExample
import com.example.face_rolling.ui.theme.Face_RollingTheme


val TAG: String = "tag"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(viewModel: MyViewModel) {
    var account by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Text(text = "欢迎登录")
        Text(text = "请输入您的账号密码")
        Image(painter = painterResource(id = R.drawable.icon_person), contentDescription = null)
        OutlinedTextField(value = account, onValueChange = {
            account = it
        }, label = { Text(text = "请输入账号") })
        OutlinedTextField(value = password, onValueChange = {
            password = it
        }, label = { Text(text = "请输入密码") })
        Button(onClick = {
            viewModel.login(account, password)
        }) {

        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = {
                viewModel.preIndex = 2
            }) {
                Text(text = "注册")
            }

            Text(text = "忘记密码？", Modifier.clickable { })

        }
    }
}

@Preview(showBackground = true)
@Composable
fun view() {
    Surface(modifier = Modifier.fillMaxSize()) {
//  Login()

    }
}