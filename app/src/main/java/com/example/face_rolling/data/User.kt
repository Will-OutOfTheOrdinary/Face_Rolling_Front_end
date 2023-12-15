package com.example.face_rolling.data

import androidx.annotation.DrawableRes
import com.example.face_rolling.R
import com.google.gson.Gson

open class User(
    var id: Int,//每人唯一id
    var name: String = "user",//用户名
    var account: String,//账号
    @DrawableRes var avatar: Int = R.drawable.avatar_default,
    var password:String ?=null,
    var avatarUri: String? = null,
    var gender: String? = null,
    var region: String? = null,
    var phone: String? = null,
    var email: String? = null,
    var joinInTeam: List<Team> = mutableListOf(),
    var createTeam: List<Team> = mutableListOf()
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
        var Me: User = User(1, "hyk","18663114530")
        var MeJson =""
            get() {
                return Gson().toJson(Me.toString())}

        val szx = User(2, "天上的白云真白啊", "123", avatar =  R.drawable.avatar_szx)
        val hjj = User(3, "ღ人生总得慢慢熬", "123",R.drawable.avatar_hjj)
    }

    override fun toString(): String {
        return "User(id=$id, name='$name', account='$account', avatar=$avatar, password=$password, avatarUri=$avatarUri, gender=$gender, region=$region, phone=$phone, email=$email, joinInTeam=$joinInTeam, createTeam=$createTeam)"
    }


}
