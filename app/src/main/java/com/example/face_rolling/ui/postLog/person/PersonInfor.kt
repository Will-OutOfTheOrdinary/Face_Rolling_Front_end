package com.example.face_rolling.ui.postLog.person

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.face_rolling.R
import com.example.face_rolling.data.User

@Composable
fun PersonInfor(user: User) {

    Column {
        IconButton(
            onClick = { /*TODO*/ }, modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        ) {
            Icon(painter = painterResource(id = R.drawable.icon_camera), contentDescription = null)

        }
        Information(user)

    }

}

@Composable
fun Information(user: User) {
    var infor: List<String> = mutableListOf("昵称","性别","地区","手机号","邮箱")
    Column(){
        InforItem(user = user, p = User.Property.Name)
        }

    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InforItem( user: User, p: User.Property) {
        Row {
            when (p) {
                User.Property.Name -> {
                    Text(text = user.name, modifier = Modifier.weight(00.5f))
                    TextField(value = user.name, onValueChange = {
                        user.name = it
                    } )
                }

                else -> {}
            }


        }
}

