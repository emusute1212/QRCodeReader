package com.yosuke.qrcodereader.home

import android.arch.lifecycle.ViewModel
import com.yosuke.qrcodereader.NavigationController
import javax.inject.Inject

class HomeViewModel @Inject constructor(
        private val homeNavigator: NavigationController
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