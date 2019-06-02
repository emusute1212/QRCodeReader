package com.yosuke.qrcodereader.data

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Environment
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.yosuke.qrcodereader.ext.toStringForFileName
import org.threeten.bp.ZonedDateTime
import java.io.File
import java.util.*

object QrCodeExporter {

    private val BASE_DIR_PATH = Environment.getExternalStorageDirectory().path
    private val BASE_DIR = File(BASE_DIR_PATH)
    private const val SAVE_DIR_PATH = "QR_CODE"
    private val SAVE_DIR = File(BASE_DIR, SAVE_DIR_PATH)
    private const val IMAGE_EXT = ".png"

    fun exportFile(image: Bitmap): Boolean {
        if (!SAVE_DIR.exists()) SAVE_DIR.mkdirs()
        var result = false
        File(SAVE_DIR, ZonedDateTime.now().toStringForFileName().plus(IMAGE_EXT)).outputStream().use {
            result = image.compress(Bitmap.CompressFormat.PNG, 100, it)
            it.fd.sync()
            it.flush()
        }
        return result
    }

    fun exportQrCode(text: String): Bitmap {
        val encodeHint = Hashtable<EncodeHintType, Any>()
        encodeHint[EncodeHintType.CHARACTER_SET] = "UTF-8"

        //エラー修復レベルを指定
        //L 7%が復元可能
        //M 15%が復元可能
        //Q 25%が復元可能
        //H 30%が復元可能
        encodeHint[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.L

        // エンコード
        val matrix = QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, 100, 100, encodeHint)

        val width = matrix.width
        val height = matrix.height
        val pixels = IntArray(width * height)
        // データがあるところだけ黒にする
        for (y in 0 until height) {
            for (x in 0 until width) {
                pixels[(y * width) + x] = if (matrix.get(x, y)) Color.BLACK else Color.WHITE
            }
        }
        return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).apply {
            setPixels(pixels, 0, width, 0, 0, width, height)
        }
    }
}