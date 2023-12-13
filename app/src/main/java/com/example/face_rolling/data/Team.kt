package com.example.face_rolling.data

import androidx.annotation.DrawableRes
import com.example.face_rolling.R

class Team(
    var name: String,
    var teammate: MutableList<User>? = null,
    @DrawableRes var avatar: Int = R.drawable.avatar_team,
    var avatarUri: String? = null

) {


}