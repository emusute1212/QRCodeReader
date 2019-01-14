package com.yosuke.qrcodereader

import com.yosuke.qrcodereader.generator.GeneratorActivity
import com.yosuke.qrcodereader.home.MainActivity
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