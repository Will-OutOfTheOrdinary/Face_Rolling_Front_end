package com.example.face_rolling.ui.postLog.home

import android.net.wifi.hotspot2.pps.HomeSp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.face_rolling.MyViewModel
import com.example.face_rolling.R
import com.example.face_rolling.data.Course
import com.example.face_rolling.ui.preLog.Login

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(viewModel: MyViewModel) {
    var search by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
            Text(text = "首页")
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                placeholder = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "搜索行程", modifier = Modifier.padding(end = 8.dp))
                        Icon(painter = painterResource(id = R.drawable.icon_magnifier_small), contentDescription = null)
                    }
                },
                maxLines = 1,
                shape = CircleShape,
                textStyle = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)) {
            Text(text = "欢迎使用人脸识别签到系统")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "现在是 ${viewModel.today}，您今天的行程有")
        }
        LazyColumn(modifier = Modifier.weight(1f)) {
            itemsIndexed(viewModel.courseList) { index, item ->
                CourseShow(item)
            }
        }
    }
}

@Composable
fun CourseShow(course: Course) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Image(painter = painterResource(id = course.avatar), contentDescription = null)
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = course.name.toString())
            Text(text = course.getPeriod.toString())
        }
        IconButton(onClick = {}, Modifier.width(65.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
//                Text(text = "去点名", maxLines = 1, modifier = Modifier.padding(5.dp))
                Icon(painter = painterResource(id = R.drawable.icon_right_arrow), contentDescription = null)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun viewHome() {
    val viewModel: MyViewModel = viewModel()
    Surface(modifier = Modifier.fillMaxSize()) {
        Home(viewModel = viewModel)
    }
}

