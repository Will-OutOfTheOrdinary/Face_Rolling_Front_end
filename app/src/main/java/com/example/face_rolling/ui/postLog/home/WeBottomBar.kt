package com.example.face_rolling.ui.postLog.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import com.example.face_rolling.R
import com.example.face_rolling.ui.theme.LightBlue

val pageNameList = listOf<String>("首页", "日历", "团队", "通知", "个人")

@Composable
fun WeBottomBar(selected: Int, onSelectedChanged: (Int) -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.fillMaxWidth()
        ) {
            TabItem(
                R.drawable.icon_home,
                "首页",
                if (selected == 0) LightBlue else Color.Gray,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onSelectedChanged(0)
                    }
            )
            TabItem(
                R.drawable.icon_calindar,
                "日历",
                if (selected == 1) LightBlue else Color.Gray,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onSelectedChanged(1)
                    }
            )
            TabItem(
                R.drawable.icon_team,
                "团队",
                if (selected == 2) LightBlue else Color.Gray,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onSelectedChanged(2)
                    }
            )
//            TabItem(
//                R.drawable.icon_message,
//                "通知",
//                if (selected == 3) LightBlue else Color.Gray,
//                modifier = Modifier
//                    .weight(1f)
//                    .clickable {
//                        onSelectedChanged(3)
//                    }
//            )
//            TabItem(
//                R.drawable.icon_person,
//                "个人",
//                if (selected == 4) LightBlue else Color.Gray,
//                modifier = Modifier
//                    .weight(1f)
//                    .clickable {
//                        onSelectedChanged(4)
//                    }
//            )
            TabItem(
                R.drawable.icon_person,
                "个人",
                if (selected == 4) LightBlue else Color.Gray,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onSelectedChanged(4)
                    }
            )
        }
    }
}

@Composable
fun TabItem(
    @DrawableRes iconId: Int,
    title: String,
    tint: Color,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = title,
            tint = tint,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = title,
            color = tint,
            fontSize = 11.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun show() {
    var selectedTab by remember { mutableStateOf(0) }
    WeBottomBar(selectedTab) {
        selectedTab = it
    }
}
