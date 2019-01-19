package com.yosuke.qrcodereader

import android.support.v7.app.AppCompatActivity
import com.yosuke.qrcodereader.data.repository.QrCodeRepository
import javax.inject.Inject

class NavigationController @Inject constructor(
        private val activity: AppCompatActivity,
        private val repository: QrCodeRepository
) {

}