
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.face_rolling.MyViewModel
import com.example.face_rolling.ui.postLog.person.ImageSelectionScreen
import kotlinx.coroutines.launch


//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun ModalBottomSheetLayout(viewModel: MyViewModel) {
//    val modalBottomSheetState =
//        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
//    val coroutineScope = rememberCoroutineScope()
//    Column {
//        // 使用ModalBottomSheetLayout包装BottomSheet的内容
//        ModalBottomSheetLayout(
//
//            sheetState = modalBottomSheetState,
//            sheetContent = {
//                // BottomSheet的内容
//                Column {
//
//                    ImageSelectionScreen(viewModel)
//                    Button(onClick = { coroutineScope.launch { modalBottomSheetState.hide() } }) {
//                        Text("Close BottomSheet")
//                    }
//                }
//            },
////            modifier = Modifier.height(50.dp)
//        ) {
//            // 主要内容
//            Button(onClick = {
//                Log.e("TAG", "TTTTT")
//                coroutineScope.launch { modalBottomSheetState.show() }
//            }) {
//                Text("弹出 BottomSheet")
//            }
//        }
//    }
//}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetModal(
    showModal: Boolean,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    Log.d("tag", "BottomSheetModal: $showModal")

    var sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            Surface(
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                elevation = 16.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    content()
                }
            }
        },
        content = {},
        sheetBackgroundColor = Color.White,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetContentColor = Color.Black
    )

        LaunchedEffect(showModal) {
            Log.d("tag", "BottomSheetModal: $showModal")
            if (showModal) {
                // 打开底部弹窗
                sheetState.show()
            } else {
                // 关闭底部弹窗
                sheetState.hide()

            }
        }

}
