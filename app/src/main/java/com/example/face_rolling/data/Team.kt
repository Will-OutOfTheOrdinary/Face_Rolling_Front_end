package com.example.face_rolling.data

import androidx.annotation.DrawableRes
import com.example.face_rolling.R

class Team(
    var name: String = "",
    var id: Int = 0,
    var teammate: MutableList<User>? = null,
    var manager: Manager? = null,
    @DrawableRes var avatar: Int = R.drawable.avatar_team,
    var avatarUri: String? = null,
    var percent: String? =null,
    var late: List<User> = mutableListOf<User>(),

)
{


}