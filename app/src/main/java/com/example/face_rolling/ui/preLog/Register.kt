package com.example.face_rolling.ui.preLog

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.face_rolling.MyViewModel
import com.example.face_rolling.R
import com.example.face_rolling.bean.UserBean
import com.example.face_rolling.bean.VerificationBean
import com.example.face_rolling.network.NetworkRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun Register() {
//    val viewModel: MyViewModel = viewModel()
//    var phone by remember { mutableStateOf("") }
//    var verification by remember { mutableStateOf("") }
//    var password1 by remember { mutableStateOf("") }
//    var password2 by remember { mutableStateOf("") }
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(30.dp)
//    ) {
//        Text(text = "注册")
//        Text(text = "请输入您的账号密码")
//        Image(painter = painterResource(id = R.drawable.icon_person), contentDescription = null)
//        OutlinedTextField(
//            value = phone,
//            onValueChange = {
//                phone = it
//            },
//            label = { Text(text = "请输入手机号/邮箱") },
//        )
//
//        Row {
//
//            OutlinedTextField(
//                value = verification,
//                onValueChange = {
//                    verification = it
//                },
//                label = { Text(text = "请输入验证码") },
//                modifier = Modifier.width(160.dp)
//            )
//            Button(onClick = {
//                val call = NetworkRequest.getVerification(phone)
//                call.enqueue(object : Callback<VerificationBean> {
//                    override fun onResponse(
//                        call: Call<VerificationBean>,
//                        response: Response<VerificationBean>
//                    ) {
//                        println(response.body().toString())
//                    }
//
//                    override fun onFailure(call: Call<VerificationBean>, t: Throwable) {
//                        t.printStackTrace()
//                    }
//
//                })
//            }) {
//                Text(text = "发送验证码")
//            }
//        }
//        OutlinedTextField(
//            value = password1,
//            onValueChange = {
//                password1 = it
//            },
//            label = { Text(text = "请输入密码") },
//            visualTransformation = PasswordVisualTransformation()
//        )
//        OutlinedTextField(value = password2, onValueChange = {
//            password2 = it
//        },
//            label = { Text(text = "请再次输入密码") },
//            visualTransformation = PasswordVisualTransformation()
//            )
//        Button(onClick = {
//            val call = NetworkRequest.register(phone, verification, password1, password2, "user")
//            call.enqueue(object : Callback<UserBean> {
//                override fun onResponse(call: Call<UserBean>, response: Response<UserBean>) {
//                    println(response)
//                    var bean = response.body()
//                    println(bean.toString())
//                    if (bean!!.message == "success") {
//                        viewModel.preIndex = 1
//                    }
//                }
//
//                override fun onFailure(call: Call<UserBean>, t: Throwable) {
//                    t.printStackTrace()
//                }
//
//            })
//        }) {
//            Text(text = "注册并登录")
//
//        }
//    }
//}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register() {
    val viewModel: MyViewModel = viewModel()
    var phone by remember { mutableStateOf("") }
    var verification by remember { mutableStateOf("") }
    var password1 by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("") }

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
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp)
            ) {
                Text(text = "注册", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = "请输入您的账号密码", fontSize = 14.sp)
//        Image(
//            painter = painterResource(id = R.drawable.icon_person),
//            contentDescription = null,
//            modifier = Modifier.size(100.dp)
//        )
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text(text = "请输入手机号/邮箱") },
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = verification,
                        onValueChange = { verification = it },
                        label = { Text(text = "请输入验证码") },
                        modifier = Modifier.weight(1f)
                    )
                    Button(
                        onClick = {
                            val call = NetworkRequest.getVerification(phone)
                            call.enqueue(object : Callback<VerificationBean> {
                                override fun onResponse(
                                    call: Call<VerificationBean>,
                                    response: Response<VerificationBean>
                                ) {
                                    println(response.body().toString())
                                }

                                override fun onFailure(call: Call<VerificationBean>, t: Throwable) {
                                    t.printStackTrace()
                                }
                            })
                        },
                        modifier = Modifier.width(160.dp)
                    ) {
                        Text(text = "发送验证码")
                    }
                }

                OutlinedTextField(
                    value = password1,
                    onValueChange = { password1 = it },
                    label = { Text(text = "请输入密码") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = password2,
                    onValueChange = { password2 = it },
                    label = { Text(text = "请再次输入密码") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        val call =
                            NetworkRequest.register(
                                phone,
                                verification,
                                password1,
                                password2,
                                "user"
                            )
                        call.enqueue(object : Callback<UserBean> {
                            override fun onResponse(
                                call: Call<UserBean>,
                                response: Response<UserBean>
                            ) {
                                println(response)
                                var bean = response.body()
                                println(bean.toString())
                                if (bean!!.message == "success") {
                                    viewModel.preIndex = 1
                                }
                            }

                            override fun onFailure(call: Call<UserBean>, t: Throwable) {
                                t.printStackTrace()
                            }
                        })
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "注册并登录")
                }
            }
        }
    }
}