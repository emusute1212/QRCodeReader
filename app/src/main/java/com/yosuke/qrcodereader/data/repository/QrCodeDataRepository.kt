package com.yosuke.qrcodereader.data.repository

import android.graphics.Bitmap
import com.yosuke.qrcodereader.QrCodeApp
import com.yosuke.qrcodereader.data.QrCodeExporter
import javax.inject.Inject

class QrCodeDataRepository @Inject constructor(
        private val app: QrCodeApp
) : QrCodeRepository {
    override fun saveImage(image: Bitmap) = QrCodeExporter.exportFile(app.applicationContext, image)
    override fun encodeQrCode(text: String) = QrCodeExporter.exportQrCode(text)
}