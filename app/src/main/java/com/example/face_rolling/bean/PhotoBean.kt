package com.example.face_rolling.bean

import com.example.face_rolling.network.Constant.BASE_URL


class PhotoBean() {
     val avatarUri: String? = null
     val message: String? = null
    val status: Int = 0
     var avatarUriString  = ""
        get() {
            return "$BASE_URL$avatarUri"}

    override fun toString(): String {
        return "PhotoBean(avatarUri=$avatarUri, message=$message, status=$status, avatarUriString='$avatarUriString')"
    }


}