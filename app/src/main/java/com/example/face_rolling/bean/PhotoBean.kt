package com.example.face_rolling.bean

import com.example.face_rolling.data.User
import com.example.face_rolling.network.Constant.BASE_URL


class PhotoBean() {
     val avatarUri: User? = null
     val message: String? = null
    val status: Int = 0
    override fun toString(): String {
        return "PhotoBean(avatarUri=$avatarUri, message=$message, status=$status)"
    }


}