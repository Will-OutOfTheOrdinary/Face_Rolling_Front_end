package com.example.face_rolling.bean

import com.example.face_rolling.data.Team
import com.example.face_rolling.data.User

class RecognizeBean {
     val data: Team? = null
     val message: String? = null
     val status = 0

    override fun toString(): String {
        return "RecognizeBean(data=$data, message=$message, status=$status)"
    }


}
