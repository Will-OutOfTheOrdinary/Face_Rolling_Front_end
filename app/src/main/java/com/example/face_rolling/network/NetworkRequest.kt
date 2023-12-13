package com.example.face_rolling.network

import com.example.face_rolling.bean.PhotoBean
import com.example.face_rolling.bean.VerificationBean
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object NetworkRequest {

    /**
     * 创建服务
     */
    private val service = ServiceCreator.create(ApiService::class.java)

    //通过await()函数将getNews()函数也声明成挂起函数。使用协程
    suspend fun getEpidemicNews() = service.login("hyk","123456").await()

    //通过await()函数将getNews()函数也声明成挂起函数。使用协程
    fun uploadPhoto(body: MultipartBody.Part): Call<PhotoBean> = service.takePhoto(body)

    fun getVerification(): Call<VerificationBean> = service.getVerification("18663114530")



    /**
     * Retrofit网络返回处理
     */
    private suspend fun <T> Call<T>.await(): T = suspendCoroutine {
        enqueue(object : Callback<T> {
            //正常返回
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                if (body != null) it.resume(body)
                else it.resumeWithException(RuntimeException("response body is null"))
            }

            //异常返回
            override fun onFailure(call: Call<T>, t: Throwable) {
                it.resumeWithException(t)
            }
        })
    }

}
