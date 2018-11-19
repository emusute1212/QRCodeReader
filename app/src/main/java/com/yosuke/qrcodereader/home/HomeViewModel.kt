package com.yosuke.qrcodereader.home

import android.arch.lifecycle.ViewModel
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
        homeNavigator.goToMakeQrCode()
    }
}