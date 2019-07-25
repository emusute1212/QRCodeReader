package com.yosuke.qrcodereader.home

import androidx.lifecycle.ViewModel
import com.yosuke.qrcodereader.NavigationController
import javax.inject.Inject

class HomeViewModel @Inject constructor(
        private val homeNavigator: NavigationController
) : ViewModel() {
    companion object {
        private val TAG = HomeViewModel::class.java.simpleName
    }

    fun goToReadQrCode() {
        homeNavigator.goToReadQrCode()
    }

    fun goToMakeQrCode() {
        homeNavigator.goToGenerateQrCode()
    }
}