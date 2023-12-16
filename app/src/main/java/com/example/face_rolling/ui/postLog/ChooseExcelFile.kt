package com.example.face_rolling.ui.postLog

import android.content.ContentResolver
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
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
import okhttp3.MediaType

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExcelFileUploadButton(onFileSelected: (Uri) -> Unit) {

    val toastUtils = ToastUtils.getInstance(LocalContext.current)
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            // 当用户选择文件后执行此代码块
            onFileSelected.invoke(uri)
            toastUtils.show("选择了文件")
        }
    }
    Column {
        Button(
            onClick = { launcher.launch("application/vnd.ms-excel") },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "选择Excel文件")
        }

    }

}



@Composable
fun ChooseExcelFileScreen() {
    val toastUtils = ToastUtils.getInstance(LocalContext.current)

    var selectedFileUri by remember { mutableStateOf<Uri?>(null) }

    Column {

        ExcelFileUploadButton { uri,  ->
            selectedFileUri = uri

        }


        if (selectedFileUri != null) {

            toastUtils.show("选到了文件")
            Text(text = "已选择的Excel文件: ${selectedFileUri.toString()}")
            val contentResolver = LocalContext.current.contentResolver
            val file = convertUriToFile(contentResolver, selectedFileUri!!)

            Button(onClick = {
                if (file != null) {
                    // 创建请求体
                    val requestFile =
                        RequestBody.create("application/vnd.ms-excel".toMediaTypeOrNull(), file)

                    // 创建 MultipartBody.Part 对象
                    val filePart = MultipartBody.Part.createFormData("file", file.name, requestFile)

                    // 执行上传请求
                    val call = NetworkRequest.creatTeamByFile( filePart)
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
            }) {

            }

        }
        else{
            toastUtils.show("选取空")
        }
//        var file: File? = null
//        if (selectedFileUri != null) {
//            toastUtils.show("选到了文件")
//            Text(text = "已选择的Excel文件: ${selectedFileUri.toString()}")
//
//            file = selectedFileUri!!.toFile()
//
//            val requestBody =
//                RequestBody.create("application/vnd.ms-excel".toMediaTypeOrNull(), file)
//            val body = MultipartBody.Part.createFormData(
//                "file",
//                file.name,
//                requestBody
//            )
//            Text(text = teamName2)
//
//            Button(onClick = {
//
//
//
//
//            }) {
//                Text(text = "创建")
//            }
//            val call = NetworkRequest.creatTeamByFile(teamName2, body)
//            call.enqueue(object : Callback<TeamFileBean> {
//                override fun onResponse(
//                    call: Call<TeamFileBean>,
//                    response: Response<TeamFileBean>
//                ) {
//                    if (response.isSuccessful) {
//                        val bean = response.body()
//                        if (bean?.status == 200) {
//                            toastUtils.show("创建成功")
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<TeamFileBean>, t: Throwable) {
//                    toastUtils.show("网络请求失败")
//
//                }
//            })
    }
//        else{
//            toastUtils.show("空")
//
//        }

//    }
}

@Composable
fun convertUriToFile(contentResolver: ContentResolver, uri: Uri): File? {
    val inputStream = contentResolver.openInputStream(uri)
    val outputFile = File(LocalContext.current.cacheDir, "temp_file.xls")

    try {
        val outputStream = FileOutputStream(outputFile)
        inputStream?.copyTo(outputStream)
        outputStream.close()
        inputStream?.close()

        return outputFile
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return null
}




