package com.yosuke.qrcodereader

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yosuke.qrcodereader.data.repository.QrCodeRepository
import com.yosuke.qrcodereader.generator.GeneratorActivity
import com.yosuke.qrcodereader.generator.QrCodeViewDialogFragment
import com.yosuke.qrcodereader.reader.QrCodeReadActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import androidx.core.app.ActivityCompat.startActivityForResult
import com.yosuke.qrcodereader.picture.QrCodeReaderFromPictureActivity

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

    fun goToQrCodeReaderFromPicture(){
        QrCodeReaderFromPictureActivity.startActivity(activity)
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
        GlobalScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.Default) { repository.saveImage(image) }
            if (result) {
                Toast.makeText(activity, "QRコードの保存が完了しました。", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "QRコードの保存が失敗しました。", Toast.LENGTH_SHORT).show()
            }
        }
    }
}