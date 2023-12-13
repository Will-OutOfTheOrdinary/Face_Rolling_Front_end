package com.example.face_rolling.network

import com.example.face_rolling.bean.PhotoBean
import com.example.face_rolling.bean.VerificationBean
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {


    /*
    首页
     */
    @Multipart
    @POST("takePhoto")
    fun takePhoto(@Part file: MultipartBody.Part): Call<PhotoBean>

    @Multipart
    @POST("addCourse")
    fun addCourse(@Field("teamName") teamName:String,
                  @Field("date") date:String,
                  @Field("startTime") startTime:String,
                  @Field("endTime") endTime:String,
    ): Call<PhotoBean>


    /*
    注册登录 接口
     */
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("name") account: String,
        @Field("password") password: String
    ): Call<EpidemicNews>

    @FormUrlEncoded
    @POST
    fun register (@Field("phone") phone:String,
                  @Field("verification") verification:String,
                  @Field("password1") password1:String,
                  @Field("password2") password2:String,
                  @Field("name") name:String,
    ): Call<VerificationBean>

    @FormUrlEncoded
    @POST("getVerification")
    fun getVerification(@Field("phone") phone: String): Call<VerificationBean>


    /*
    团队
     */
    @FormUrlEncoded
    @POST("creatTeam")
    fun creatTeam(
        @Field("teamName") name: String,
        @Field("period") period: String,
        @Field("teammate") teammate: String,
    ): Call<VerificationBean>

}



class EpidemicNews {

}
