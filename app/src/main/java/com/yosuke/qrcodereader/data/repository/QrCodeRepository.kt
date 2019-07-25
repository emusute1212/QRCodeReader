package com.yosuke.qrcodereader.data.repository

import android.graphics.Bitmap
import androidx.annotation.WorkerThread

interface QrCodeRepository {
    @WorkerThread
    fun saveImage(image: Bitmap): Boolean

    @WorkerThread
    fun encodeQrCode(text: String): Bitmap
}