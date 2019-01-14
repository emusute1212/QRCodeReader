package com.yosuke.qrcodereader.generator

import android.graphics.Bitmap
import android.widget.Toast
import com.yosuke.qrcodereader.data.repository.QrCodeRepository
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import javax.inject.Inject

class GeneratorNavigator @Inject constructor(
        private val activity: GeneratorActivity,
        private val repository: QrCodeRepository
) {
    fun viewQrCodeDialog() {
        activity.viewDialog()
    }

    fun save(image: Bitmap) {
        launch(UI) {
            val result = withContext(CommonPool) { repository.saveImage(image) }
            if (result) {
                Toast.makeText(activity, "QRコードの保存が完了しました。", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "QRコードの保存が失敗しました。", Toast.LENGTH_SHORT).show()
            }
        }
    }
}