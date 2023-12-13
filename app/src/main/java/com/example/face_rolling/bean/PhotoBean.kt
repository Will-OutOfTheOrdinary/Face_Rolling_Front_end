package com.example.face_rolling.bean

import android.provider.SettingsSlicesContract.BASE_URI

class PhotoBean() {
    private val avatarUri: String? = null
    private val message: String? = null
    private val status = 0
    private var avatarUriString  = ""
        get() {
            return "$BASE_URI$avatarUri"}
}