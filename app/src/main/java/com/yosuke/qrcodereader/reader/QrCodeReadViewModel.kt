package com.yosuke.qrcodereader.reader

import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableField
import android.net.Uri
import android.webkit.URLUtil
import com.yosuke.qrcodereader.NavigationController
import javax.inject.Inject

class QrCodeReadViewModel @Inject constructor(
        private val navigator: NavigationController
) : ViewModel() {

    val readText = ObservableField<String>()

    fun setReadText(value: String) {
        readText.set(value)
    }

    fun onOpenBrowserButton() {
        navigator.openBrowser(Uri.parse(readText.get()))
    }

    fun isUrl(target: String?): Boolean {
        if (target == null) return false
        return URLUtil.isValidUrl(target)
    }

}