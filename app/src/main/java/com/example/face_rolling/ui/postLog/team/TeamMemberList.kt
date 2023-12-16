package com.example.face_rolling.ui.postLog.team

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.face_rolling.data.User

@Composable
fun TeamMemberListShow(userList: List<User>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
//        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.height(20.dp)) {
//            Text(text = "name", Modifier.weight(1f))
//            Spacer(modifier = Modifier.weight(1f))
//            Text(text = it.id.toString(), Modifier.weight(1f))
//        }
        items(userList) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.height(20.dp)) {
                Text(text = it.name, Modifier.weight(1f))
                Spacer(modifier = Modifier.weight(1f))
                Text(text = it.id.toString(), Modifier.weight(1f))
            }
        }
    }
}