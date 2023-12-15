package com.example.face_rolling.ui.postLog.team

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import com.example.face_rolling.MyViewModel
import com.example.face_rolling.data.Team
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.face_rolling.R
import com.example.face_rolling.bean.UserBean
import com.example.face_rolling.data.User
import com.example.face_rolling.network.NetworkRequest
import com.example.face_rolling.store.SharedPreferencesHelper
import com.example.face_rolling.ui.postLog.person.FACE_RECOGNIZE
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("MutableCollectionMutableState")
@Composable
fun Team(viewModel: MyViewModel, navController: NavHostController) {
    val ifCreate = remember { mutableStateOf(false) }
    val sharedPreferencesHelper = SharedPreferencesHelper.getInstance(LocalContext.current)
    val gson = Gson()

    LaunchedEffect(Unit) {
        // 在 Composable 访问期间启动协程
        val call = NetworkRequest.getPersonalInfor(
            gson.fromJson(
                sharedPreferencesHelper.getData(
                    "me",
                    User.MeJson
                ),
                User::class.java
            ).id
        )
        call.enqueue(object : Callback<UserBean> {
            override fun onResponse(call: Call<UserBean>, response: Response<UserBean>) {
                val string = response.body()
                if (response.isSuccessful) {
                    if (string?.status == 200) {
                        User.Me = string.data!!
                        if (string.data.joinInTeam.isNotEmpty()) {
                            viewModel.teamList = string.data.joinInTeam.toMutableList()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<UserBean>, t: Throwable) {

            }
        })
    }
    Column {
        TopTeamBar(ifCreate.value) { ifCreate.value = !ifCreate.value }

        if (ifCreate.value) {
            CreateNewTeam()
        }

        TeamListShow(viewModel, navController)
    }
}

@Composable
fun TeamListShow(viewModel: MyViewModel, navController: NavHostController) {
    val teamList = viewModel.teamList

    LazyColumn {
        items(teamList) { item ->
            TeamItem(item, navController, viewModel)
        }
    }
}

@Composable
fun TeamItem(item: Team, navController: NavHostController, viewModel: MyViewModel) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.avatar),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(text = item.name)
                Text(text = "ID: ${item.id}", color = Color.Gray)
            }
            IconButton(
                onClick = {
                    //给团队拍照
                    viewModel.toggleBottomSheet(item.id, FACE_RECOGNIZE)
                },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_camera),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_right_arrow),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        val team = viewModel.userAbsentData
        if (team.late.isNotEmpty() && item.id == team.id) {
            AbsentDataShow(viewModel.userAbsentData)
        }
    }

}

@Composable
fun AbsentDataShow(userAbsentData: Team) {
    Column(Modifier.padding(vertical = 16.dp, horizontal = 8.dp)) {
        Text(text = "Percent: ${userAbsentData.percent}", modifier = Modifier.fillMaxWidth())

        LazyColumn(modifier = Modifier.height(40.dp)) {
            items(userAbsentData.late) { item ->
                Text(text = item.name)
            }
        }
    }
}

@Composable
fun TopTeamBar(ifCreate: Boolean, selected: () -> Unit) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "我的团队", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.size(32.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_magnifier_small),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        }
        IconButton(
            onClick = selected,
            modifier = Modifier.size(32.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_add_small),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNewTeam() {
    Surface(
        shadowElevation = 3.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
    ) {
        var teamName by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = teamName,
                onValueChange = { teamName = it },
                label = { Text("团队名字") }
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "团队人员选择")
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_right_arrow),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "创建新的团队")
            }
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CreateNewTeam() {
//    Surface(
//        shadowElevation = 3.dp,
//        tonalElevation = 3.dp,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(30.dp)
//            .clip(RoundedCornerShape(20.dp))
//    ) {
//        var teamName by remember { mutableStateOf("") }
//
//        Column(Modifier.padding(20.dp)) {
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier.padding(0.dp, 30.dp)
//            ) {
//                Text(text = "团队名字")
//                OutlinedTextField(
//                    modifier = Modifier.height(10.dp),
//                    value = teamName,
//                    onValueChange = {
//                        teamName = it
//                    }
//                )
//            }
//
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Text(text = "团队人员选择")
//                IconButton(onClick = { /*TODO*/ }) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.icon_right_arrow),
//                        contentDescription = null
//                    )
//                }
//            }
//
//            //创建团队按钮，弃用
//            TextButton(onClick = {}) {
//                Text(text = "创建新的团队")
//            }
//        }
//    }
//}
