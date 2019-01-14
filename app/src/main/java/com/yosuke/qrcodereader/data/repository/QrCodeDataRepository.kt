package com.yosuke.qrcodereader.data.repository

import android.graphics.Bitmap
import android.os.Environment
import com.yosuke.qrcodereader.ext.toStringForFileName
import org.threeten.bp.ZonedDateTime
import java.io.File
import java.io.FileOutputStream

class QrCodeDataRepository : QrCodeRepository {
    override fun saveImage(image: Bitmap): Boolean {
        if (!SAVE_DIR.exists()) SAVE_DIR.mkdirs()
        var result = false
        FileOutputStream(File(SAVE_DIR, ZonedDateTime.now().toStringForFileName().plus(IMAGE_EXT))).use {
            result = image.compress(Bitmap.CompressFormat.PNG, 100, it)
            it.fd.sync()
            it.flush()
        }
        return result
    }

    companion object {
        private val BASE_DIR_PATH = Environment.getExternalStorageDirectory().path
        private val BASE_DIR = File(BASE_DIR_PATH)
        private const val SAVE_DIR_PATH = "QR_CODE"
        val SAVE_DIR = File(BASE_DIR, SAVE_DIR_PATH)
        const val IMAGE_EXT = ".png"
    }
}