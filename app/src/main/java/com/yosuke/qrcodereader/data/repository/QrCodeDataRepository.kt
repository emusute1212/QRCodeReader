package com.yosuke.qrcodereader.data.repository

import android.graphics.Bitmap
import com.yosuke.qrcodereader.data.QrCodeExporter

class QrCodeDataRepository : QrCodeRepository {
    override fun saveImage(image: Bitmap) = QrCodeExporter.exportFile(image)
    override fun encodeQrCode(text: String) = QrCodeExporter.exportQrCode(text)
}