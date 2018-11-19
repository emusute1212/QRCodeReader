package com.yosuke.qrcodereader.home

import android.widget.Toast
import com.yosuke.qrcodereader.reader.QrCodeReadActivity
import javax.inject.Inject

class HomeNavigator @Inject constructor(
        private val mainActivity: MainActivity
) {
    fun goToReadQrCode() {
        QrCodeReadActivity.startActivity(mainActivity)
    }

    fun goToMakeQrCode() {
        Toast.makeText(mainActivity, "Go to make qr code.", Toast.LENGTH_SHORT).show()
    }
}