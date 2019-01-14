package com.yosuke.qrcodereader.home

import android.arch.lifecycle.ViewModel
import com.yosuke.qrcodereader.HomeNavigator
import javax.inject.Inject

class HomeViewModel @Inject constructor(
        private val homeNavigator: HomeNavigator
) : ViewModel() {
    companion object {
        val TAG: String
            get() = HomeViewModel::class.java.simpleName
    }

    fun goToReadQrCode() {
        homeNavigator.goToReadQrCode()
    }

    fun goToMakeQrCode() {
        homeNavigator.goToGenerateQrCode()
    }
}