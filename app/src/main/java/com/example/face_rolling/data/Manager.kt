package com.example.face_rolling.data

class Manager(
    id: Int,//每人唯一id
    name: String = "user",//用户名
    account: String,//账号
) : User(id, name, account) {


}