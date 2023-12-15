package com.example.face_rolling.ui.postLog.person

import BottomSheetModal
import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Bottom
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.face_rolling.MyViewModel
import com.example.face_rolling.R
import com.example.face_rolling.bean.UserBean
import com.example.face_rolling.data.User
import com.example.face_rolling.data.User.Companion.Me
import com.example.face_rolling.network.NetworkRequest
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import com.example.face_rolling.network.Constant.BASE_URL
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Person(navController: NavController, viewModel: MyViewModel) {
    val user = Me
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "个人主页", style = MaterialTheme.typography.h5)
        Card(
//            backgroundColor = MaterialTheme.colors.surface,
            shape = RoundedCornerShape(10.dp),
//            elevation = CardDefaults.,
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                AsyncImage(
                    model = user.avatarUri,

                    contentDescription = null,
                    modifier = Modifier
                        .width(48.dp)
                        .height(48.dp)
                        .clip(CircleShape)
                        .placeholder(
                            visible = false,
                            color = Color(231, 234, 239, 255),
                            highlight = PlaceholderHighlight.shimmer(),
                        ),
                    contentScale = ContentScale.Crop,
                )
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(text = "账号：${user.account}")
            }
        }
        Setting(navController, viewModel)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Setting(navController: NavController, viewModel: MyViewModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "设置",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f))
        LazyColumn {
            item {
                SettingIcon(
                    iconId = R.drawable.icon_person_1,
                    title = "个人资料",
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                )
            }
            item {
                SettingIcon(
                    iconId = R.drawable.icon_person_2,
                    title = "人脸绑定",
                    onClick = {

                        //人脸绑定
                        viewModel.toggleBottomSheet(0,AVATAR_UPLOAD)

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                )
            }
            item {
                SettingIcon(
                    iconId = R.drawable.icon_person_3,
                    title = "建议与反馈",
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                )
            }
            item {
                SettingIcon(
                    iconId = R.drawable.icon_person_4,
                    title = "历史数据",
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                )
            }
            item {
                Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f), thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
            }
            item {
                Text(
                    text = "文件",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
            item {
                SettingIcon(
                    iconId = R.drawable.icon_person_file,
                    title = "创建团队",
                    onClick = { navController.navigate("up_excel_file") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                )
            }
            item {
                Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f), thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
            }
            item {
                SettingIcon(
                    iconId = R.drawable.icon_person_5,
                    title = "退出登录",
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                )
            }
        }
    }
}

@Composable
fun SettingIcon(
    @DrawableRes iconId: Int,
    title: String,
    tint: Color = MaterialTheme.colors.onSurface,
    onClick: () -> Unit,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.clickable(onClick = onClick)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = null,

                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.h5,
                color = tint
            )
        }
    }
}
