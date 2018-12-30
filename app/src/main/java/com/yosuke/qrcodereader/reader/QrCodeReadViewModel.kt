package com.yosuke.qrcodereader.reader

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.webkit.URLUtil
import javax.inject.Inject

class QrCodeReadViewModel @Inject constructor() : ViewModel() {

    val readText = ObservableField<String>()

    fun setReadText(value: String) {
        readText.set(value)
    }

    fun isUrl(target: String): Boolean {
        return URLUtil.isValidUrl(target)
    }

}