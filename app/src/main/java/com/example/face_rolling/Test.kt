import android.Manifest
import android.app.Activity
import android.content.Context

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ImageSelectionScreen() {
    val context = LocalContext.current

    val takePicture =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imageFile = File(currentPhotoPath.toString())
                // 图片已经拍摄，处理图片
                // 这里可以将图片文件传给后端
                println(imageFile.isFile)

            }
        }

    val pickImage =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri = result.data?.data
                uri?.let {
                    val imageFile = File(getRealPathFromUri(context, it))
                    // 从相册选择图片成功，处理图片
                    // 这里可以将图片文件传给后端
                }
            }
        }

    Column {
        Button(onClick = {
            takePicture.launch(createImageCaptureIntent(context))
        }) {
            Text("拍照")
        }

        Button(onClick = {
            pickImage.launch(Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI))
        }) {
            Text("选择照片")
        }
    }
}

private var currentPhotoPath: String? = null

private fun createImageCaptureIntent(context: Context): Intent {
    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val imageFileName = "JPEG_${timeStamp}_"

    val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    val imageFile = File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        storageDir /* directory */
    )
    currentPhotoPath = imageFile.absolutePath

    val photoURI: Uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        FileProvider.getUriForFile(
            context,
            context.packageName + ".fileprovider",
            imageFile
        )
    } else {
        Uri.fromFile(imageFile)
    }

    return Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
        putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
    }
}

private fun getRealPathFromUri(context: Context, uri: Uri): String? {
    val projection = arrayOf(MediaStore.Images.Media.DATA)
    val cursor = context.contentResolver.query(uri, projection, null, null, null)
    return cursor?.use {
        val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        it.moveToFirst()
        it.getString(columnIndex)
    }
}
