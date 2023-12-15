package com.example.face_rolling.ui.preLog

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.face_rolling.MyViewModel
import com.example.face_rolling.R
import com.example.face_rolling.bean.UserBean
import com.example.face_rolling.data.User.Companion.Me
import com.example.face_rolling.network.NetworkRequest
import com.example.face_rolling.store.SharedPreferencesHelper
import com.example.face_rolling.util.ToastUtils
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


val TAG: String = "tag"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(viewModel: MyViewModel, sharedPreferencesHelper: SharedPreferencesHelper) {



    var account by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val toastUtils = ToastUtils.getInstance(LocalContext.current)
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "欢迎登录", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))

                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = account,
                    onValueChange = { account = it },
                    label = { Text(text = "请输入账号") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = "请输入密码") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                //网络请求登录
                Button(
                    onClick = {
                        val call = NetworkRequest.login(account, password)
                        call.enqueue(object : Callback<UserBean> {
                            override fun onResponse(
                                call: Call<UserBean>,
                                response: Response<UserBean>
                            ) {
                                Log.d(TAG, "onResponse: $response")
                                if (response.isSuccessful) {
                                    val bean = response.body()
                                    Log.d(TAG, "onResponse: ${bean.toString()}")

                                    //存储个人信息
                                        sharedPreferencesHelper.saveData(
                                            "me",
                                            Gson().toJson(bean?.data)
                                        )

                                    if (bean?.message == "success") {
                                        toastUtils.show("登录成功")
                                        sharedPreferencesHelper.saveData("ifCanLogin", "true")
                                        Log.d(TAG, "onResponse: success")
                                        viewModel.ifLoginAuto =true
                                        viewModel.ifCanLogin = true
                                    }
                                }
                            }

                            override fun onFailure(call: Call<UserBean>, t: Throwable) {
                                toastUtils.show("网络请求失败")
                                t.printStackTrace()
                            }
                        })
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "登录")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = {
                        viewModel.preIndex = 2
                    }) {
                        Text(text = "注册")
                    }
                }
            }
        }
    }

}