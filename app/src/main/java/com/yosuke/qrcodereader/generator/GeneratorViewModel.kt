package com.yosuke.qrcodereader.generator

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel.L
import kotlinx.coroutines.experimental.launch
import java.util.*
import javax.inject.Inject


class GeneratorViewModel @Inject constructor(
        private val navigator: GeneratorNavigator
) : ViewModel() {
    val qrCodeStr = ObservableField<String>()
    val qrCode = ObservableField<Bitmap>()

    fun onGenerateButton() {
        if (qrCodeStr.get().isNullOrEmpty()) return
        launch { qrCode.set(encode()) }
        navigator.viewQrCodeDialog()
    }

    fun onSaveButton(image: Bitmap) {
        navigator.save(image)
    }

    //TODO:エンコードは別の層で行う
    private fun encode(): Bitmap {
        //異なる型の値を入れるためgenericは使えない
        val encodeHint = Hashtable<EncodeHintType, Any>()
        encodeHint[EncodeHintType.CHARACTER_SET] = "UTF-8"

        //エラー修復レベルを指定
        //L 7%が復元可能
        //M 15%が復元可能
        //Q 25%が復元可能
        //H 30%が復元可能
        encodeHint[EncodeHintType.ERROR_CORRECTION] = L

        // エンコード
        val matrix = QRCodeWriter().encode(qrCodeStr.get(), BarcodeFormat.QR_CODE, 100, 100, encodeHint)
        // ピクセルを作る
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