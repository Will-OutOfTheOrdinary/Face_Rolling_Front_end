package com.example.face_rolling.bean

import com.example.face_rolling.data.User


data class UserBean(
    val data: User? = null,
    val message: String? = null,
    val status: Int = 0,
) {

    override fun toString(): String {
        return "UserBean(data=$data, message=$message, status=$status)"
    }
}