package com.example.face_rolling

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.face_rolling.data.Chat
import com.example.face_rolling.data.Course
import com.example.face_rolling.data.Message
import com.example.face_rolling.data.Team
import com.example.face_rolling.data.User
import com.example.face_rolling.data.User.Companion.Me
import com.example.face_rolling.data.User.Companion.hjj
import com.example.face_rolling.data.User.Companion.szx
import java.util.Date

@SuppressLint("MutableCollectionMutableState")
class MyViewModel : ViewModel() {
    var selectedTab by mutableIntStateOf(0)
    var ifCanLogin :Boolean by mutableStateOf(false)

    var selectedPersonTab by mutableIntStateOf(0)

    //to select login or register
    var preIndex by mutableIntStateOf(1)




    var courseList = mutableListOf<Course>(
        Course("阮公会议", "03-22", "09:00", "11:00"),
        Course("阮公会议", "03-22", "09:00", "11:00")

    )
    var teamList by mutableStateOf(
        mutableListOf(
            Team("策划部"),
            Team("技术部"),
            Team("人力部"),
        )
    )

    var chatList by mutableStateOf(
        listOf<Chat>(
            Chat(
                friend = szx,
                mutableListOf(
                    Message(szx, "我看了下，我们得先交这个啊", "12:50:00"),
                    Message(User.Me, "哦哦", "12:50:00"),
                    Message(szx, "wok", "12:51:00"),
                    Message(szx, "人家这代码规范写那么多", "12:51:00"),
                    Message(User.Me, "搜的", "12:51:00"),
                )
            ),
            Chat(
                friend = hjj,
                mutableListOf(
                    Message(Me, "就是啥都没有啊", "19:43:04"),
                    Message(User.Me, "因为没传啊", "19:43:04"),
                    Message(User.Me, "我网卡，github一直没加载出来", "19:43:04"),
                    Message(User.Me, "空仓库", "19:43:04"),
                    Message(hjj, "我传不了啊", "19:46:00"),
                    Message(hjj, "我也不能新建文件夹", "19:46:00"),
                    Message(hjj, "我也不能新建文件夹", "19:47:00"),
                    Message(User.Me, "哦哦", "19:48:00"),
                )
            )
        )
    )
    fun login(account: String, password: String) {
        ifCanLogin = account == "1" && password == "2"

    }

    //Calendar
    //viewModel类里的selectDay用作动态更新，MutableState 类是一个单一的值持有者，其读取和写入由 Compose 观察，当值发生变化会更新ui。
    var selectDay by mutableIntStateOf(1)
}
