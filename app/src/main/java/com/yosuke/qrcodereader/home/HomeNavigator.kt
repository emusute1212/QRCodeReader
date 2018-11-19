package com.yosuke.qrcodereader.home

import android.widget.Toast
import javax.inject.Inject

class HomeNavigator @Inject constructor(
        private val mainActivity: MainActivity
) {
    fun goToReadQrCode() {
        Toast.makeText(mainActivity, "Go to read qr code.", Toast.LENGTH_SHORT).show()
    }

    fun goToMakeQrCode() {
        Toast.makeText(mainActivity, "Go to make qr code.", Toast.LENGTH_SHORT).show()
    }
}