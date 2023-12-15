package com.example.face_rolling.ui.postLog

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toFile
import com.example.face_rolling.bean.TeamFileBean
import com.example.face_rolling.network.NetworkRequest
import com.example.face_rolling.util.ToastUtils
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


@Composable
fun ExcelFileUploadButton(onFileSelected: (Uri) -> Unit) {
    val toastUtils = ToastUtils.getInstance(LocalContext.current)
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        // 当用户选择文件后执行此代码块
        onFileSelected.invoke(uri!!)
        toastUtils.show("选择了文件")
    }

    Button(
        onClick = { launcher.launch("application/vnd.ms-excel") },
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "选择Excel文件")
    }
}


@Composable
fun ChooseExcelFileScreen() {
    val toastUtils = ToastUtils.getInstance(LocalContext.current)

    var selectedFileUri by remember { mutableStateOf<Uri?>(null) }

    Column {

        ExcelFileUploadButton { uri ->
            selectedFileUri = uri
        }

        var file: File? = null
        if (selectedFileUri != null) {
            Text(text = "已选择的Excel文件: ${selectedFileUri.toString()}")
            file = selectedFileUri!!.toFile()

            val requestBody =
                RequestBody.create("application/vnd.ms-excel".toMediaTypeOrNull(), file!!)
            val body = MultipartBody.Part.createFormData(
                "photo",
                file.name,
                requestBody
            )

            val call = NetworkRequest.creatTeamByFile("", body)
            call.enqueue(object : Callback<TeamFileBean> {
                override fun onResponse(
                    call: Call<TeamFileBean>,
                    response: Response<TeamFileBean>
                ) {
                    if (response.isSuccessful) {
                        val bean = response.body()
                        if (bean?.status == 200) {
                            toastUtils.show("创建成功")
                        }
                    }
                }

                override fun onFailure(call: Call<TeamFileBean>, t: Throwable) {
                    toastUtils.show("网络请求失败")

                }

            })
        }
        else{
            toastUtils.show("空")
//
        }

    }
}



