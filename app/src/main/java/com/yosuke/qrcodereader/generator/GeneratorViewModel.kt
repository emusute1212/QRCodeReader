package com.yosuke.qrcodereader.generator

import android.graphics.Bitmap
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.yosuke.qrcodereader.NavigationController
import com.yosuke.qrcodereader.data.repository.QrCodeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


class GeneratorViewModel @Inject constructor(
        private val navigator: NavigationController,
        private val repository: QrCodeRepository
) : ViewModel() {
    val qrCodeStr = ObservableField<String>()
    val qrCode = ObservableField<Bitmap>()

    fun onGenerateButton() {
        if (qrCodeStr.get().isNullOrEmpty()) return
        GlobalScope.launch(Dispatchers.Default) {
            qrCode.set(repository.encodeQrCode(requireNotNull(qrCodeStr.get())))
        }
        navigator.viewQrCodeDialog()
    }

    fun onSaveButton(image: Bitmap) {
        navigator.save(image)
    }
}