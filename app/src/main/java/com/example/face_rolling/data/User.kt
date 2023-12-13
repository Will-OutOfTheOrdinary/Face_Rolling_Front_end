package com.example.face_rolling.data

import androidx.annotation.DrawableRes
import com.example.face_rolling.R

class User(
    var id: String,//每人唯一id
    var name: String = "user",//用户名
    var account: String,//账号
    @DrawableRes var avatar: Int = R.drawable.avatar_default,
    var avatarUri: String? = null,
    var gender: String? = null,
    var region: String? = null,
    var phone: String? = null,
    var email: String? = null,
    var joinInTeam: MutableList<Team> = mutableListOf(),
    var createTeam: MutableList<Team> = mutableListOf()
) {

    enum class Property {
        ID ,
        Name,
        ACCOUNT,
        AVATAR,
        GENDER,
        REGION,
        PHONE,
        EMAIL,
        JOIN_IN_TEAM,
        CREATE_TEAM

    }

    companion object{
        val Me: User = User("id", "hyk","18663114530")
        val szx = User("id", "天上的白云真白啊", "123", avatar  =  R.drawable.avatar_szx)
        val hjj = User("id", "ღ人生总得慢慢熬", "123",R.drawable.avatar_hjj)
    }

}
