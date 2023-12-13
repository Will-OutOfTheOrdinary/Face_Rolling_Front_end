package com.example.face_rolling.ui.postLog.message

import com.example.face_rolling.R

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.face_rolling.MyViewModel
import com.example.face_rolling.data.Chat


@Composable
fun ChatList(chats: List<Chat>) {

    Column {

        Column(Modifier.weight(0.6f)) {
            ChatTopBar(
                title = "消息",
                iconList = mutableListOf<Int>(R.drawable.icon_magnifier_small, R.drawable.icon_gear)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                itemsIndexed(chats) { _index, it ->
                    ChatListItem(it)
                }
            }
        }
        Column (Modifier.weight(0.4f)){
            ChatTopBar("通知")
        }
    }
}


@Composable
fun Notification() {

}


@Composable
private fun ChatListItem(it: Chat) {
    val viewModel: MyViewModel = viewModel()

    Row(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = it.friend.avatar),
            contentDescription = it.friend.name,
            modifier = Modifier
                .padding(4.dp)
                .size(48.dp)
                .unRead(!it.messages.last().read, Color.Red)
                .clip(RoundedCornerShape(5.dp))
                .clickable {
//                    viewModel.startChat(it)
                }

        )
        Column(
            Modifier
                .padding(6.dp, 0.dp)
                .weight(1f)
        ) {
            Text(
                text = it.friend.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(0.dp, 6.dp, 0.dp, 0.dp)

            )
            Text(text = it.messages.last().text)
        }
        Text(text = it.messages.last().time, modifier = Modifier.padding(8.dp))
    }
}

/**
 * 红点绘制
 */
fun Modifier.unRead(show: Boolean, color: Color): Modifier = this.drawWithContent {
    drawContent()
    if (show) {
        drawCircle(
            color,
            5.dp.toPx(),
            Offset((size.width - 3), 3.0.toFloat())
        )
    }
}

/**
 * @param title 标题
 * @param onBack 是否有点击返回事件
 * @param iconList 右侧icon的id集合
 */
@Composable
fun ChatTopBar(title: String, onBack: (() -> Unit)? = null, iconList: List<Int> = mutableListOf()) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = title,
                modifier = Modifier
                    .padding(10.dp),
//                    .fillMaxWidth()
//                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,

                )
            Spacer(modifier = Modifier.weight(1f))
            if (iconList.isNotEmpty()) {
                for (i in iconList) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(id = i),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(10.dp)
                                .size(20.dp) )
                    }
                }
            }
        }
    }
}