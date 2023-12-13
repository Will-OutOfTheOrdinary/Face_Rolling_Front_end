package com.example.face_rolling.data

import androidx.annotation.DrawableRes
import com.example.face_rolling.R

var testCourse : Course = Course("阮公会议", "03-22", "09:00", "11:00")

class Course(

    var name: String,
    //MM-dd
    var date : String,
    //HH:mm
    var startTime: String,

    var endTime: String,

    @DrawableRes
    var avatar: Int = R.drawable.icon_course_avatar
){


    var getPeriod: String? = null
        get() {
            return "$date $startTime-$endTime"
    }

}