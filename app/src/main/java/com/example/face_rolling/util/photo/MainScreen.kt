package com.example.face_rolling.util.photo

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.face_rolling.bean.PhotoBean
import com.example.face_rolling.network.NetworkRequest.uploadPhoto
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import kotlin.math.log


val TAG: String = "Photo"

@Composable
fun MainScreen() {

    val mediaAction by lazy { PhotoComponent.instance }
    var localImgPath by remember{
        mutableStateOf(Uri.EMPTY)
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(24.dp))
            AsyncImage(
                model = localImgPath, contentDescription = null,
                modifier = Modifier
                    .width(48.dp)
                    .height(48.dp)
                    .clip(CircleShape)
                    .placeholder(
                        visible = localImgPath == Uri.EMPTY,
                        color = Color(231, 234, 239, 255),
                        highlight = PlaceholderHighlight.shimmer(),
                    ),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.height(24.dp))

            TextButton(
                onClick = {
                    mediaAction.takePhoto()
                },
            ) {
                Text(text = "拍照")
            }
            Spacer(modifier = Modifier.height(24.dp))
            TextButton(
                onClick = {
                    mediaAction.selectImage()
                },
            ) {
                Text(text = "相册")
            }
            Button(onClick = {
            }) {

                Text(text = "upload")

                val file = File(localImgPath.path.toString())
                val requestFile = RequestBody.create(
                    contentType = "image/png".toMediaTypeOrNull(),
                    file
                )
                val body = MultipartBody.Part.createFormData(
                    "photo",
                    file.name,
                    requestFile
                )
                uploadPhoto(body).enqueue(object : Callback<PhotoBean> {
                    override fun onResponse(call: Call<PhotoBean>, response: Response<PhotoBean>) {
                        if (response.isSuccessful) {
                            print(response.body().toString())
                        }
                    }

                    override fun onFailure(call: Call<PhotoBean>, t: Throwable) {
                        t.printStackTrace()
                    }

                })
            }
        }
    }

    mediaAction.Register(
        galleryCallback = {
            Log.d(TAG, "MainScreen: 相册内容${it}")
            if (it.isSuccess) {
                localImgPath = it.uri
            }
        },
        graphCallback = {
            Log.d(TAG, "MainScreen: 拍照内容${it.uri}")
            if (it.isSuccess) {
                localImgPath = it.uri
            }
        },
        permissionRationale = {
            //权限拒绝的处理
        }
    )
}
