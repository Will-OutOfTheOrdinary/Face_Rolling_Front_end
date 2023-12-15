package com.example.face_rolling.network

import com.example.face_rolling.bean.PhotoBean
import com.example.face_rolling.bean.RecognizeBean
import com.example.face_rolling.bean.TeamFileBean
import com.example.face_rolling.bean.UserBean
import com.example.face_rolling.bean.VerificationBean
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {


    /*
    首页
     */


    @FormUrlEncoded
    @POST("addCourse")
    fun addCourse(
        @Field("teamName") teamName: String,
        @Field("date") date: String,
        @Field("startTime") startTime: String,
        @Field("endTime") endTime: String,
    ): Call<PhotoBean>


    /*
    注册登录 接口
     */
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("name") account: String,
        @Field("password") password: String
    ): Call<UserBean>

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("phone") phone: String,
        @Field("verification") verification: String,
        @Field("password1") password1: String,
        @Field("password2") password2: String,
        @Field("name") name: String,
    ): Call<UserBean>

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
        @Field("teammate") teammate: String,
    ): Call<TeamFileBean>


    @Multipart
    @POST("creatTeamByFile")
    fun creatTeamByFile(
        @Part("teamName") name: String,
        @Part file: MultipartBody.Part,
    ): Call<TeamFileBean>


    /**
     * 面部信息绑定的方法
     */
    @Multipart
    @POST("connectImageToPerson")
    fun connectImageToPerson(
        @Part file: MultipartBody.Part,
        @Part("person_id") id: Int
    ): Call<UserBean>

    /**
     * 人脸识别签到的方法
     */
    @Multipart
    @POST("FaceReconize")
    fun FaceReconize(
        @Part  together_image: MultipartBody.Part,
        @Part("team_id") id: Int
    ): Call<RecognizeBean>


    /**
     * 查询team的信息
     */
    @FormUrlEncoded
    @POST("getTeamInfo")
    fun getTeamInfo(
        @Field("team_id") id: Int
    ): Call<UserBean>


    /*
    我的
     */
    @FormUrlEncoded
    @POST("getPersonalInfor")
    fun getPersonalInfor(
        @Field("id") id: Int
    ): Call<UserBean>


}



class EpidemicNews {

}
