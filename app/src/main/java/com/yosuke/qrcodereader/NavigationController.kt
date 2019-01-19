package com.yosuke.qrcodereader

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.yosuke.qrcodereader.data.repository.QrCodeRepository
import com.yosuke.qrcodereader.generator.GeneratorActivity
import com.yosuke.qrcodereader.generator.QrCodeViewDialogFragment
import com.yosuke.qrcodereader.reader.QrCodeReadActivity
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import javax.inject.Inject

class NavigationController @Inject constructor(
        private val activity: AppCompatActivity,
        private val repository: QrCodeRepository
) {

    fun goToReadQrCode() {
        QrCodeReadActivity.startActivity(activity)
    }

    fun goToGenerateQrCode() {
        GeneratorActivity.startActivity(activity)
    }

    fun openBrowser(uri: Uri) {
        Intent(Intent.ACTION_VIEW, uri).also {
            activity.startActivity(it)
        }
    }

    fun viewQrCodeDialog() {
        QrCodeViewDialogFragment.newInstance().show(activity.supportFragmentManager, QrCodeViewDialogFragment.FRAGMENT_TAG)
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