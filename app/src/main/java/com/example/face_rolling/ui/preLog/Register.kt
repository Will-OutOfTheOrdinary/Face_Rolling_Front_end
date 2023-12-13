package com.example.face_rolling.ui.preLog

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.face_rolling.R
import com.example.face_rolling.bean.VerificationBean
import com.example.face_rolling.network.NetworkRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register() {

    var phone by remember { mutableStateOf("") }
    var verification by remember { mutableStateOf("") }
    var password1 by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(30.dp)
    ) {
        Text(text = "注册")
        Text(text = "请输入您的账号密码")
        Image(painter = painterResource(id = R.drawable.icon_person), contentDescription = null)
        OutlinedTextField(
            value = phone,
            onValueChange = {
                phone = it
            },
            placeholder = { Text(text = "请输入手机号/邮箱") },
        )

        Row {

            OutlinedTextField(
                value = verification,
                onValueChange = {
                    verification = it
                },
                placeholder = { Text(text = "请输入验证码") },
                modifier = Modifier.width(160.dp)
            )
            Button(onClick = { /*TODO*/ }) {
                Text(text = "发送验证码")
            }
        }
        OutlinedTextField(value = password1, onValueChange = {
            password1 = it
        },
            placeholder = { Text(text = "请输入密码") })
        OutlinedTextField(value = password2, onValueChange = {
            password2 = it
        },
            placeholder = { Text(text = "请输入验证码") })
        Button(onClick = {
            val call = NetworkRequest.getVerification()
            call.enqueue(object : Callback<VerificationBean> {
                override fun onResponse(
                    call: Call<VerificationBean>,
                    response: Response<VerificationBean>
                ) {
                    Log.d("tag", "onResponse: ${response.body().toString()}")
                }

                override fun onFailure(call: Call<VerificationBean>, t: Throwable) {
                    t.printStackTrace()
                }

            })
        }) {
            Text(text = "注册并登录")
        }
    }
}

@Preview
@Composable
fun viewRegister() {
    Surface(Modifier.fillMaxSize()) {
//        Register()
    }
}