package com.yosuke.qrcodereader.usecase

import android.graphics.Bitmap
import android.util.Log
import com.google.zxing.BinaryBitmap
import com.google.zxing.MultiFormatReader
import com.google.zxing.RGBLuminanceSource
import com.google.zxing.common.HybridBinarizer

class QrCodeUsecase {
    fun scanQRImage(target: Bitmap): String {
        val bitmap = IntArray(target.width * target.height).let { intArray ->
            target.getPixels(intArray, 0, target.width, 0, 0, target.width, target.height)
            return@let BinaryBitmap(
                    HybridBinarizer(
                            RGBLuminanceSource(target.width, target.height, intArray)
                    )
            )
        }
        return try {
            MultiFormatReader().decode(bitmap).text
        } catch (e: Exception) {
            Log.w(TAG, "Error decoding barcode", e)
            ""
        }
    }

    companion object {
        private val TAG = QrCodeUsecase::class.java.simpleName
    }
}