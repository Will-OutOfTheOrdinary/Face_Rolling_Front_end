package com.example.face_rolling.bean

import com.example.face_rolling.data.User

class TeamSearchBean(
    val data: TeamSearchData? = null,
    val message: String? = null,
    val status: Int = 0,
) {
    override fun toString(): String {
        return "TeamSearchBean(data=$data, message=$message, status=$status)"
    }
}
class TeamSearchData {
     val id: String? = null
     val members: List<User>? = null
     val number = 0
    override fun toString(): String {
        return "TeamSearchData(id=$id, members=$members, number=$number)"
    }

}