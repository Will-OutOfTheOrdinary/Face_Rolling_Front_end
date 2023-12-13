package com.example.face_rolling.ui.postLog.team

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.face_rolling.MyViewModel
import com.example.face_rolling.data.Team
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.face_rolling.R


@Composable
fun Team(viewModel: MyViewModel) {
    var ifCreate by remember { mutableStateOf(false)   }
    Column {
        TopTeamBar() { ifCreate = it }
        if (ifCreate) {
            CreateNewTeam()
        }
        TeamListShow(viewModel.teamList)

    }

}

@Composable
fun TeamListShow(teamList: MutableList<Team>) {
    LazyColumn() {
        itemsIndexed(teamList){index, item ->
            TeamItem(item)
        }

    }
}

@Composable
fun TeamItem(item: Team) {
    Row(modifier = Modifier.padding(5.dp), verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(id = item.avatar), contentDescription = null)
        Text(text = item.name)
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.icon_right_arrow),
                contentDescription = null
            )
        }


    }
}

@Composable
fun TopTeamBar(selected: (Boolean) -> Unit) {
    var ifCreate by remember { mutableStateOf(false)   }
    Row {
        Text(text = "我的团队")
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.icon_magnifier_small),
                contentDescription = null
            )
        }
        IconButton(onClick = { selected(!ifCreate)
            ifCreate = !ifCreate}) {

            Icon(
                painter = painterResource(id = R.drawable.icon_add_small),
                contentDescription = null,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNewTeam() {
    Surface(
        shadowElevation = 3.dp,
        tonalElevation = 3.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        var teamName by remember { mutableStateOf("") }
        Column (Modifier.padding(20.dp)){
            Text(text = "创建新的团队")
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(0.dp,30.dp)
            ) {
                Text(text = "团队名字")
                OutlinedTextField(
                    modifier = Modifier.height(10.dp),
                    value = teamName,
                    onValueChange = {
                        teamName = it
                    })
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "团队人员选择")
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_right_arrow),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

