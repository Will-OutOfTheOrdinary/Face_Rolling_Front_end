package com.example.face_rolling.network

import com.example.face_rolling.network.Constant.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceCreator {


    private fun getRetrofit(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.MINUTES) // 连接超时时间为 2 分钟
            .readTimeout(20, TimeUnit.MINUTES) // 读取超时时间为 2 分钟
            .writeTimeout(20, TimeUnit.MINUTES) // 写入超时时间为 2 分钟
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
               okHttpClient
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    fun <T> create(serviceClass: Class<T>): T = getRetrofit().create(serviceClass)

//    inline fun <reified T> create(): T = create(T::class.java)
}

