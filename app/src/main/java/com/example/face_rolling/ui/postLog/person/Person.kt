package com.example.face_rolling.ui.postLog.person

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.face_rolling.MyViewModel
import com.example.face_rolling.R
import com.example.face_rolling.data.User

@Composable
fun Person(navController: NavController,user: User,viewModel: MyViewModel) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Text(text = "个人主页")
        Image(
            painter = painterResource(id = user.avatar), contentDescription = null
        )
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = user.name)
            Text(text = "账号： ${user.account}")
        }

        Surface(
            shadowElevation = 1.dp,
            tonalElevation = 3.dp,
            modifier = Modifier
                .padding(60.dp)
                .fillMaxWidth()

                .clip(RoundedCornerShape(10.dp))
        ) {
            Setting(navController) {

            }
        }
    }
}

@Composable
fun Setting(navController: NavController, onSelectedChanged: (Int) -> Unit) {
    Column {
        Text(text = "设置")
        SettingIcon(
            R.drawable.icon_person_1,
            "个人资料",
            modifier = Modifier.clickable {
//                navController.navigate()
            }

        )
        SettingIcon(
            R.drawable.icon_person_2,
            "人脸绑定",
        )
        SettingIcon(
            R.drawable.icon_person_3,
            "建议与反馈",
        )
        SettingIcon(
            R.drawable.icon_person_4,
            "历史数据",
        )
        SettingIcon(
            R.drawable.icon_person_5,
            "退出登录",
        )
    }
}

@Composable
fun SettingIcon(
    @DrawableRes iconId: Int,
    title: String,
    tint: Color = Color.Black,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {

    Row (modifier = modifier){
        Image(
            painter = painterResource(id = iconId), contentDescription = null,
        )
        Text(text = title, color = tint)
    }

}
