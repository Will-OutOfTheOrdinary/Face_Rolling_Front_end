package com.example.face_rolling.ui.postLog.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.face_rolling.R
import com.example.face_rolling.data.Course

@Composable
fun UpPhoto(navController: NavController, course: Course?) {
    Column {
            Text(text = "上传人脸信息")
        Text(text = "拍照上传")
        IconButton(onClick = {
            /* TODO
                navController.navigate() */
            navController.popBackStack()
        }) {
            Icon(painter = painterResource(id = R.drawable.icon_add_large), contentDescription = null)

        }
    }

}
@Preview(showBackground = true)
@Composable
fun ViewUpPhoto() {
    Surface(modifier = Modifier.fillMaxSize()) {
//        UpPhoto(course = testCourse)
    }
}