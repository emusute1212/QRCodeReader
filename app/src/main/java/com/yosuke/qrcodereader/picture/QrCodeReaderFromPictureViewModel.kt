package com.yosuke.qrcodereader.picture

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.yosuke.qrcodereader.usecase.QrCodeUsecase
import javax.inject.Inject

class QrCodeReaderFromPictureViewModel @Inject constructor(
        private val usecase: QrCodeUsecase
) : ViewModel() {

    fun scanQRImage(target: Bitmap): String {
        return usecase.scanQRImage(target)
    }
}