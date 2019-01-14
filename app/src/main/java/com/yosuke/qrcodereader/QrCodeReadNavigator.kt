package com.yosuke.qrcodereader

import android.content.Intent
import android.net.Uri
import com.yosuke.qrcodereader.reader.QrCodeReadActivity
import javax.inject.Inject

class QrCodeReadNavigator @Inject constructor(
        private val activity: QrCodeReadActivity
) {
    fun openBrowser(uri: Uri) {
        Intent(Intent.ACTION_VIEW, uri).also {
            activity.startActivity(it)
        }
    }
}