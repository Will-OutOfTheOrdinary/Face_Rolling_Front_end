package com.example.face_rolling.util

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext

//val toastUtils = ToastUtils.getInstance(LocalContext.current)

class ToastUtils private constructor(private val context: Context) {

    private var toast: Toast? = null

    fun show(message: String, duration: Int = Toast.LENGTH_SHORT) {
        toast?.cancel() // 取消已有的 Toast

        toast = Toast.makeText(context.applicationContext, message, duration)
        toast?.show()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: ToastUtils? = null

        fun getInstance(context: Context): ToastUtils {
            return instance ?: synchronized(this) {
                instance ?: ToastUtils(context).also { instance = it }
            }
        }
    }
}

