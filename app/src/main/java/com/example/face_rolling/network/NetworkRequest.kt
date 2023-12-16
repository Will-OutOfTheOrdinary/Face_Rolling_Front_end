package com.example.face_rolling.network

import com.example.face_rolling.bean.PhotoBean
import com.example.face_rolling.bean.RecognizeBean
import com.example.face_rolling.bean.TeamFileBean
import com.example.face_rolling.bean.TeamSearchBean
import com.example.face_rolling.bean.UserBean
import com.example.face_rolling.bean.VerificationBean
import com.example.face_rolling.data.User
import okhttp3.MultipartBody
import retrofit2.Call

object NetworkRequest {

    /**
     * 创建服务
     */
    private val service = ServiceCreator.create(ApiService::class.java)

    /*
    登录注册的方法
     */
    fun login(
        account: String,
        password: String
    ): Call<UserBean> = service.login(account, password)

    fun getVerification(phone: String): Call<VerificationBean> = service.getVerification(phone)

    fun register(
        phone: String,
        verification: String,
        password1: String,
        password2: String,
        name: String
    ) : Call<UserBean> = service.register(phone, verification, password1, password2, name)


    /*
    团队的方法
     */
    fun creatTeam(name: String, teammateString: String): Call<TeamFileBean> =
        service.creatTeam(name, teammateString)

    fun creatTeamByFile(file:MultipartBody.Part): Call<TeamFileBean> =
        service.creatTeamByFile(file)


    fun connectImageToPerson(user_id: Int, avatar_file: MultipartBody.Part): Call<UserBean> =
        service.connectImageToPerson(avatar_file, user_id)

    fun faceRecognize(
        together_image: MultipartBody.Part,
        team_id: Int
    ): Call<RecognizeBean> = service.FaceReconize(together_image, team_id)


    fun getTeamInfo(
        team_id: Int
    ): Call<TeamSearchBean> = service.getTeamInfo(team_id)





    /*
    我的
     */
    fun getPersonalInfor(id: Int): Call<UserBean> = service.getPersonalInfor(id)






}
