package com.yosuke.qrcodereader.home

import com.yosuke.qrcodereader.generator.GeneratorActivity
import com.yosuke.qrcodereader.reader.QrCodeReadActivity
import javax.inject.Inject

class HomeNavigator @Inject constructor(
        private val mainActivity: MainActivity
) {
    fun goToReadQrCode() {
        QrCodeReadActivity.startActivity(mainActivity)
    }

    fun goToGenerateQrCode() {
        GeneratorActivity.startActivity(mainActivity)
    }
}