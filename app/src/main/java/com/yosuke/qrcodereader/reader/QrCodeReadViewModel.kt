package com.yosuke.qrcodereader.reader

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.net.Uri
import android.webkit.URLUtil
import javax.inject.Inject

class QrCodeReadViewModel @Inject constructor(
        private val navigator: QrCodeReadNavigator
) : ViewModel() {

    val readText = ObservableField<String>()

    fun setReadText(value: String) {
        readText.set(value)
    }

    fun onOpenBrowserButton() {
        navigator.openBrowser(Uri.parse(readText.get()))
    }

    fun isUrl(target: String): Boolean {
        return URLUtil.isValidUrl(target)
    }

}