package com.yosuke.qrcodereader.home

import android.arch.lifecycle.ViewModel
import android.util.Log
import javax.inject.Inject

class HomeViewModel @Inject constructor() : ViewModel() {
    companion object {
        val TAG: String
            get() = HomeViewModel::class.java.simpleName
    }

    fun goToReadQrCode() {
        Log.d(TAG, "Go to ReadQrCode.")
    }

    fun goToMakeQrCode() {
        Log.d(TAG, "Go to MakeQrCode")
    }
}