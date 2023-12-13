package com.example.face_rolling.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Chat(
    var friend: User,
    var messages: MutableList<Message>

) {

}

class Message(
    val from: User,
    val text: String,
    val time: String
) {
    var read: Boolean by mutableStateOf(true)

}

class Notification(){

}